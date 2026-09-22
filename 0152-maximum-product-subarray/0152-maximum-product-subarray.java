class Solution {
    public int maxProduct(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int ms = Integer.MIN_VALUE;
        int cs = 1;
        for(int i=0; i<nums.length; i++){
            cs *= nums[i];
            ms = Math.max(cs,ms);
            
            if(cs == 0){
                cs = 1;
            }
        }
        int j = Integer.MIN_VALUE;
        int x = 1;
        for(int i=nums.length-1; i>0; i--){
            x *= nums[i];
            j = Math.max(x,j);
            
            if(x == 0){
                x = 1;
            }
        }
        return Math.max(ms,j);
    }
}