class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;
        
        // Find the index of 0
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }
        
        boolean inc = true;
        boolean dec = true;
        
        // Validate cyclic order
        for (int i = 0; i < n; i++) {
            int expectedInc = i;
            int actualInc = nums[(zero + i) % n];
            if (actualInc != expectedInc) {
                inc = false;
            }
            
            int expectedDec = (n - i) % n;
            int actualDec = nums[(zero + i) % n];
            if (actualDec != expectedDec) {
                dec = false;
            }
        }
        
        int ans = Integer.MAX_VALUE;
        
        // Scenario 1: Elements are cyclically increasing
        if (inc) {
            ans = Math.min(ans, zero);         // Pure rotations
            ans = Math.min(ans, n - zero + 2); // Reverse + rotate + reverse
        }
        
        // Scenario 2: Elements are cyclically decreasing
        if (dec) {
            ans = Math.min(ans, zero + 2);     // Rotate + reverse + reverse
            ans = Math.min(ans, n - zero);     // Reverse + rotate
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
