class Solution {
    public int findMin(int[] nums) {
        int ms = Integer.MAX_VALUE;
        int mid;
        int low = 0;
        int high = nums.length-1;
        while( low <= high){
            mid = (low + high) / 2;

            if(nums[low] <= nums[mid]){
                ms = Math.min(ms , nums[low]);
                low = mid + 1;
            }else{
                ms = Math.min(ms , nums[mid]);
                high = mid - 1;
            }
        }

        return ms;
    }
}