class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
      int n = nums.length;
      List<List<Integer>> result = new ArrayList<>();
      
      if( n < 3){
        return result;
      }

      Arrays.sort(nums);
      for(int i=0; i<n; i++){
        if(i>0 && nums[i] == nums[i-1]){
            continue;
        }

        int n1 = nums[i];
        int target = -n1;
        int left = i+1;
        int right = n-1;
        while( left < right){
           if(nums[left] + nums[right] > target){
            right--;
           }else if(nums[left] + nums[right] < target){
            left++;
           }else{
             while(left<right && nums[left] == nums[left + 1]){
                left++;
             }
             while(left<right && nums[right] == nums[right - 1]){
                right--;
             }

             result.add(Arrays.asList(-target , nums[left] , nums[right]));
             left++;
             right--;   
           }

        } 
     
    }
     return result;
  }
}