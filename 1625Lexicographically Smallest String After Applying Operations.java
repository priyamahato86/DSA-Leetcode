// You are given a string s of even length consisting of digits from 0 to 9, and two integers a and b.

// You can apply either of the following two operations any number of times and in any order on s:

// Add a to all odd indices of s (0-indexed). Digits post 9 are cycled back to 0. For example, if s = "3456" and a = 5, s becomes "3951".
// Rotate s to the right by b positions. For example, if s = "3456" and b = 1, s becomes "6345".
// Return the lexicographically smallest string you can obtain by applying the above operations any number of times on s.

// A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b. For example, "0158" is lexicographically smaller than "0190" because the first position they differ is at the third letter, and '5' comes before '9'.

##CODE:
class Solution {
    void rotate(StringBuilder s, int b) {
        int n = s.length();
        b %= n;
        reverse(s, 0, n - 1);
        reverse(s, 0, b - 1);
        reverse(s, b, n - 1);
    }

    void reverse(StringBuilder s, int l, int r) {
        while (l < r) {
            char temp = s.charAt(l);
            s.setCharAt(l, s.charAt(r));
            s.setCharAt(r, temp);
            l++;
            r--;
        }
    }

    void dfs(StringBuilder curr, int a, int b, Set<String> visited, StringBuilder smallest) {
        String str = curr.toString();
        if (visited.contains(str)) return;
        visited.add(str);
        if (str.compareTo(smallest.toString()) < 0)
            smallest.replace(0, smallest.length(), str);

        StringBuilder added = new StringBuilder(str);
        for (int i = 1; i < added.length(); i += 2) {
            int newDigit = (added.charAt(i) - '0' + a) % 10;
            added.setCharAt(i, (char) (newDigit + '0'));
        }
        dfs(added, a, b, visited, smallest);

        StringBuilder rotated = new StringBuilder(str);
        rotate(rotated, b);
        dfs(rotated, a, b, visited, smallest);
    }
    public String findLexSmallestString(String s, int a, int b) {
         Set<String> visited = new HashSet<>();
        StringBuilder smallest = new StringBuilder(s);
        dfs(new StringBuilder(s), a, b, visited, smallest);
        return smallest.toString();
    }
}
