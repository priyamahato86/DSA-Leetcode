// You are given a 2D integer matrix grid of size n x m, where each element is either 0, 1, or 2.

// A V-shaped diagonal segment is defined as:

// The segment starts with 1.
// The subsequent elements follow this infinite sequence: 2, 0, 2, 0, ....
// The segment:
// Starts along a diagonal direction (top-left to bottom-right, bottom-right to top-left, top-right to bottom-left, or bottom-left to top-right).
// Continues the sequence in the same diagonal direction.
// Makes at most one clockwise 90-degree turn to another diagonal direction while maintaining the sequence

##CODE:
class Solution {
    private int n;
    private int m; 
    private int[][] dir = {
        {1, 1}, {1, -1}, {-1, -1}, {-1, 1}
    };


    public int lenOfVDiagonal(int[][] grid) {
        n = grid.length;
        m = grid[0].length;

        Integer[][][][] dp = new Integer[n][m][4][2];

        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    for (int d = 0; d < 4; d++) {
                        maxLength = Math.max(maxLength, 1 + dfs(grid, i, j, 2, d, true, dp));
                    }
                }
            }
        }

        return maxLength;
    }

    private boolean isValid(int[][] grid, int i, int j, int expected) {
        return i >= 0 && i < n && j >= 0 && j < m && grid[i][j] == expected;
    }

    private int dfs(int[][] grid, int i, int j, int expected, int d, boolean canTurn, Integer[][][][] dp) {
        int newI = i + dir[d][0];
        int newJ = j + dir[d][1];

        if (!isValid(grid, newI, newJ, expected)) {
            return 0;
        }

        int cIndex = canTurn ? 1 : 0;

        if (dp[newI][newJ][d][cIndex] != null) {
            return dp[newI][newJ][d][cIndex];
        }

        int nextExpected = expected == 2 ? 0 : 2;

        int maxLength = dfs(grid, newI, newJ, nextExpected, d, canTurn, dp);

        if (canTurn) {
            int newDir = (d + 1) % 4;
            maxLength = Math.max(maxLength, dfs(grid, newI, newJ, nextExpected, newDir, false, dp));
        }

        dp[newI][newJ][d][cIndex] = 1 + maxLength;
        return dp[newI][newJ][d][cIndex];
    }
}
