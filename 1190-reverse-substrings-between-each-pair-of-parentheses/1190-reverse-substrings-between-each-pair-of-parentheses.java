class Solution {
    public String reverseParentheses(String s) {

        Stack<String> st = new Stack<>();
        StringBuilder curr = new StringBuilder();

        for(char ch : s.toCharArray()) {

            if(ch == '(') {
                st.push(curr.toString());
                curr = new StringBuilder();
            }

            else if(ch == ')') {

                curr.reverse();

                String previous = st.pop();

                curr = new StringBuilder(previous + curr);
            }

            else {
                curr.append(ch);
            }
        }

        return curr.toString();
    }
}