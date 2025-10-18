// You are given an integer array nums and an integer k.

// You are allowed to perform the following operation on each element of the array at most once:

// Add an integer in the range [-k, k] to the element.
// Return the maximum possible number of distinct elements in nums after performing the operations.

##CODE:
class Solution {
    public int maxDistinctElements(int[] nums, int k) {
         int n = nums.length;

        Arrays.sort(nums);

        int count = 0;
        int prev = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int minVal = nums[i] - k;

            if (prev < minVal) {
                // choose nums[i] - k
                prev = minVal;
                count++;
            } else if (prev < nums[i] + k) {
                // choose next available distinct value greater than prev
                prev = prev + 1;
                count++;
            }
        }

        return count;
    }
}
