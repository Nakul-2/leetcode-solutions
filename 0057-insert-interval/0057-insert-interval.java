class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        int n = intervals.length;
        ArrayList<int[]> list = new ArrayList<>();

        int start = newInterval[0];
        int end = newInterval[1];

        int i = 0;

        //Intervals before newInterval
        while(i < n && intervals[i][1] < start) {
            list.add(intervals[i]);
            i++;
        }

        // Overlapping intervals
        while(i < n && intervals[i][0] <= end) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }

        // Add merged newInterval
        list.add(new int[]{start, end});

        //Intervals after newInterval
        while(i < n) {
            list.add(intervals[i]);
            i++;
        }

        return list.toArray(new int[list.size()][]);
    }
}