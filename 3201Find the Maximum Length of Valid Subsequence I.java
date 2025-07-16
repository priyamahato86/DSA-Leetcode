// You are given an integer array nums.
// A subsequence sub of nums with length x is called valid if it satisfies:

// (sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2.
// Return the length of the longest valid subsequence of nums.

// A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

##CODE:
class Solution {
    public int maximumLength(int[] nums) {
        int countEven = 0, countOdd = 0;
        for (int num : nums) {
            if (num % 2 == 0) 
                countEven++;
            else 
                countOdd++;
        }

        int altLen = 1; // At least one number
        int prevParity = nums[0]%2;

        for (int i = 1; i < nums.length; ++i) {
            int currParity = nums[i] % 2;
            if (currParity != prevParity) {
                altLen++;
                prevParity = currParity;
            }
        }

        return Math.max(Math.max(countEven, countOdd), altLen);
    }
}
