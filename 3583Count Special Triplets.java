// You are given an integer array nums.

// A special triplet is defined as a triplet of indices (i, j, k) such that:

// 0 <= i < j < k < n, where n = nums.length
// nums[i] == nums[j] * 2
// nums[k] == nums[j] * 2
// Return the total number of special triplets in the array.

// Since the answer may be large, return it modulo 109 + 7.

##CODE:
class Solution {
    static final int M = (int)1e9 + 7;
    public int specialTriplets(int[] nums) {
        Map<Integer, Integer> validI = new HashMap<>();
        Map<Integer, Integer> validJ = new HashMap<>();

        int result = 0;

        for (int num : nums) {

            if (num % 2 == 0) {
                result = (result + validJ.getOrDefault(num / 2, 0)) % M;
            }
            int updatedJ = (validJ.getOrDefault(num, 0) +
                            validI.getOrDefault(num * 2, 0)) % M;
            validJ.put(num, updatedJ);

            
            validI.put(num, validI.getOrDefault(num, 0) + 1);
        }

        return result;
    }
}
