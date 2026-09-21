class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] ans = new long[k];

        // dp[r] = number of subarrays ending at the previous index
        // whose product % k == r
        long[] dp = new long[k];

        for (int i = 0; i < nums.length; i++) {

            int current = nums[i] % k;

            // Counts of subarrays ending at the current index
            long[] newDp = new long[k];

            // Start a new subarray containing only nums[i]
            newDp[current]++;

            // Extend every previous subarray
            for (int r = 0; r < k; r++) {

                if (dp[r] > 0) {

                    int newRemainder = (r * current) % k;

                    newDp[newRemainder] += dp[r];
                }
            }

            // Add current subarray counts to the final answer
            for (int r = 0; r < k; r++) {
                ans[r] += newDp[r];
            }

            dp = newDp;
        }

        return ans;
    }
}