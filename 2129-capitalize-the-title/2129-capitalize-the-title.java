class Solution {
    public String capitalizeTitle(String title) {
        
        char[] arr = title.toCharArray();
        int start = 0;
        for(int i=0; i<=arr.length; i++){
            if(i == arr.length || arr[i] == ' '){
               if(i - start > 2){
                arr[start] = (char)(arr[start] - 32);
               }
               start = i+1;
            }else if(arr[start] >= 'A' && arr[i] <= 'Z'){
                arr[i] = (char)(arr[i] + 32);
            }
        }
        return new String(arr);

    }
}