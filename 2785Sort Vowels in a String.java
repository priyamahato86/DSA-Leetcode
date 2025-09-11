// Given a 0-indexed string s, permute s to get a new string t such that:

// All consonants remain in their original places. More formally, if there is an index i with 0 <= i < s.length such that s[i] is a consonant, then t[i] = s[i].
// The vowels must be sorted in the nondecreasing order of their ASCII values. More formally, for pairs of indices i, j with 0 <= i < j < s.length such that s[i] and s[j] are vowels, then t[i] must not have a higher ASCII value than t[j].
// Return the resulting string.

// The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in lowercase or uppercase. Consonants comprise all letters that are not vowels.

##CODE:
class Solution {
    private boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
    }

    public String sortVowels(String s) {
Map<Character, Integer> map = new HashMap<>();

        for (char ch : s.toCharArray()) {
            if (isVowel(ch)) {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }
        }

        String vowels = "AEIOUaeiou";
        int j = 0; // pointing to temp (current vowel)

        char[] resultArray = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) {
                while (map.getOrDefault(vowels.charAt(j), 0) == 0) {
                    j++;
                }

                resultArray[i] = vowels.charAt(j);
                map.put(vowels.charAt(j), map.get(vowels.charAt(j)) - 1);
            }
        }

        return new String(resultArray);

    }
}

