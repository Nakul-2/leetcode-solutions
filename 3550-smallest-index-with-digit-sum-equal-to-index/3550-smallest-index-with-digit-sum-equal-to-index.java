class Solution {
    public int smallestIndex(int[] nums) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<nums.length; i++){
            int n = nums[i];
            while( n > 0){
                int x = Math.abs(n % 10);
                sum += x;
                n = n / 10;
            }
            if( sum == i){
                min = Math.min(min,i);
            }
            sum = 0;
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}