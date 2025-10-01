// There are numBottles water bottles that are initially full of water. You can exchange numExchange empty water bottles from the market with one full water bottle.

// The operation of drinking a full water bottle turns it into an empty bottle.

// Given the two integers numBottles and numExchange, return the maximum number of water bottles you can drink.

##CODE:
class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int consumed = numBottles;
        int emptyBottles = numBottles;

        while (emptyBottles >= numExchange) {
            int extraFullBottles = emptyBottles / numExchange;
            int remain = emptyBottles % numExchange;

            consumed += extraFullBottles;
            emptyBottles = remain + extraFullBottles;
        }

        return consumed;
    }
}
