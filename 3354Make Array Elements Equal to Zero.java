// You are given an integer array nums.

// Start by selecting a starting position curr such that nums[curr] == 0, and choose a movement direction of either left or right.

// After that, you repeat the following process:

// If curr is out of the range [0, n - 1], this process ends.
// If nums[curr] == 0, move in the current direction by incrementing curr if you are moving right, or decrementing curr if you are moving left.
// Else if nums[curr] > 0:
// Decrement nums[curr] by 1.
// Reverse your movement direction (left becomes right and vice versa).
// Take a step in your new direction.
// A selection of the initial position curr and movement direction is considered valid if every element in nums becomes 0 by the end of the process.

// Return the number of possible valid selections.

##CODE:
class Solution {
    public int countValidSelections(int[] nums) {
        int n = nums.length;
        int result = 0;
        int currSum = 0;
        int S = 0;

        for (int num : nums) {
            S += num;
        }

        for (int i = 0; i < n; i++) {
            currSum += nums[i];

            int left  = currSum;
            int right = S - left;

            if (nums[i] != 0) 
                continue;

            if (left == right)
                result += 2;
            else if (Math.abs(left - right) == 1)
                result++;
        }

        return result;
    }
}
