// You are given an integer n, representing the number of employees in a company. Each employee is assigned a unique ID from 1 to n, and employee 1 is the CEO. You are given two 1-based integer arrays, present and future, each of length n, where:

// present[i] represents the current price at which the ith employee can buy a stock today.
// future[i] represents the expected price at which the ith employee can sell the stock tomorrow.
// The company's hierarchy is represented by a 2D integer array hierarchy, where hierarchy[i] = [ui, vi] means that employee ui is the direct boss of employee vi.

// Additionally, you have an integer budget representing the total funds available for investment.

// However, the company has a discount policy: if an employee's direct boss purchases their own stock, then the employee can buy their stock at half the original price (floor(present[v] / 2)).

// Return the maximum profit that can be achieved without exceeding the given budget.

// Note:

// You may buy each stock at most once.
// You cannot use any profit earned from future stock prices to fund additional investments and must buy only from budget.

##CODE:

class Solution {
     private void dfs(
        int u,
        int[] present,
        int[] future,
        List<Integer>[] adj,
        int[][][] statesProfit,
        int budget
    ) {
        List<int[][]> childrenStatesProfit = new ArrayList<>();

        for (int v : adj[u]) {
            dfs(v, present, future, adj, statesProfit, budget);
            childrenStatesProfit.add(
                new int[][] { statesProfit[v][0], statesProfit[v][1] }
            );
        }

        for (int parentBought = 0; parentBought <= 1; parentBought++) {

            int price = (parentBought == 1) ? present[u] / 2 : present[u];
            int profit = future[u] - price;

            int[] bestProfitAtU = new int[budget + 1];

          
            int[] childrenProfitIfUNotBought = new int[budget + 1];

            for (int[][] child : childrenStatesProfit) {
                int[] childNoBuy = child[0];
                int[] temp = new int[budget + 1];

                for (int used = 0; used <= budget; used++) {
                    for (int take = 0; used + take <= budget; take++) {
                        temp[used + take] = Math.max(
                            temp[used + take],
                            childrenProfitIfUNotBought[used] + childNoBuy[take]
                        );
                    }
                }
                childrenProfitIfUNotBought = temp;
            }

            for (int b = 0; b <= budget; b++) {
                bestProfitAtU[b] = Math.max(bestProfitAtU[b], childrenProfitIfUNotBought[b]);
            }

            if (price <= budget) {
                int[] childrenProfitIfUBought = new int[budget + 1];

                for (int[][] child : childrenStatesProfit) {
                    int[] childBuy = child[1];
                    int[] temp = new int[budget + 1];

                    for (int used = 0; used <= budget; used++) {
                        for (int take = 0; used + take <= budget; take++) {
                            temp[used + take] = Math.max(
                                temp[used + take],
                                childrenProfitIfUBought[used] + childBuy[take]
                            );
                        }
                    }
                    childrenProfitIfUBought = temp;
                }

                for (int b = price; b <= budget; b++) {
                    bestProfitAtU[b] = Math.max(
                        bestProfitAtU[b],
                        childrenProfitIfUBought[b - price] + profit
                    );
                }
            }

            statesProfit[u][parentBought] = bestProfitAtU;
        }
    }
    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        for (int[] h : hierarchy) {
            int u = h[0] - 1;
            int v = h[1] - 1;
            adj[u].add(v);
        }

        int[][][] statesProfit = new int[n][2][budget + 1];

        dfs(0, present, future, adj, statesProfit, budget);

        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            ans = Math.max(ans, statesProfit[0][0][b]);
        }
        return ans;
    }
}
