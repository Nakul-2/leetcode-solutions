import java.util.*;

class Solution {

    static final long MOD1 = 1_000_000_007L;
    static final long MOD2 = 1_000_000_009L;

    static final long BASE1 = 911382323L;
    static final long BASE2 = 972663749L;

    public int smallestUniqueSubarray(int[] nums) {

        int n = nums.length;

        long left = 1;
        long right = n;

        while (left < right) {

            int mid = (int) (left + (right - left) / 2);

            if (hasUniqueSubarray(nums, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return (int) left;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {

        int n = nums.length;

        long power1 = 1;
        long power2 = 1;

        for (int i = 0; i < len; i++) {
            power1 = (power1 * BASE1) % MOD1;
            power2 = (power2 * BASE2) % MOD2;
        }

        long hash1 = 0;
        long hash2 = 0;

        // Calculate hash of the first window.
        for (int i = 0; i < len; i++) {
            long value = nums[i] + 1L;

            hash1 = (hash1 * BASE1 + value) % MOD1;
            hash2 = (hash2 * BASE2 + value) % MOD2;
        }

        HashMap<Long, Integer> map = new HashMap<>();

        addHash(map, hash1, hash2);

        // Slide the window.
        for (int i = len; i < n; i++) {

            long outgoing = nums[i - len] + 1L;
            long incoming = nums[i] + 1L;

            hash1 = (hash1 * BASE1
                    - outgoing * power1 % MOD1
                    + incoming + MOD1) % MOD1;

            hash2 = (hash2 * BASE2
                    - outgoing * power2 % MOD2
                    + incoming + MOD2) % MOD2;

            addHash(map, hash1, hash2);
        }

        // Check whether any hash occurs exactly once.
        for (int count : map.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }

    private void addHash(HashMap<Long, Integer> map,
                         long hash1, long hash2) {

        long key = hash1 * MOD2 + hash2;

        map.put(key, map.getOrDefault(key, 0) + 1);
    }
}