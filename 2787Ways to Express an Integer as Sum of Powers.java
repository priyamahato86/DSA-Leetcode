// Given two positive integers n and x.

// Return the number of ways n can be expressed as the sum of the xth power of unique positive integers, in other words, the number of sets of unique integers [n1, n2, ..., nk] where n = n1x + n2x + ... + nkx.

// Since the result can be very large, return it modulo 109 + 7.

// For example, if n = 160 and x = 3, one way to express n is n = 23 + 33 + 53.

##CODE:
class Solution {
    private final int M = 1000000007; // 1e9 + 7
    private int[][] t = new int[301][301];

    private int solve(int n, int num, int x) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }

        int currPowerValue = (int) Math.pow(num, x);
        if (currPowerValue > n) {
            return 0;
        }

        if (t[n][num] != -1) {
            return t[n][num];
        }

        int take = solve(n - currPowerValue, num + 1, x);
        int skip = solve(n, num + 1, x);

        return t[n][num] = (take + skip) % M;
    }

    public int numberOfWays(int n, int x) {
        // Fill memoization array with -1
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                t[i][j] = -1;
            }
        }
        return solve(n, 1, x);
    }
}
