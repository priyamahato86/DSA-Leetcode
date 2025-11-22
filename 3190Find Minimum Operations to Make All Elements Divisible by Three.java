// You are given an integer array nums. In one operation, you can add or subtract 1 from any element of nums.

// Return the minimum number of operations to make all elements of nums divisible by 3.

##CODE:
class Solution {
    public int minimumOperations(int[] nums) {
        int result = 0;

        for (int n : nums) {
            if (n % 3 != 0) {
                result += 1;
            }
        }

        return result;
    }
}
