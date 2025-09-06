// You are given a 2D array queries, where queries[i] is of the form [l, r]. Each queries[i] defines an array of integers nums consisting of elements ranging from l to r, both inclusive.

// In one operation, you can:

// Select two integers a and b from the array.
// Replace them with floor(a / 4) and floor(b / 4).
// Your task is to determine the minimum number of operations required to reduce all elements of the array to zero for each query. Return the sum of the results for all queries.

##CODE:
class Solution {
    private long solve(int l, int r) {
        long L = 1;  // starting point of range
        long S = 1;  // steps multiplier
        long steps = 0;

        while (L <= r) {
            long R = 4 * L - 1;  // end of current range

            long start = Math.max(L, (long) l);
            long end = Math.min(R, (long) r);

            if (start <= end) {
                steps += (end - start + 1) * S;
            }

            S += 1;
            L = L * 4;
        }

        return steps;
    }
    public long minOperations(int[][] queries) {
        long result = 0;

        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];

            long steps = solve(l, r);
            result += (steps + 1) / 2;
        }

        return result;
    }
}

 
