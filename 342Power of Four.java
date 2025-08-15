// Given an integer n, return true if it is a power of four. Otherwise, return false.

// An integer n is a power of four, if there exists an integer x such that n == 4x.

##CODE:
class Solution {
    public boolean isPowerOfFour(int n) {
         if (n <= 0) {
            return false;
        }
        
        int a = (int) (Math.log(n) / Math.log(4));
        /*
            4^a = n
            a = log(n) to base 4
            a = log(n) to base e / log(4) to base e
        */
        
        return n == Math.pow(4, a);
    }
}
