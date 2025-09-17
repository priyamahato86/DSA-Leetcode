// Design a food rating system that can do the following:

// Modify the rating of a food item listed in the system.
// Return the highest-rated food item for a type of cuisine in the system.
// Implement the FoodRatings class:

// FoodRatings(String[] foods, String[] cuisines, int[] ratings) Initializes the system. The food items are described by foods, cuisines and ratings, all of which have a length of n.
// foods[i] is the name of the ith food,
// cuisines[i] is the type of cuisine of the ith food, and
// ratings[i] is the initial rating of the ith food.
// void changeRating(String food, int newRating) Changes the rating of the food item with the name food.
// String highestRated(String cuisine) Returns the name of the food item that has the highest rating for the given type of cuisine. If there is a tie, return the item with the lexicographically smaller name.
// Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is, either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.

##CODE:
class FoodRatings {
    private static class Food {
        String name;
        int rating;

        Food(String name, int rating) {
            this.name = name;
            this.rating = rating;
        }
    }

    private Map<String, PriorityQueue<Food>> cuisinePQ = new HashMap<>();
    private Map<String, String> foodToCuisine = new HashMap<>();
    private Map<String, Integer> foodToRating = new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodToCuisine.put(food, cuisine);
            foodToRating.put(food, rating);
            
            cuisinePQ.computeIfAbsent(cuisine, k -> new PriorityQueue<>(
                (a, b) -> a.rating != b.rating ? b.rating - a.rating : a.name.compareTo(b.name)
            )).offer(new Food(food, rating));
        }
    }
    
    public void changeRating(String food, int newRating) {
         String cuisine = foodToCuisine.get(food);
        foodToRating.put(food, newRating);
        cuisinePQ.get(cuisine).offer(new Food(food, newRating));
    }
    
    public String highestRated(String cuisine) {
        PriorityQueue<Food> pq = cuisinePQ.get(cuisine);

        while (true) {
            Food food = pq.peek();

            if (food.rating == foodToRating.get(food.name)) {
                return food.name;
            }
            pq.poll();
        }
    }
}
