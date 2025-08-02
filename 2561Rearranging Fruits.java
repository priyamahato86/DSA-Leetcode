// You have two fruit baskets containing n fruits each. You are given two 0-indexed integer arrays basket1 and basket2 representing the cost of fruit in each basket. You want to make both baskets equal. To do so, you can use the following operation as many times as you want:

// Chose two indices i and j, and swap the ith fruit of basket1 with the jth fruit of basket2.
// The cost of the swap is min(basket1[i],basket2[j]).
// Two baskets are considered equal if sorting them according to the fruit cost makes them exactly the same baskets.

// Return the minimum cost to make both the baskets equal or -1 if impossible.

##CODE:
class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        int minEl = Integer.MAX_VALUE;

        // Populate the map and find the minimum element from basket1
        for (int x : basket1) {
            mp.put(x, mp.getOrDefault(x, 0) + 1);
            minEl = Math.min(minEl, x);
        }

        // Update the map with elements from basket2
        for (int x : basket2) {
            mp.put(x, mp.getOrDefault(x, 0) - 1);
            minEl = Math.min(minEl, x);
        }

        ArrayList<Integer> finalList = new ArrayList<>();

        // Process the map to create finalList
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            int cost = entry.getKey();
            int count = entry.getValue();

            if (count == 0) {
                continue;
            }

            if (count % 2 != 0) {
                return -1;  // Immediate return if counts are not even
            }

            // Add half of the count to finalList
            for (int c = 1; c <= Math.abs(count) / 2; c++) {
                finalList.add(cost);
            }
        }

        // Find the min of the elements
        Collections.sort(finalList); // Since we're dealing with costs, we can sort to find the minimum values
        long result = 0;

        // Calculate the minimum cost by using the least costs
        for (int i = 0; i < finalList.size() / 2; i++) {
            result += Math.min(finalList.get(i), minEl * 2);
        }

        return result;
    }
}
