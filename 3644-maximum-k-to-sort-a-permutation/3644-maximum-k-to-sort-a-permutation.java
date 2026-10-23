class Solution {
    public int sortPermutation(int[] nums) {
        // Initialize ans with -1 (all bits set to 1 in two's complement)
        // This acts as a neutral identity element for cumulative bitwise AND operations.
        int ans = -1; 
        boolean misplacedFound = false;

        for (int i = 0; i < nums.length; i++) {
            // Identify elements that are out of their correct sorted positions
            if (nums[i] != i) {
                ans &= nums[i];
                misplacedFound = true;
            }
        }

        // If the array was already sorted, return 0 as required by the problem statement.
        return misplacedFound ? ans : 0;
    }
}
