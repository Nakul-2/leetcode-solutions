class Solution {
    public int minMoves(int[] nums) {
        int maxVal = nums[0];
        long sum = 0;
        
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            sum += num;
        }
        
        return (maxVal * nums.length) - (int) sum;
    }
}
