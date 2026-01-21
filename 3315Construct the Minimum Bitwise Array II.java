// You are given an array nums consisting of n prime integers.

// You need to construct an array ans of length n, such that, for each index i, the bitwise OR of ans[i] and ans[i] + 1 is equal to nums[i], i.e. ans[i] OR (ans[i] + 1) == nums[i].

// Additionally, you must minimize each value of ans[i] in the resulting array.

// If it is not possible to find such a value for ans[i] that satisfies the condition, then set ans[i] = -1.

##CODE:

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int num = nums.get(i);

            if (num == 2) {
                result[i] = -1;
                continue;
            }

            boolean found = false;

            for (int j = 1; j < 32; j++) {
        
                if ((num & (1 << j)) != 0) {
                    continue;
                }

               
                result[i] = num ^ (1 << (j - 1));
                found = true;
                break;
            }

            if (!found) {
                result[i] = -1;
            }
        }

        return result;
    }
}
