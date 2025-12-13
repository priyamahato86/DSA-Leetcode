// You are given three arrays of length n that describe the properties of n coupons: code, businessLine, and isActive. The ith coupon has:

// code[i]: a string representing the coupon identifier.
// businessLine[i]: a string denoting the business category of the coupon.
// isActive[i]: a boolean indicating whether the coupon is currently active.
// A coupon is considered valid if all of the following conditions hold:

// code[i] is non-empty and consists only of alphanumeric characters (a-z, A-Z, 0-9) and underscores (_).
// businessLine[i] is one of the following four categories: "electronics", "grocery", "pharmacy", "restaurant".
// isActive[i] is true.
// Return an array of the codes of all valid coupons, sorted first by their businessLine in the order: "electronics", "grocery", "pharmacy", "restaurant", and then by code in lexicographical (ascending) order within each category.

##CODE:

class Solution {
     private boolean checkValidCode(String code) {
        if (code == null || code.isEmpty()) {
            return false;
        }

        for (char ch : code.toCharArray()) {
            if (!Character.isLetterOrDigit(ch) && ch != '_') {
                return false;
            }
        }
        return true;
    }
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
         Map<String, Integer> mp = new HashMap<>();
        mp.put("electronics", 0);
        mp.put("grocery", 1);
        mp.put("pharmacy", 2);
        mp.put("restaurant", 3);

        List<Pair> temp = new ArrayList<>();

        for (int i = 0; i < code.length; i++) {
            if (isActive[i]
                    && mp.containsKey(businessLine[i])
                    && checkValidCode(code[i])) {

                temp.add(new Pair(mp.get(businessLine[i]), code[i]));
            }
        }

        Collections.sort(temp);

        List<String> result = new ArrayList<>();
        for (Pair p : temp) {
            result.add(p.code);
        }

        return result;
    }

    private static class Pair implements Comparable<Pair> {
        int order;
        String code;

        Pair(int order, String code) {
            this.order = order;
            this.code = code;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.order != other.order) {
                return Integer.compare(this.order, other.order);
            }
            return this.code.compareTo(other.code);
    }
}
}
