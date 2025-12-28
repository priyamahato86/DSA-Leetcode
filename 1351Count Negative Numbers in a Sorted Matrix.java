// Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.

##CODE:

class Solution {
    public int countNegatives(int[][] grid) {
int m = grid.length, n = grid[0].length;
        int row = 0, col = n - 1;
        int count = 0;

        while (row < m && col >= 0) {
            if (grid[row][col] < 0) {
                count += (m - row);
                col--;
            } else {
                row++;
            }
        }
        return count;
    }
}

 
