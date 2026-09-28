class Solution {
    public int maxDepth(String s) {
        int currDept = 0;
        int maxDept = -1;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                 currDept++;
                maxDept = Math.max(currDept , maxDept);
            }else if(s.charAt(i) == ')'){
                currDept--;
            }
        }

        if(maxDept == -1){
            return 0;
        }else{
            return maxDept;
        }
    }
}