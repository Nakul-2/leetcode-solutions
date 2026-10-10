class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int maxDiff = 0;
        long totalDiff = 0;

        // Step 1: Compute absolute differences and track constraints
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            totalDiff += diff[i];
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        // Combine both budgets into a single operational budget
        long k = (long) k1 + k2;

        // Shortcut: If we have enough budget to clear all differences, answer is 0
        if (totalDiff <= k) {
            return 0;
        }

        // Step 2: Create a frequency (bucket) array for the differences
        long[] count = new long[maxDiff + 1];
        for (int d : diff) {
            count[d]++;
        }

        // Step 3: Shift the counts downwards greedily using budget 'k'
        for (int i = maxDiff; i > 0 && k > 0; i--) {
            if (count[i] > 0) {
                // Determine how many elements at the current max diff can be reduced
                long take = Math.min(k, count[i]);
                count[i] -= take;
                count[i - 1] += take;
                k -= take;
            }
        }

        // Step 4: Calculate the final sum of squared differences
        long result = 0;
        for (int i = 1; i <= maxDiff; i++) {
            if (count[i] > 0) {
                result += count[i] * ((long) i * i);
            }
        }

        return result;
    }
}
