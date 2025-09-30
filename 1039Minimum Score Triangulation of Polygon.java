// You have a convex n-sided polygon where each vertex has an integer value. You are given an integer array values where values[i] is the value of the ith vertex in clockwise order.

// Polygon triangulation is a process where you divide a polygon into a set of triangles and the vertices of each triangle must also be vertices of the original polygon. Note that no other shapes other than triangles are allowed in the division. This process will result in n - 2 triangles.

// You will triangulate the polygon. For each triangle, the weight of that triangle is the product of the values at its vertices. The total score of the triangulation is the sum of these weights over all n - 2 triangles.

// Return the minimum possible score that you can achieve with some triangulation of the polygon.

##CODE:
class Solution {
        private int[][] dp;

    private int solve(int[] values, int i, int j) {
        // Need at least 3 points to form a triangle
        if (j - i + 1 < 3) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int result = Integer.MAX_VALUE;

        for (int k = i + 1; k < j; k++) {
            int wt = values[i] * values[k] * values[j]
                     + solve(values, i, k)
                     + solve(values, k, j);

            result = Math.min(result, wt);
        }

        dp[i][j] = result;
        return result;
    }

    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        dp = new int[n][n];

        // Initialize dp with -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return solve(values, 0, n - 1);

    }
}
