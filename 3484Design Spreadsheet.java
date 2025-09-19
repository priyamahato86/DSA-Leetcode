// A spreadsheet is a grid with 26 columns (labeled from 'A' to 'Z') and a given number of rows. Each cell in the spreadsheet can hold an integer value between 0 and 105.

// Implement the Spreadsheet class:

// Spreadsheet(int rows) Initializes a spreadsheet with 26 columns (labeled 'A' to 'Z') and the specified number of rows. All cells are initially set to 0.
// void setCell(String cell, int value) Sets the value of the specified cell. The cell reference is provided in the format "AX" (e.g., "A1", "B10"), where the letter represents the column (from 'A' to 'Z') and the number represents a 1-indexed row.
// void resetCell(String cell) Resets the specified cell to 0.
// int getValue(String formula) Evaluates a formula of the form "=X+Y", where X and Y are either cell references or non-negative integers, and returns the computed sum.
// Note: If getValue references a cell that has not been explicitly set using setCell, its value is considered 0.

##CODE:
class Spreadsheet {
     private int[][] sheet;
    public Spreadsheet(int rows) {
        sheet = new int[rows][26];
    }
    
    public void setCell(String cell, int value) {
        int col = cell.charAt(0) - 'A';
        int row = Integer.parseInt(cell.substring(1)) - 1;
        sheet[row][col] = value;
    }
    
    public void resetCell(String cell) {
        int col = cell.charAt(0) - 'A';
        int row = Integer.parseInt(cell.substring(1)) - 1;
        sheet[row][col] = 0;
    }
    private int solve(String s) {
        if (Character.isDigit(s.charAt(0))) {
            return Integer.parseInt(s);
        }
        int col = s.charAt(0) - 'A';
        int row = Integer.parseInt(s.substring(1)) - 1;
        return sheet[row][col];
    }
    
    public int getValue(String formula) {
         String s = formula.substring(1); // remove '='
        int plusIdx = s.indexOf('+');

        String left = s.substring(0, plusIdx);
        String right = s.substring(plusIdx + 1);

        return solve(left) + solve(right);
    }
}
