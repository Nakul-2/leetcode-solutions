class Solution {
    public int lengthOfLongestSubstring(String s) {
        int l = 0, r = 0 , maxLen = 0;
        char[] arr = s.toCharArray();
        int[] hash = new int[256];
        Arrays.fill(hash , -1);
        while(r < arr.length){
            if(hash[arr[r]] != -1){
                if(hash[arr[r]] >= l){
                    l = hash[arr[r]] + 1;
                }
            }
            int len = r-l+1;
            maxLen = Math.max(len , maxLen);
            hash[arr[r]] = r;
            r++;
        }
        return maxLen;
    }
}