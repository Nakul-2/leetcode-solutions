import java.util.*;

class Solution {
    private record Result(long weight, List<Integer> selected) {}
    private record Interval(int left, int right, int weight, int originalIndex) {}

    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < intervalsList.size(); ++i) {
            List<Integer> item = intervalsList.get(i);
            intervals.add(new Interval(item.get(0), item.get(1), item.get(2), i));
        }

        // Sort intervals by their left boundary
        intervals.sort(Comparator.comparingInt(Interval::left));

        int n = intervals.size();
        Result[][] memo = new Result[n][5];
        Result res = dp(intervals, memo, 0, 4);

        int[] output = res.selected().stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(output);
        return output;
    }

    private Result dp(List<Interval> intervals, Result[][] memo, int i, int quota) {
        if (i == intervals.size() || quota == 0) {
            return new Result(0, List.of());
        }
        if (memo[i][quota] != null) {
            return memo[i][quota];
        }

        // Option 1: Skip the current interval
        Result skip = dp(intervals, memo, i + 1, quota);

        // Option 2: Take the current interval
        Interval curr = intervals.get(i);
        int nextIdx = findFirstNonOverlapping(intervals, i + 1, curr.right());
        Result takeRes = dp(intervals, memo, nextIdx, quota - 1);

        List<Integer> nextSelected = new ArrayList<>(takeRes.selected);
        nextSelected.add(curr.originalIndex());
        // Sort to maintain correct lexicographical order tracking
        Collections.sort(nextSelected);
        Result take = new Result(curr.weight() + takeRes.weight, nextSelected);

        // Compare results: maximize weight, then minimize lexicographically
        Result best = compareResults(skip, take);
        memo[i][quota] = best;
        return best;
    }

    private int findFirstNonOverlapping(List<Interval> intervals, int low, int targetRight) {
        int high = intervals.size();
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (intervals.get(mid).left() > targetRight) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private Result compareResults(Result r1, Result r2) {
        if (r1.weight != r2.weight) {
            return r1.weight > r2.weight ? r1 : r2;
        }
        // Lexicographical comparison for identical weights
        for (int i = 0; i < Math.min(r1.selected.size(), r2.selected.size()); i++) {
            int cmp = Integer.compare(r1.selected.get(i), r2.selected.get(i));
            if (cmp != 0) {
                return cmp < 0 ? r1 : r2;
            }
        }
        return r1.selected.size() < r2.selected.size() ? r1 : r2;
    }
}
