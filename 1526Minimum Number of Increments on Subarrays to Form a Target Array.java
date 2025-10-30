// You are given an integer array target. You have an integer array initial of the same size as target with all elements initially zeros.

// In one operation you can choose any subarray from initial and increment each value by one.

// Return the minimum number of operations to form a target array from initial.

// The test cases are generated so that the answer fits in a 32-bit integer.

##CODE:
class Solution {
    public int minNumberOperations(int[] target) {
          int sum = target[0];

        for (int i = 1; i < target.length; i++) {
            if (target[i] > target[i - 1]) {
                sum += (target[i] - target[i - 1]);
            }
        }

        return sum;
    }
}
