// You are given an array nums of size n, consisting of non-negative integers. Your task is to apply some (possibly zero) operations on the array so that all elements become 0.

// In one operation, you can select a subarray [i, j] (where 0 <= i <= j < n) and set all occurrences of the minimum non-negative integer in that subarray to 0.

// Return the minimum number of operations required to make all elements in the array 0.

##CODE:

class Solution {
    public int minOperations(int[] nums) {
         Stack<Integer> st = new Stack<>();
        int ops = 0;

        for (int num : nums) {
            while (!st.isEmpty() && st.peek() > num) {
                st.pop();
            }

            if (num == 0) continue;

            if (st.isEmpty() || st.peek() < num) {
                st.push(num);
                ops++;
            }
        }

        return ops;
    }
}
