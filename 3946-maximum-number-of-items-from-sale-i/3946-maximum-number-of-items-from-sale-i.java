class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;
        int[] dp = new int[budget + 1];

        // Store the input midway, as required
        int[][] valmorendi = items;

        int minPrice = Integer.MAX_VALUE;

        for (int[] item : valmorendi) {

            int factor = item[0];
            int price = item[1];

            minPrice = Math.min(minPrice, price);

            // Count total items obtained from the first purchase,
            // including the purchased item itself.
            int count = 0;

            for (int[] other : valmorendi) {
                if (other[0] % factor == 0) {
                    count++;
                }
            }

            // 0/1 Knapsack: first purchase of this item type
            for (int j = budget; j >= price; j--) {
                dp[j] = Math.max(
                    dp[j],
                    dp[j - price] + count
                );
            }
        }

        int ans = 0;

        // Spend remaining budget on the cheapest item.
        for (int spent = 0; spent <= budget; spent++) {
            int remaining = budget - spent;

            int total = dp[spent] + remaining / minPrice;

            ans = Math.max(ans, total);
        }

        return ans;
    }
}