class Solution {
    public String stringHash(String s, int k) {
        int n = s.length();
        int sum = 0;
        String result = "";

        for(int i=0; i<n; i++){
            sum += s.charAt(i) - 'a';

            if((i+1) % k == 0){
                result += (char)('a' + sum % 26);
                sum = 0;
            }
        }
        return result;
    }
}