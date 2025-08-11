// Given a positive integer n, there exists a 0-indexed array called powers, composed of the minimum number of powers of 2 that sum to n. The array is sorted in non-decreasing order, and there is only one way to form the array.

// You are also given a 0-indexed 2D integer array queries, where queries[i] = [lefti, righti]. Each queries[i] represents a query where you have to find the product of all powers[j] with lefti <= j <= righti.

// Return an array answers, equal in length to queries, where answers[i] is the answer to the ith query. Since the answer to the ith query may be too large, each answers[i] should be returned modulo 109 + 7.

##CODE:
class Solution {
    int MOD = (int)(1e9 + 7);

    public int[] productQueries(int n, int[][] queries) {
          List<Long> powers = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        // Build powers array
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) { // i-th bit is set
                powers.add((long)(1 << i));
            }
        }

        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];

            long product = 1;
            for (int i = start; i <= end; i++) {
                product = (product * powers.get(i)) % MOD;
            }

            result.add((int)product);
        }

        // Convert result to int array
        return result.stream().mapToInt(i -> i).toArray();
    }
}
