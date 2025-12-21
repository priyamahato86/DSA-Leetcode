// You are given an array of n strings strs, all of the same length.

// We may choose any deletion indices, and we delete all the characters in those indices for each string.

// For example, if we have strs = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef", "vyz"].

// Suppose we chose a set of deletion indices answer such that after deletions, the final array has its elements in lexicographic order (i.e., strs[0] <= strs[1] <= strs[2] <= ... <= strs[n - 1]). Return the minimum possible value of answer.length.

##CODE:

class Solution {
    public int minDeletionSize(String[] strs) {
 int rows = strs.length;          
        int cols = strs[0].length();     

        int deletion = 0;
        boolean[] alreadySorted = new boolean[rows];

        for (int col = 0; col < cols; col++) {
            boolean deleted = false;

  
            for (int row = 0; row < rows - 1; row++) {
                if (!alreadySorted[row] &&
                    strs[row].charAt(col) > strs[row + 1].charAt(col)) {
                    deletion++;
                    deleted = true;
                    break;
                }
            }

            if (deleted) {
                continue;
            }

            
            for (int i = 0; i < rows - 1; i++) {
                alreadySorted[i] = alreadySorted[i] ||
                        (strs[i].charAt(col) < strs[i + 1].charAt(col));
            }
        }

        return deletion;
    }
}
