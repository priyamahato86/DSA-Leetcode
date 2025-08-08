// You have two soups, A and B, each starting with n mL. On every turn, one of the following four serving operations is chosen at random, each with probability 0.25 independent of all previous turns:

// pour 100 mL from type A and 0 mL from type B
// pour 75 mL from type A and 25 mL from type B
// pour 50 mL from type A and 50 mL from type B
// pour 25 mL from type A and 75 mL from type B
// Note:

// There is no operation that pours 0 mL from A and 100 mL from B.
// The amounts from A and B are poured simultaneously during the turn.
// If an operation asks you to pour more than you have left of a soup, pour all that remains of that soup.
// The process stops immediately after any turn in which one of the soups is used up.

// Return the probability that A is used up before B, plus half the probability that both soups are used up in the same turn. Answers within 10-5 of the actual answer will be accepted.

##CODE:
class Solution {
    int[][] operations = {
        {100, 0},
        {75, 25},
        {50, 50},
        {25, 75}
    };
    public double soupServings(int n) {
         if (n > 5000) {
            return 1.0;
        }

        Double[][] memo = new Double[n + 1][n + 1];
        return solve(n, n, memo);
    }

    private double solve(int a, int b, Double[][] memo) {
        // A & B
        if (a <= 0 && b <= 0) {
            return 0.5;
        }

        // A
        if (a <= 0) {
            return 1.0;
        }

        // B
        if (b <= 0) {
            return 0.0;
        }

        if (memo[a][b] != null) {
            return memo[a][b];
        }

        double prob = 0.0;
        for (int[] operation : operations) {
            int leftA = a - operation[0];
            int leftB = b - operation[1];

            prob += solve(leftA, leftB, memo);
        }
        memo[a][b] = 0.25 * prob;
        return memo[a][b];
    }
}
