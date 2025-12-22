// You are given an array of n strings strs, all of the same length.

// We may choose any deletion indices, and we delete all the characters in those indices for each string.

// For example, if we have strs = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef", "vyz"].

// Suppose we chose a set of deletion indices answer such that after deletions, the final array has every string (row) in lexicographic order. (i.e., (strs[0][0] <= strs[0][1] <= ... <= strs[0][strs[0].length - 1]), and (strs[1][0] <= strs[1][1] <= ... <= strs[1][strs[1].length - 1]), and so on). Return the minimum possible value of answer.length.



##CODE:

class Solution {
    public int minDeletionSize(String[] strs) {
int rows = strs.length;
        int cols = strs[0].length();

        int[] dp = new int[cols];
    

        int LIS = 1;

        for (int i = 0; i < cols; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {

                boolean valid = true;
                for (int k = 0; k < rows; k++) {
                    if (strs[k].charAt(j) > strs[k].charAt(i)) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            LIS = Math.max(LIS, dp[i]);
        }

        return cols - LIS;
    }
}
