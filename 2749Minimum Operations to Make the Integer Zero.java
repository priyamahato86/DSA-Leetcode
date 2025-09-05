// You are given two integers num1 and num2.

// In one operation, you can choose integer i in the range [0, 60] and subtract 2i + num2 from num1.

// Return the integer denoting the minimum number of operations needed to make num1 equal to 0.

// If it is impossible to make num1 equal to 0, return -1.

##CODE:
class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        for (int t = 1; t <= 36; t++) {
            long val = (long) num1 - (long) t * num2;

            if (val < 0)
                return -1;

            if (Long.bitCount(val) <= t && t <= val) {
                return t;
            }
        }

        return -1;
    }
}
