// You are given a 0-indexed integer array nums and an integer value.

// In one operation, you can add or subtract value from any element of nums.

// For example, if nums = [1,2,3] and value = 2, you can choose to subtract value from nums[0] to make nums = [-1,2,3].
// The MEX (minimum excluded) of an array is the smallest missing non-negative integer in it.

// For example, the MEX of [-1,2,3] is 0 while the MEX of [1,0,3] is 2.
// Return the maximum MEX of nums after applying the mentioned operation any number of times.

##CODE:
class Solution {
    public int findSmallestInteger(int[] nums, int value) {
         HashMap<Integer, Integer> map = new HashMap<>();

    
        for (int num : nums) {
            int r = ((num % value) + value) % value; 
            map.put(r, map.getOrDefault(r, 0) + 1);
        }

        int MEX = 0;

        
        while (map.getOrDefault(MEX % value, 0) > 0) {
            map.put(MEX % value, map.get(MEX % value) - 1);
            MEX++;
        }

        return MEX;
    }
}

 
