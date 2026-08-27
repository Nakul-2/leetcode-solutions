class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        int[] count = new int[26];

        // Frequency of characters in s
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        // Match target from left to right as much as possible
        int i = 0;

        while (i < n) {

            int idx = target.charAt(i) - 'a';

            if (count[idx] == 0) {
                break;
            }

            count[idx]--;
            i++;
        }

        while (true) {

            // Try to make position i greater than target[i]
            if (i < n) {

                int targetIdx = target.charAt(i) - 'a';

                for (int c = targetIdx + 1; c < 26; c++) {

                    if (count[c] > 0) {

                        StringBuilder ans = new StringBuilder();

                        // Prefix remains exactly the same
                        for (int j = 0; j < i; j++) {
                            ans.append(target.charAt(j));
                        }

                        // Make this position greater
                        ans.append((char) ('a' + c));

                        count[c]--;

                        // Put remaining characters in sorted order
                        for (int j = 0; j < 26; j++) {

                            while (count[j] > 0) {
                                ans.append((char) ('a' + j));
                                count[j]--;
                            }
                        }

                        return ans.toString();
                    }
                }
            }

            // Cannot make current position greater.
            // Go one position back.
            if (i == 0) {
                break;
            }

            i--;

            // Restore target[i]
            count[target.charAt(i) - 'a']++;
        }

        return "";
    }
}