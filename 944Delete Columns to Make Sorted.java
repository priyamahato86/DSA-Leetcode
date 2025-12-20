// You are given an array of n strings strs, all of the same length.

// The strings can be arranged such that there is one on each line, making a grid.

// For example, strs = ["abc", "bce", "cae"] can be arranged as follows:
// abc
// bce
// cae
// You want to delete the columns that are not sorted lexicographically. In the above example (0-indexed), columns 0 ('a', 'b', 'c') and 2 ('c', 'e', 'e') are sorted, while column 1 ('b', 'c', 'a') is not, so you would delete column 1.

// Return the number of columns that you will delete.

##CODE:

class Solution {
    public int minDeletionSize(String[] strs) {
         int n = strs.length;
        
        int k = strs[0].length();
        
        int count = 0;
        
        for(int i = 0; i<k; i++) {

            for(int j = 1; j<n; j++) {
                if(strs[j].charAt(i) < strs[j - 1].charAt(i)) {
                    count++;
                    break;
                }
            }
        }
        
        return count;
    }
}
