class Solution {
    public int lengthOfLongestSubstring(String s) {

        HashMap<Character, Integer> map = new HashMap<>();

        int l = 0;
        int r = 0;
        int maxLen = 0;

        while (r < s.length()) {

            char ch = s.charAt(r);

            if (map.containsKey(ch)) {
                if (map.get(ch) >= l) {
                    l = map.get(ch) + 1;
                }
            }

            int len = r - l + 1;
            maxLen = Math.max(maxLen, len);

            map.put(ch, r);

            r++;
        }

        return maxLen;
    }
}