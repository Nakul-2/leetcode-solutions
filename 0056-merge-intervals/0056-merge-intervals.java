class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals , Comparator.comparingInt(o->o[0]));
        int start = intervals[0][0];
        int end = intervals[0][1];

        ArrayList<int[]> list = new ArrayList<>();
        for(int i=1; i<n; i++){
            int s = intervals[i][0];
            int e = intervals[i][1];

            if(s <= end){
               end = Math.max(end , e);
            }else{
                list.add(new int[]{start,end});
                start = s;
                end = e;
            }

        }
        list.add(new int[]{start,end});
        return list.toArray(new int[list.size()][]);
    }
}