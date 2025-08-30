// Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

// Each row must contain the digits 1-9 without repetition.
// Each column must contain the digits 1-9 without repetition.
// Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
// Note:

// A Sudoku board (partially filled) could be valid but is not necessarily solvable.
// Only the filled cells need to be validated according to the mentioned rules.

##CODE:
class Solution {
    public boolean isValidSudoku(char[][] board) {
       Set<String> set = new HashSet<>();

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];

                if (ch == '.') {continue;}

                String row = "ROW_" + r + "_" + ch;
                String column = "COLUMN_" + c + "_" + ch;
                String grid = "GRID_" + r/3 + "_" + c/3 + "_" + ch;

                if (set.contains(row) || set.contains(column) || set.contains(grid)) {
                    return false;
                }

                set.add(row);
                set.add(column);
                set.add(grid);
            }
        }
        return true;
    }
}
