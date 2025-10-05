// There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

// The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

// The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

// Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.

##CODE:
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacificVisited = new boolean[rows][cols];
        boolean[][] atlanticVisited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            dfs(i, 0, heights, pacificVisited, rows, cols);
            dfs(i, cols - 1, heights, atlanticVisited, rows, cols);
        }

        for (int i = 0; i < cols; i++) {
            dfs(0, i, heights, pacificVisited, rows, cols);
            dfs(rows - 1, i, heights, atlanticVisited, rows, cols);
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacificVisited[i][j] && atlanticVisited[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int x, int y, int[][] heights, boolean[][] visited, int rows, int cols) {
        visited[x][y] = true;

        int[][] directions = {
            
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
            
        };

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (isValid(newX, newY, rows, cols) && !visited[newX][newY] 
            && heights[newX][newY] >= heights[x][y]) {
                dfs(newX, newY, heights, visited, rows, cols);
            }
        }
    }

    private boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && y >= 0 && x < rows && y < cols;

    }
}
