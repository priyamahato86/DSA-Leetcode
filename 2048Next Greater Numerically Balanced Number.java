// An integer x is numerically balanced if for every digit d in the number x, there are exactly d occurrences of that digit in x.

// Given an integer n, return the smallest numerically balanced number strictly greater than n.

##CODE:
class Solution {
    private int[] digitCount = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    private int backtrack(int n, int curr, int count) {
        if (count == 0) {
            for (int digit = 1; digit <= 9; digit++) {
                if (digitCount[digit] != 0 && digitCount[digit] != digit) {
                    return 0;
                }
            }
            return curr > n ? curr : 0;
        }

        int result = 0;
        for (int digit = 1; digit <= 9; digit++) {
            if (digitCount[digit] > 0 && digitCount[digit] <= count) {
                digitCount[digit]--;
                result = backtrack(n, curr * 10 + digit, count - 1);
                digitCount[digit]++;
            }
            if (result != 0) break;
        }
        return result;
    }
    public int nextBeautifulNumber(int n) {
        int numDigits = String.valueOf(n).length();

        int result = backtrack(n, 0, numDigits);
        if (result == 0) {
            result = backtrack(n, 0, numDigits + 1);
        }

        return result;
    }
}
