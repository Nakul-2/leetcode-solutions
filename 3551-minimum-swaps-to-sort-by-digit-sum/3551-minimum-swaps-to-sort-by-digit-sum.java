class Solution {

    public int DigitSum(int x){
        int sum = 0;

        while(x > 0){
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

    public int minSwaps(int[] nums) {
        Integer[] num2 = new Integer[nums.length];
        for(int i=0; i<nums.length; i++){
            num2[i] = nums[i];
        }

        Arrays.sort(num2 , (a,b) -> {
            int sumA = DigitSum(a);
            int sumB = DigitSum(b);

            if(sumA != sumB){
                return sumA - sumB;
            }

            return a-b;
        });

        HashMap<Integer , Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            map.put(nums[i] , i);
        }

        int swap = 0;

        for(int i=0; i<nums.length; i++){
            if(nums[i] != num2[i]){
              int idx = map.get(num2[i]);
              int temp = nums[i];
              nums[i] = nums[idx];
              nums[idx] = temp;

              swap++;

              map.put(nums[i] , i);
              map.put(nums[idx], idx);
            }else{
                continue;
            }
        }

        return swap;
    }
}