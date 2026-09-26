class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        ArrayList<int[]>[] graph = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int cost = flight[2];

            graph[from].add(new int[]{to, cost});
        }

        Queue<int[]> q = new LinkedList<>();

        // {city, cost, stops}
        q.add(new int[]{src, 0, 0});

        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[src] = 0;

        while(!q.isEmpty()) {

            int[] current = q.remove();

            int city = current[0];
            int cost = current[1];
            int stops = current[2];

            // We can take at most k stops = k+1 flights
            if(stops > k) {
                continue;
            }

            for(int[] next : graph[city]) {

                int nextCity = next[0];
                int flightCost = next[1];

                int newCost = cost + flightCost;

                if(newCost < minCost[nextCity]) {

                    minCost[nextCity] = newCost;

                    q.add(new int[]{
                        nextCity,
                        newCost,
                        stops + 1
                    });
                }
            }
        }

        return minCost[dst] == Integer.MAX_VALUE ? -1 : minCost[dst];
    }
}