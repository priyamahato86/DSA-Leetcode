// Given an integer array nums, find the maximum possible bitwise OR of a subset of nums and return the number of different non-empty subsets with the maximum bitwise OR.

// An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b. Two subsets are considered different if the indices of the elements chosen are different.

// The bitwise OR of an array a is equal to a[0] OR a[1] OR ... OR a[a.length - 1] (0-indexed).

##CODE:
class Solution {
    public int countMaxOrSubsets(int[] nums) {
         int maxOr = 0;

        for (int num : nums) {
            maxOr = maxOr | num;
        }

        return helper(nums, 0, maxOr, 0);
    }

    private int helper(int[] nums, int index, int maxOr, int currentOr) {
        // Base Condtion
        if (index >= nums.length) {
            return currentOr == maxOr ? 1 : 0;
        }

        int take = helper(nums, index + 1, maxOr, currentOr | nums[index]);
        int notTake = helper(nums, index + 1, maxOr, currentOr);

        return take + notTake;
    }
}
