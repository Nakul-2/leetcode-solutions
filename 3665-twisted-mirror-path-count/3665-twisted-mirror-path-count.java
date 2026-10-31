public class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        // dp[i][j] stores the number of unique paths to reach the empty cell (i, j)
        int[][] dp = new int[m][n];
        
        // Base case: Start at the top-left corner
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Skip if the cell is unreachable or is a mirror itself
                if (dp[i][j] == 0 || grid[i][j] == 1) {
                    continue;
                }

                // --- Choice 1: Move Right ---
                int r = i;
                int c = j + 1;
                int dir = 0; // 0 = moving Right, 1 = moving Down
                
                while (r < m && c < n) {
                    if (grid[r][c] == 0) {
                        dp[r][c] = (dp[r][c] + dp[i][j]) % MOD;
                        break; // Successfully landed on an empty cell
                    }
                    // Hit a mirror! Reflect immediately
                    if (dir == 0) {
                        r++;     // Turned down -> move to the cell below the mirror
                        dir = 1; // Now moving Down
                    } else {
                        c++;     // Turned right -> move to the cell to the right of the mirror
                        dir = 0; // Now moving Right
                    }
                }

                // --- Choice 2: Move Down ---
                r = i + 1;
                c = j;
                dir = 1; // 1 = moving Down
                
                while (r < m && c < n) {
                    if (grid[r][c] == 0) {
                        dp[r][c] = (dp[r][c] + dp[i][j]) % MOD;
                        break; // Successfully landed on an empty cell
                    }
                    // Hit a mirror! Reflect immediately
                    if (dir == 0) {
                        r++;     // Turned down
                        dir = 1;
                    } else {
                        c++;     // Turned right
                        dir = 0;
                    }
                }
            }
        }

        // Return total paths reaching the bottom-right corner
        return dp[m - 1][n - 1];
    }
}
