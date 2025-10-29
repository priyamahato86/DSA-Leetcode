// You are given a positive number n.

// Return the smallest number x greater than or equal to n, such that the binary representation of x contains only set bits

##CODE:
class Solution {
    public int smallestNumber(int n) {
        int bits = (int)(Math.log(n) / Math.log(2)) + 1;
        return (1 << bits) - 1;
    }
}
