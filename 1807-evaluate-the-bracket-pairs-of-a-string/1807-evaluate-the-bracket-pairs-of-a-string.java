class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        int n = s.length();

        HashMap<String , String> map = new HashMap<>();
        for(int i=0; i<knowledge.size(); i++){
           map.put(knowledge.get(i).get(0) , knowledge.get(i).get(1));
        }

        int i = 0;
        int j = 0;
        StringBuilder result = new StringBuilder(); 

        while( i < n){
            if(s.charAt(i) == '('){
                j = i + 1;
              while(s.charAt(j) != ')'){
                j++;
              }
              String temp = s.substring( i+1 , j );
              if(map.containsKey(temp)){
                result.append(map.get(temp));
              }else{
                result.append('?');
              }
              i = j;
            }else{
                result.append(s.charAt(i));
            }
            i++;
        } 

        return result.toString();
    }
}