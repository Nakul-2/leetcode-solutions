class Solution {
    static final int MOD = 1_000_000_007;

    int[] sum;
    int[] sumSq;
    int[] lazy;
    int n;

   public int sumCounts(int[] nums) {
      n = nums.length;

        sum = new int[4 * n];
        sumSq = new int[4 * n];
        lazy = new int[4 * n];

        Map<Integer, Integer> last = new HashMap<>();
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            int prev = last.getOrDefault(x, -1);

            update(1, 0, n - 1, prev + 1, i);

            ans = (ans + sumSq[1]) % MOD;

            last.put(x, i);
        }

        return (int) ans;
    }

    void update(int node, int left, int right, int ql, int qr) {
        if (ql > right || qr < left) {
            return;
        }

        if (ql <= left && right <= qr) {
            apply(node, left, right);
            return;
        }

        pushDown(node, left, right);

        int mid = left + (right - left) / 2;

        update(node * 2, left, mid, ql, qr);
        update(node * 2 + 1, mid + 1, right, ql, qr);

        sum[node] = (int) (((long) sum[node * 2]
                + sum[node * 2 + 1]) % MOD);

        sumSq[node] = (int) (((long) sumSq[node * 2]
                + sumSq[node * 2 + 1]) % MOD);
    }

    void apply(int node, int left, int right) {
        long len = right - left + 1;
        long oldSum = sum[node];

        sumSq[node] = (int) (
                (sumSq[node] + 2L * oldSum + len) % MOD
        );

        sum[node] = (int) ((oldSum + len) % MOD);

        lazy[node] = (lazy[node] + 1) % MOD;
    }

    void pushDown(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = left + (right - left) / 2;

        applyMultiple(node * 2, left, mid, lazy[node]);
        applyMultiple(node * 2 + 1, mid + 1, right, lazy[node]);

        lazy[node] = 0;
    }

    void applyMultiple(int node, int left, int right, int delta) {
        long len = right - left + 1;
        long d = delta;
        long oldSum = sum[node];

        sumSq[node] = (int) (
                (sumSq[node] + 2L * d * oldSum
                        + d * d % MOD * len) % MOD
        );

        sum[node] = (int) ((oldSum + d * len) % MOD);

        lazy[node] = (int) ((lazy[node] + d) % MOD);
    }
}