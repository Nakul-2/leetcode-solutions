class Solution {

    int n;
    int k;

    class Node {
        int prod;
        int[] cnt;

        Node() {
            prod = 1;
            cnt = new int[k];
        }
    }

    Node[] tree;

    // Merge two nodes
    Node merge(Node left, Node right) {

        Node result = new Node();

        // Product of complete segment
        result.prod = (left.prod * right.prod) % k;

        // Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            result.cnt[r] = left.cnt[r];
        }

        // Prefixes which enter the right segment
        for (int r = 0; r < k; r++) {

            int newRemainder = (left.prod * r) % k;

            result.cnt[newRemainder] += right.cnt[r];
        }

        return result;
    }

    // Build segment tree
    void build(int node, int start, int end, int[] nums) {

        if (start == end) {

            int value = nums[start] % k;

            tree[node].prod = value;
            tree[node].cnt[value] = 1;

            return;
        }

        int mid = (start + end) / 2;

        build(node * 2, start, mid, nums);
        build(node * 2 + 1, mid + 1, end, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Update one index
    void update(int node, int start, int end, int index, int value) {

        if (start == end) {

            tree[node] = new Node();

            int rem = value % k;

            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = (start + end) / 2;

        if (index <= mid) {
            update(node * 2, start, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, end, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Query range [left, right]
    Node query(int node, int start, int end, int left, int right) {

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        if (right <= mid) {
            return query(node * 2, start, mid, left, right);
        }

        if (left > mid) {
            return query(node * 2 + 1, mid + 1, end, left, right);
        }

        Node leftNode = query(node * 2, start, mid, left, right);
        Node rightNode = query(node * 2 + 1, mid + 1, end, left, right);

        return merge(leftNode, rightNode);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        for (int i = 0; i < 4 * n; i++) {
            tree[i] = new Node();
        }

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Update nums[index]
            nums[index] = value;
            update(1, 0, n - 1, index, value);

            // Query [start, n-1]
            Node result = query(1, 0, n - 1, start, n - 1);

            // Number of prefixes whose product % k == x
            ans[q] = result.cnt[x];
        }

        return ans;
    }
}