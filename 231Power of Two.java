// Given an integer n, return true if it is a power of two. Otherwise, return false.

// An integer n is a power of two, if there exists an integer x such that n == 2x.

##CODE:
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0)
            return false;

        return Integer.bitCount(n) == 1;
    }
}
