// Given a binary array nums, you should delete one element from it.

// Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.

##CODE:
class Solution {
    public int longestSubarray(int[] nums) {
        int left = 0;
        int right = 0;
        boolean isDeleted = false;
        int ans = 0;

        while (right < nums.length) {
            int num = nums[right];

            if (num == 1) {
                ans = isDeleted ? Math.max(ans, right - left) : Math.max(ans, right - left + 1);
            } else {
                if (isDeleted) {
                    while (nums[left] == 1) {
                        left += 1;
                    }
                    left += 1;
                } else {
                    isDeleted = true;
                }
            }


            right += 1;
        }

        return isDeleted ? ans : ans - 1;
    }
}
