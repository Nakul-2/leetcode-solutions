class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int lessCount = 0;
        int targetCount = 0;
        
        for (int num : nums) {
            if (num < target) {
                lessCount++;
            } else if (num == target) {
                targetCount++;
            }
        }
        
        List<Integer> list = new ArrayList<>(targetCount);
        for (int i = 0; i < targetCount; i++) {
            list.add(lessCount + i);
        }
        
        return list;
    }
}