// You are given a 2D array points of size n x 2 representing integer coordinates of some points on a 2D plane, where points[i] = [xi, yi].

// Count the number of pairs of points (A, B), where

// A is on the upper left side of B, and
// there are no other points in the rectangle (or line) they make (including the border).
// Return the count.

##CODE:
class Solution {
    public int numberOfPairs(int[][] points) {
        int n = points.length;

        // Sort: x ascending, if x same then sort as y descending
        Arrays.sort(points, (point1, point2) -> {
            if (point1[0] == point2[0]) {
                return point2[1] - point1[1];
            }
            return point1[0] - point2[0];
        });

        int result = 0;

        for (int i = 0; i < n; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];   // upper left

            int bestY = Integer.MIN_VALUE;

            for (int j = i + 1; j < n; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];   // lower right

                // Condition: (x2, y2) must be above (x1, y1)
                if (y2 > y1) { //not lower right
                    continue;
                }

                if (y2 > bestY) {
                    result++;
                    bestY = y2;
                }
            }
        }

        return result;
    }
}
