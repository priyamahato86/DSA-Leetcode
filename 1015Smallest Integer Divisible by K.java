// Given a positive integer k, you need to find the length of the smallest positive integer n such that n is divisible by k, and n only contains the digit 1.

// Return the length of n. If there is no such n, return -1.

// Note: n may not fit in a 64-bit signed integer.

##CODE:

class Solution {
    public int smallestRepunitDivByK(int k) {
            if (k == 1)
            return 1;

        
        if (k % 2 == 0 || k % 5 == 0)
            return -1;

        int remain = 0;

        for (int length = 1; length <= k; length++) {
            remain = (remain * 10 + 1) % k;

            if (remain == 0)
                return length;
        }

        return -1;

    }
}
