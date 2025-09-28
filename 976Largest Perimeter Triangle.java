// Given an integer array nums, return the largest perimeter of a triangle with a non-zero area, formed from three of these lengths. If it is impossible to form any triangle of a non-zero area, return 0.

##CODE:
class Solution {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int largestParameter = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] + nums[i + 1] > nums[i + 2]) {
                largestParameter = Math.max(largestParameter, nums[i] + nums[i + 1] + nums[i + 2]);
            }
        }

        return largestParameter;
    }
}
