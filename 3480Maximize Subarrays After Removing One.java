// You are given an integer n which represents an array nums containing the numbers from 1 to n in order. Additionally, you are given a 2D array conflictingPairs, where conflictingPairs[i] = [a, b] indicates that a and b form a conflicting pair.

// Remove exactly one element from conflictingPairs. Afterward, count the number of non-empty subarrays of nums which do not contain both a and b for any remaining conflicting pair [a, b].

// Return the maximum number of subarrays possible after removing exactly one conflicting pair.

##CODE:
class Solution {
    public long maxSubarrays(int n, int[][] conflictingPairs) {
long valid = 0;

        // conflictingPoints[i] = list of points which conflict with i
        List<List<Integer>> conflictingPoints = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            conflictingPoints.add(new ArrayList<>());
        }

        // Build the conflicting points list
        for (int[] p : conflictingPairs) { 
            int a = Math.min(p[0], p[1]);
            int b = Math.max(p[0], p[1]);
            conflictingPoints.get(b).add(a);
        }

        int maxConflict = 0;
        int secondMaxConflict = 0;

        // extra[i] = number of extra subarrays by removing the conflicting point i
        long[] extra = new long[n + 1];

        // Process each end point of subarrays
        for (int end = 1; end <= n; end++) {
            // Check all conflicting points of 'end'
            for (int u : conflictingPoints.get(end)) {
                if (u >= maxConflict) {
                    secondMaxConflict = maxConflict;
                    maxConflict = u;
                } else if (u > secondMaxConflict) {
                    secondMaxConflict = u;
                }
            }
            // Count valid subarrays ending at 'end'
            valid += end - maxConflict;
            // Add extra subarrays count
            extra[maxConflict] += maxConflict - secondMaxConflict;
        }

        // Find the maximum value in extra array
        long maxExtra = 0;
        for (long val : extra) {
            if (val > maxExtra) {
                maxExtra = val;
            }
        }

        return valid + maxExtra;
    }
}
