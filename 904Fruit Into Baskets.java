// You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

// You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

// You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
// Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
// Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
// Given the integer array fruits, return the maximum number of fruits you can pick.

##CODE:
class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        HashMap<Integer, Integer> fruitMap = new HashMap<>();

        int i = 0; // Left pointer
        int j = 0; // Right pointer
        int count = 0; // Maximum number of fruits collected

        while (j < n) {
            // Add the fruit at the right pointer to the map
            fruitMap.put(fruits[j], fruitMap.getOrDefault(fruits[j], 0) + 1);

            // While more than 2 types of fruits are in the map, move the left pointer
            if (fruitMap.size() > 2) {
                fruitMap.put(fruits[i], fruitMap.get(fruits[i]) - 1);
                if (fruitMap.get(fruits[i]) == 0) {
                    fruitMap.remove(fruits[i]);
                }
                i++; // Move the left pointer to the right
            }

            // Calculate the maximum count of fruits collected
            count = Math.max(count, j - i + 1);

            // Move the right pointer to the right
            j++;
        }
        return count;
    }
}
