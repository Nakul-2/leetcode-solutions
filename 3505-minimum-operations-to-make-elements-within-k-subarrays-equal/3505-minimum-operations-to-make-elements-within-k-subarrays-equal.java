import java.util.*;

class Solution {

    class DualSet {
        TreeMap<Integer, Integer> low = new TreeMap<>();
        TreeMap<Integer, Integer> high = new TreeMap<>();

        int lowSize = 0, highSize = 0;
        long lowSum = 0, highSum = 0;

        void addOne(TreeMap<Integer, Integer> map, int x) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        void removeOne(TreeMap<Integer, Integer> map, int x) {
            if (map.get(x) == 1) {
                map.remove(x);
            } else {
                map.put(x, map.get(x) - 1);
            }
        }

        void add(int x) {
            if (lowSize == 0 || x <= low.lastKey()) {
                addOne(low, x);
                lowSize++;
                lowSum += x;
            } else {
                addOne(high, x);
                highSize++;
                highSum += x;
            }

            balance();
        }

        void remove(int x) {
            if (low.containsKey(x)) {
                removeOne(low, x);
                lowSize--;
                lowSum -= x;
            } else {
                removeOne(high, x);
                highSize--;
                highSum -= x;
            }

            balance();
        }

        void balance() {
            while (lowSize > highSize + 1) {
                int x = low.lastKey();

                removeOne(low, x);
                lowSize--;
                lowSum -= x;

                addOne(high, x);
                highSize++;
                highSum += x;
            }

            while (lowSize < highSize) {
                int x = high.firstKey();

                removeOne(high, x);
                highSize--;
                highSum -= x;

                addOne(low, x);
                lowSize++;
                lowSum += x;
            }
        }

        long getCost() {
            int median = low.lastKey();

            long left = (long) median * lowSize - lowSum;
            long right = highSum - (long) median * highSize;

            return left + right;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Step 1: Calculate cost of every window
        long[] cost = new long[n - x + 1];

        DualSet ds = new DualSet();

        for (int i = 0; i < n; i++) {

            ds.add(nums[i]);

            if (i >= x - 1) {

                int start = i - x + 1;

                cost[start] = ds.getCost();

                ds.remove(nums[start]);
            }
        }

        // Step 2: Dynamic Programming
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], INF);
        }

        // Zero windows require zero operations
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        // Select exactly j non-overlapping windows
        for (int j = 1; j <= k; j++) {

            for (int i = 1; i <= n; i++) {

                // Choice 1: Skip current position
                dp[j][i] = dp[j][i - 1];

                // Choice 2: Select a window ending at i-1
                if (i >= x) {

                    long previous = dp[j - 1][i - x];

                    if (previous != INF) {
                        dp[j][i] = Math.min(
                            dp[j][i],
                            previous + cost[i - x]
                        );
                    }
                }
            }
        }

        return dp[k][n];
    }
}