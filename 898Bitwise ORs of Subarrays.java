// Given an integer array arr, return the number of distinct bitwise ORs of all the non-empty subarrays of arr.

// The bitwise OR of a subarray is the bitwise OR of each integer in the subarray. The bitwise OR of a subarray of one integer is that integer.

// A subarray is a contiguous non-empty sequence of elements within an array.

##CODE:
class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> prev = new HashSet<>();
        Set<Integer> curr = new HashSet<>();
        Set<Integer> result = new HashSet<>();

        for (int num : arr) {
            for (int x : prev) {
                curr.add(x | num);
                result.add(x | num);
            }

            curr.add(num);
            result.add(num);

            prev = curr;
            curr = new HashSet<>();
        }

        return result.size();
    }
}
