class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        StringBuilder result = new StringBuilder("");

        Stack<Integer> st = new Stack<>();
       for(char ch : s.toCharArray()){
        if(ch == '('){
            st.push(result.length());
        }else if(ch == ')'){
            int l = st.pop();
            reverse(result , l , result.length()-1);
        }else{ 
            result.append(ch);
        }
       }
       return result.toString();
    }

    private void reverse(StringBuilder result , int start ,int end){
        while(start <= end){
            char temp = result.charAt(start);
            result.setCharAt(start++ , result.charAt(end));
            result.setCharAt(end-- , temp);
        }
    }
}