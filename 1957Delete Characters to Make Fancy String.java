// A fancy string is a string where no three consecutive characters are equal.

// Given a string s, delete the minimum possible number of characters from s to make it fancy.

// Return the final string after the deletion. It can be shown that the answer will always be unique.

##CODE:

class Solution {
    public String makeFancyString(String s) {
        int n = s.length();
        StringBuilder result = new StringBuilder();

        // Start with the first character
        result.append(s.charAt(0));
        int freq = 1;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == result.charAt(result.length() - 1)) {
                freq++;
                if (freq < 3) {
                    result.append(s.charAt(i));
                }
            } else {
                result.append(s.charAt(i));
                freq = 1;
            }
        }

        return result.toString();
    }
}
