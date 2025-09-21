// You have a movie renting company consisting of n shops. You want to implement a renting system that supports searching for, booking, and returning movies. The system should also support generating a report of the currently rented movies.

// Each movie is given as a 2D integer array entries where entries[i] = [shopi, moviei, pricei] indicates that there is a copy of movie moviei at shop shopi with a rental price of pricei. Each shop carries at most one copy of a movie moviei.

// The system should support the following functions:

// Search: Finds the cheapest 5 shops that have an unrented copy of a given movie. The shops should be sorted by price in ascending order, and in case of a tie, the one with the smaller shopi should appear first. If there are less than 5 matching shops, then all of them should be returned. If no shop has an unrented copy, then an empty list should be returned.
// Rent: Rents an unrented copy of a given movie from a given shop.
// Drop: Drops off a previously rented copy of a given movie at a given shop.
// Report: Returns the cheapest 5 rented movies (possibly of the same movie ID) as a 2D list res where res[j] = [shopj, moviej] describes that the jth cheapest rented movie moviej was rented from the shop shopj. The movies in res should be sorted by price in ascending order, and in case of a tie, the one with the smaller shopj should appear first, and if there is still tie, the one with the smaller moviej should appear first. If there are fewer than 5 rented movies, then all of them should be returned. If no movies are currently being rented, then an empty list should be returned.
// Implement the MovieRentingSystem class:

// MovieRentingSystem(int n, int[][] entries) Initializes the MovieRentingSystem object with n shops and the movies in entries.
// List<Integer> search(int movie) Returns a list of shops that have an unrented copy of the given movie as described above.
// void rent(int shop, int movie) Rents the given movie from the given shop.
// void drop(int shop, int movie) Drops off a previously rented movie at the given shop.
// List<List<Integer>> report() Returns a list of cheapest rented movies as described above.
// Note: The test cases will be generated such that rent will only be called if the shop has an unrented copy of the movie, and drop will only be called if the shop had previously rented out the movie.

##CODE:
class MovieRentingSystem {
    class PriceShop implements Comparable<PriceShop> {
        int price, shop;

        PriceShop(int price, int shop) {
            this.price = price;
            this.shop = shop;
        }

        public int compareTo(PriceShop o) {
            if (this.price != o.price)
                return this.price - o.price;
            return this.shop - o.shop;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof PriceShop))
                return false;
            PriceShop p = (PriceShop) o;
            return price == p.price && shop == p.shop;
        }

        @Override
        public int hashCode() {
            return Objects.hash(price, shop);
        }
    }

    class RentedMovie implements Comparable<RentedMovie> {
        int price, shop, movie;

        RentedMovie(int price, int shop, int movie) {
            this.price = price;
            this.shop = shop;
            this.movie = movie;
        }

        public int compareTo(RentedMovie o) {
            if (this.price != o.price)
                return this.price - o.price;
            if (this.shop != o.shop)
                return this.shop - o.shop;
            return this.movie - o.movie;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof RentedMovie))
                return false;
            RentedMovie r = (RentedMovie) o;
            return price == r.price && shop == r.shop && movie == r.movie;
        }

        @Override
        public int hashCode() {
            return Objects.hash(price, shop, movie);
        }
    }

    Map<Integer, TreeSet<PriceShop>> available; // movie -> {price, shop}
    Map<Integer, Map<Integer, Integer>> movieToShopPrice; // movie -> (shop -> price)
    TreeSet<RentedMovie> rented; // sorted rented movies

    public MovieRentingSystem(int n, int[][] entries) {
        available = new HashMap<>();
        movieToShopPrice = new HashMap<>();
        rented = new TreeSet<>();

        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];

            available.computeIfAbsent(movie, k -> new TreeSet<>()).add(new PriceShop(price, shop));
            movieToShopPrice.computeIfAbsent(movie, k -> new HashMap<>()).put(shop, price);
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();
        if (available.containsKey(movie)) {
            int count = 0;
            for (PriceShop ps : available.get(movie)) {
                res.add(ps.shop);
                count++;
                if (count >= 5)
                    break;
            }
        }
        return res;
    }

    public void rent(int shop, int movie) {
        int price = movieToShopPrice.get(movie).get(shop);
        available.get(movie).remove(new PriceShop(price, shop));
        rented.add(new RentedMovie(price, shop, movie));
    }

    public void drop(int shop, int movie) {
        int price = movieToShopPrice.get(movie).get(shop);
        available.get(movie).add(new PriceShop(price, shop));
        rented.remove(new RentedMovie(price, shop, movie));
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        int count = 0;
        for (RentedMovie rm : rented) {
            res.add(Arrays.asList(rm.shop, rm.movie));
            count++;
            if (count >= 5)
                break;
        }
        return res;
    }
}
