import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;
        
        int maxPoints = 0;
        
        // Iterate through each point as an anchor/pivot point
        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopeMap = new HashMap<>();
            int duplicate = 1; // Tracks the anchor point itself
            int currentMax = 0;
            
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                
                // Handle duplicate points if the problem constraints allow them
                if (dx == 0 && dy == 0) {
                    duplicate++;
                    continue;
                }
                
                // Simplify the slope fraction using GCD
                int gcd = gcd(dx, dy);
                dx /= gcd;
                dy /= gcd;
                
                // Standardize the sign to prevent tracking (-1, -2) and (1, 2) separately
                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                } else if (dx == 0) {
                    // Vertical line: ensure dy is positive for consistent keys
                    dy = Math.abs(dy);
                }
                
                // Generate a unique string key for the fraction
                String slopeKey = dy + "/" + dx;
                slopeMap.put(slopeKey, slopeMap.getOrDefault(slopeKey, 0) + 1);
                currentMax = Math.max(currentMax, slopeMap.get(slopeKey));
            }
            
            // Total points on the line from the current anchor
            maxPoints = Math.max(maxPoints, currentMax + duplicate);
        }
        
        return maxPoints;
    }
    
    // Euclidean algorithm to find the Greatest Common Divisor
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
