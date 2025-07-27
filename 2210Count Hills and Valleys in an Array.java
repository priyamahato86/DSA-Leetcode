// You are given a 0-indexed integer array nums. An index i is part of a hill in nums if the closest non-equal neighbors of i are smaller than nums[i]. Similarly, an index i is part of a valley in nums if the closest non-equal neighbors of i are larger than nums[i]. Adjacent indices i and j are part of the same hill or valley if nums[i] == nums[j].

// Note that for an index to be part of a hill or valley, it must have a non-equal neighbor on both the left and right of the index.

// Return the number of hills and valleys in nums.

##CODE:
class Solution {
    public int countHillValley(int[] nums) {
        int n = nums.length;

        int i = 0; // points to non-equal neighbor on the left-hand side
        int j = 1; // points to non-equal neighbor on the right-hand side by [j+1]

        int count = 0;
        while (j + 1 < n) {
            if ((nums[i] < nums[j] && nums[j] > nums[j + 1])
                    ||
                (nums[i] > nums[j] && nums[j] < nums[j + 1])) {
                count++;
                i = j;
            }

            j++;
        }

        return count;
    }
}
