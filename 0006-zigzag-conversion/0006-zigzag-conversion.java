class Solution {
    public String convert(String s, int numRows) {
        if(s.length() == 1 || numRows >= s.length() || numRows == 1){
            return s;
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<numRows; i++){
            int idx = i;
            int south = 2*(numRows-1-i);
            int north = 2*(i);
            boolean goingSouth = true;

            while(idx < s.length()){
                sb.append(s.charAt(idx));

                if(i == 0){
                  idx = idx + south;
                }else if(i == numRows-1){
                    idx = idx + north;
                }else{
                    if(goingSouth){
                        idx = idx + south;
                    }else{
                        idx = idx + north;
                    }
                    goingSouth = !goingSouth;
                }
            }
        } 

        return sb.toString();   
    }
}