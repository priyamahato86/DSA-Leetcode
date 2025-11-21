// Given a string s, return the number of unique palindromes of length three that are a subsequence of s.

// Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.

// A palindrome is a string that reads the same forwards and backwards.

// A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

// For example, "ace" is a subsequence of "abcde".

##CODE:


class Solution {
    public int countPalindromicSubsequence(String s) {
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        
        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i) - 'a';
            if (first[curr] == - 1) {
                first[curr] = i;
            }
            
            last[curr] = i;
        }
        
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) {
                continue;
            }
            
            Set<Character> st = new HashSet();
            for (int middle = first[i] + 1; middle < last[i]; middle++) {
                st.add(s.charAt(middle));
            }
            
            ans += st.size();
        }
        
        return ans;
    }
}
