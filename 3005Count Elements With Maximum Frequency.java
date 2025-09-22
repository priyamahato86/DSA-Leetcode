// You are given an array nums consisting of positive integers.

// Return the total frequencies of elements in nums such that those elements all have the maximum frequency.

// The frequency of an element is the number of occurrences of that element in the array.

##CODE:
class Solution {
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        int maxFreq = 0;

        for (int num : nums) {
            int count = freq.getOrDefault(num, 0) + 1;
            freq.put(num, count);
            maxFreq = Math.max(maxFreq, count);
        }

        int result = 0;
        for (int value : freq.values()) {
            if (value == maxFreq) {
                result += value;
            }
        }

        return result;
    }
}
