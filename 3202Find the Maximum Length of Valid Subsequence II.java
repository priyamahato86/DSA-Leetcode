// You are given an integer array nums and a positive integer k.
// A subsequence sub of nums with length x is called valid if it satisfies:

// (sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k.
// Return the length of the longest valid subsequence of nums.

##CODE:
class Solution {
    public int maximumLength(int[] nums, int k) {
int n = nums.length;
        
        int[][] dp = new int[k][n];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = 1;
            }
        }

        int maxSub = 1;

        //mod = 1
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                int mod = (nums[j] + nums[i]) % k;
                //mod = 0 = 0th row , %2 = 0 waale case ka result
                //mod = 1 = 1st row, %2 = 1 waale case ka result
                dp[mod][i] = Math.max(dp[mod][i], 1 + dp[mod][j]);
                maxSub = Math.max(maxSub, dp[mod][i]);
            }
        }

        return maxSub;
    }
}
