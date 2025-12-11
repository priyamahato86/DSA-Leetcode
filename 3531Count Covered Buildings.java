// You are given a positive integer n, representing an n x n city. You are also given a 2D grid buildings, where buildings[i] = [x, y] denotes a unique building located at coordinates [x, y].

// A building is covered if there is at least one building in all four directions: left, right, above, and below.

// Return the number of covered buildings.

##CODE:
class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
         Map<Integer, int[]> yToMinMaxX = new HashMap<>();
        Map<Integer, int[]> xToMinMaxY = new HashMap<>();

        for (int[] building : buildings) {
            int x = building[0];
            int y = building[1];

            yToMinMaxX.putIfAbsent(y, new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
            xToMinMaxY.putIfAbsent(x, new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});

            int[] xr = yToMinMaxX.get(y);
            xr[0] = Math.min(xr[0], x);
            xr[1] = Math.max(xr[1], x);

            int[] yr = xToMinMaxY.get(x);
            yr[0] = Math.min(yr[0], y);
            yr[1] = Math.max(yr[1], y);
        }

        int result = 0;

        for (int[] building : buildings) {
            int x = building[0];
            int y = building[1];

            int[] xr = yToMinMaxX.get(y);
            int[] yr = xToMinMaxY.get(x);

            if (xr[0] < x && x < xr[1] &&
                yr[0] < y && y < yr[1]) {
                result++;
            }
        }

        return result;
    }
}
