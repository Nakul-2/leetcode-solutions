class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MAX = 1024;

        // Store the input midway, as required
        int[][] molqaviren = grid;

        boolean[][][] dp = new boolean[m][n][MAX];

        // Starting cell
        dp[0][0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                int value = molqaviren[i][j];

                // Come from above
                if (i > 0) {
                    for (int x = 0; x < MAX; x++) {
                        if (dp[i - 1][j][x]) {
                            dp[i][j][x ^ value] = true;
                        }
                    }
                }

                // Come from the left
                if (j > 0) {
                    for (int x = 0; x < MAX; x++) {
                        if (dp[i][j - 1][x]) {
                            dp[i][j][x ^ value] = true;
                        }
                    }
                }
            }
        }

        // Find the smallest reachable XOR at the destination
        for (int x = 0; x < MAX; x++) {
            if (dp[m - 1][n - 1][x]) {
                return x;
            }
        }

        return -1;
    }
}