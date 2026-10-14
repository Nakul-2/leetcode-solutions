class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        if (num == 1) {
            if (sum >= 0 && sum <= 9) {
                return String.valueOf(sum);
            }
            return "";
        }

        while (sum > 0 && num > 0) {
            if (sum >= 9) {
                ans.append(9);
                sum -= 9;
            } else {
                ans.append(sum);
                sum = 0;
            }
            num--;
        }

        while (num > 0) {
            ans.append(0);
            num--;
        }

        return ans.toString();
    }
}