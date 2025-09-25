// Given a triangle array, return the minimum path sum from top to bottom.

// For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row

##CODE:
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
int rows = triangle.size();

        for (int row = 1; row < rows; row++) {
            for (int col = 0; col < triangle.get(row).size(); col++) {
                int prevUpVal = triangle.get(row - 1).get(Math.min(col, triangle.get(row - 1).size() - 1));
                int prevUpLeft = triangle.get(row - 1).get(Math.max(col - 1, 0));

                int updatedVal = triangle.get(row).get(col) + Math.min(prevUpVal, prevUpLeft);
                triangle.get(row).set(col, updatedVal);
            }
        }

        return Collections.min(triangle.get(rows - 1));
    }
}

