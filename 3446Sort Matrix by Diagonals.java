// You are given an n x n square matrix of integers grid. Return the matrix such that:

// The diagonals in the bottom-left triangle (including the middle diagonal) are sorted in non-increasing order.
// The diagonals in the top-right triangle are sorted in non-decreasing order.

##CODE:
class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;

        Map<Integer, List<Integer>> mp = new HashMap<>();

        // Collect diagonal elements (i - j as the key)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int diag = i - j;
                mp.computeIfAbsent(diag, k -> new ArrayList<>()).add(grid[i][j]);
            }
        }

        // Sort diagonals: non-decreasing for diag >= 0, non-increasing for diag < 0
        for (Map.Entry<Integer, List<Integer>> entry : mp.entrySet()) {
            List<Integer> list = entry.getValue();
            if (entry.getKey() >= 0) {
                Collections.sort(list); // ascending
            } else {
                list.sort(Collections.reverseOrder()); // descending
            }
        }

        // Fill grid back using elements from the map
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int diag = i - j;
                List<Integer> list = mp.get(diag);
                grid[i][j] = list.remove(list.size() - 1); // take from back
            }
        }

        return grid;
    }
}
