class Solution {
    int n;
    public int solve(int nums[] , int target){
        int left = 0;
        int len = -1;
        int sum = 0;
        for(int right = 0; right<n; right++){
            sum += nums[right];
             while(left <= right && sum > target){
                sum -= nums[left++];
             }

             if(sum == target){
                len = Math.max(len , right - left + 1);
             }
        }
        return len;
    }
    public int minOperations(int[] nums, int x) {
         n = nums.length;
        int TotalSum = 0;
        for(int i=0; i<n; i++){
            TotalSum += nums[i];
        }

        int target = TotalSum - x;
        if(target < 0) return -1;
        if(target == 0) return n;

        int ans = solve(nums , target);
        return ans == -1 ? ans : n - ans;
    }
}