// There is a game dungeon comprised of n x n rooms arranged in a grid.

// You are given a 2D array fruits of size n x n, where fruits[i][j] represents the number of fruits in the room (i, j). Three children will play in the game dungeon, with initial positions at the corner rooms (0, 0), (0, n - 1), and (n - 1, 0).

// The children will make exactly n - 1 moves according to the following rules to reach the room (n - 1, n - 1):

// The child starting from (0, 0) must move from their current room (i, j) to one of the rooms (i + 1, j + 1), (i + 1, j), and (i, j + 1) if the target room exists.
// The child starting from (0, n - 1) must move from their current room (i, j) to one of the rooms (i + 1, j - 1), (i + 1, j), and (i + 1, j + 1) if the target room exists.
// The child starting from (n - 1, 0) must move from their current room (i, j) to one of the rooms (i - 1, j + 1), (i, j + 1), and (i + 1, j + 1) if the target room exists.
// When a child enters a room, they will collect all the fruits there. If two or more children enter the same room, only one child will collect the fruits, and the room will be emptied after they leave.

// Return the maximum number of fruits the children can collect from the dungeon.

##CODE:
class Solution {
    public int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;
        int[][] t = new int[n][n];
        // t[i][j] = max fruits collected till [i][j]

        // child1Collect - Diagonal elements
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += fruits[i][i];
        }

        // Before child2 and child3, nullify the cells which can't be visited by child2 and child3
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < j && i + j < n - 1) {
                    t[i][j] = 0;
                } else if (i > j && i + j < n - 1) {
                    t[i][j] = 0;
                } else {
                    t[i][j] = fruits[i][j];
                }
            }
        }

        // child2 collect fruits
        // cells upper to diagonal : i < j
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                t[i][j] += Math.max(t[i - 1][j - 1], Math.max(t[i - 1][j], (j + 1 < n) ? t[i - 1][j + 1] : 0));
            }
        }

        // child3 collect fruits
        // cells below diagonal : i > j
        for (int j = 1; j < n; j++) {
            for (int i = j + 1; i < n; i++) {
                t[i][j] += Math.max(t[i - 1][j - 1], Math.max(t[i][j - 1], (i + 1 < n) ? t[i + 1][j - 1] : 0));
            }
        }

        return result + t[n - 2][n - 1] + t[n - 1][n - 2];
    }
}
