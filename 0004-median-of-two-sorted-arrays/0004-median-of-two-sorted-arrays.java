class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // Always binary search on the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int low = 0;
        int high = m;

        while (low <= high) {

            int cut1 = (low + high) / 2;
            int cut2 = (m + n + 1) / 2 - cut1;

            int left1;
            int right1;
            int left2;
            int right2;

            if (cut1 == 0) {
                left1 = Integer.MIN_VALUE;
            } else {
                left1 = nums1[cut1 - 1];
            }

            if (cut1 == m) {
                right1 = Integer.MAX_VALUE;
            } else {
                right1 = nums1[cut1];
            }

            if (cut2 == 0) {
                left2 = Integer.MIN_VALUE;
            } else {
                left2 = nums2[cut2 - 1];
            }

            if (cut2 == n) {
                right2 = Integer.MAX_VALUE;
            } else {
                right2 = nums2[cut2];
            }

            // Correct partition
            if (left1 <= right2 && left2 <= right1) {

                int maxLeft = Math.max(left1, left2);
                int minRight = Math.min(right1, right2);

                // Odd total length
                if ((m + n) % 2 != 0) {
                    return maxLeft;
                }

                // Even total length
                return (maxLeft + minRight) / 2.0;
            }

            // nums1 partition is too far right
            else if (left1 > right2) {
                high = cut1 - 1;
            }

            // nums1 partition is too far left
            else {
                low = cut1 + 1;
            }
        }

        return 0.0;
    }
}