// You are given a 0-indexed string array words, where words[i] consists of lowercase English letters.

// In one operation, select any index i such that 0 < i < words.length and words[i - 1] and words[i] are anagrams, and delete words[i] from words. Keep performing this operation as long as you can select an index that satisfies the conditions.

// Return words after performing all operations. It can be shown that selecting the indices for each operation in any arbitrary order will lead to the same result.

// An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase using all the original letters exactly once. For example, "dacb" is an anagram of "abdc".

##CODE:
class Solution {
    private boolean checkAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        int[] arr = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            arr[s1.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s2.length(); i++) {
            arr[s2.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }

        return true;
    }

    public List<String> removeAnagrams(String[] words) {
          int n = words.length;
        List<String> result = new ArrayList<>();

        result.add(words[0]);

        for (int i = 1; i < n; i++) {
            String lastWord = result.get(result.size() - 1);
            if (!checkAnagram(words[i], lastWord)) {
                result.add(words[i]);
            }
        }

        return result;
    }
}
