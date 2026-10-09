class Solution {
    public int minInsertions(String s) {
      int count = 0;
      int result = 0;
      int i = 0;

      while(i < s.length()){
        if(s.charAt(i) == '('){
            count ++;
            i++;
        }else{
            if(count > 0){
                count--;
            }else{
              result++;
            }

            if( i+1 < s.length() && s.charAt(i+1) == ')'){
                i += 2;
            }else{
                result++;
                i++;
            }
        }
      }

      if(count != 0){
        result = result + count * 2;
      }

      return result;
    }
}