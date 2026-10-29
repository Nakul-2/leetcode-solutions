import java.util.*;

class Solution {

    private int n, LOG, timer;
    private List<Integer>[] graph;

    private int[] tin, tout, depth, parent, order, subtreeSize;
    private int[][] up;
    private int[] rootMask;
    private char[] chars;
    private Fenwick bit;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        this.n = n;
        LOG = 32 - Integer.numberOfLeadingZeros(n);

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        order = new int[n];
        subtreeSize = new int[n];
        rootMask = new int[n];
        up = new int[LOG][n];
        chars = s.toCharArray();

        buildTreeInfo();

        // Initialize Fenwick Tree with root-path XOR masks.
        bit = new Fenwick(n + 1);

        int previousMask = 0;

        for (int i = 0; i < n; i++) {
            int node = order[i];
            int difference = rootMask[node] ^ previousMask;

            bit.update(i, difference);
            previousMask = rootMask[node];
        }

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {
                int node = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);

                if (chars[node] == newChar) {
                    continue;
                }

                int oldMask = 1 << (chars[node] - 'a');
                int newMask = 1 << (newChar - 'a');
                int difference = oldMask ^ newMask;

                // Update the root-path mask of every node in this subtree.
                bit.update(tin[node], difference);
                bit.update(tout[node] + 1, difference);

                chars[node] = newChar;

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v);

                int maskU = bit.query(tin[u]);
                int maskV = bit.query(tin[v]);
                int ancestorMask = 1 << (chars[ancestor] - 'a');

                int pathMask = maskU ^ maskV ^ ancestorMask;

                // At most one character may have an odd frequency.
                answer.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return answer;
    }

    private void buildTreeInfo() {
        int[] stack = new int[n];
        int top = 0;

        Arrays.fill(parent, -2);

        stack[top++] = 0;
        parent[0] = -1;

        timer = 0;

        // Iterative DFS avoids stack overflow on a deep tree.
        while (top > 0) {
            int node = stack[--top];

            tin[node] = timer;
            order[timer++] = node;

            subtreeSize[node] = 1;

            int p = parent[node];
            rootMask[node] =
                    (p == -1 ? 0 : rootMask[p])
                    ^ (1 << (chars[node] - 'a'));

            for (int next : graph[node]) {
                if (next == p || parent[next] != -2) {
                    continue;
                }

                parent[next] = node;
                depth[next] = depth[node] + 1;
                stack[top++] = next;
            }
        }

        // Calculate subtree sizes in reverse DFS order.
        for (int i = n - 1; i > 0; i--) {
            int node = order[i];
            subtreeSize[parent[node]] += subtreeSize[node];
        }

        for (int node = 0; node < n; node++) {
            tout[node] = tin[node] + subtreeSize[node] - 1;
            up[0][node] = parent[node] == -1 ? node : parent[node];
        }

        // Binary lifting table.
        for (int k = 1; k < LOG; k++) {
            for (int node = 0; node < n; node++) {
                up[k][node] = up[k - 1][up[k - 1][node]];
            }
        }
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int difference = depth[u] - depth[v];

        for (int k = 0; k < LOG; k++) {
            if ((difference & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    private static class Fenwick {
        private final int[] tree;
        private final int size;

        Fenwick(int size) {
            this.size = size;
            tree = new int[size + 1];
        }

        // XOR point update.
        void update(int index, int value) {
            for (int i = index + 1; i <= size; i += i & -i) {
                tree[i] ^= value;
            }
        }

        // XOR prefix query.
        int query(int index) {
            int result = 0;

            for (int i = index + 1; i > 0; i -= i & -i) {
                result ^= tree[i];
            }

            return result;
        }
    }
}