// You are given a string s consisting of lowercase English letters ('a' to 'z').

// Your task is to:

// Find the vowel (one of 'a', 'e', 'i', 'o', or 'u') with the maximum frequency.
// Find the consonant (all other letters excluding vowels) with the maximum frequency.
// Return the sum of the two frequencies.

// Note: If multiple vowels or consonants have the same maximum frequency, you may choose any one of them. If there are no vowels or no consonants in the string, consider their frequency as 0.


##CODE:
class Solution {
    public int maxFreqSum(String s) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a'] += 1;
        }

        int vMax = 0, cMax = 0;
        String vowels = "aeiou";

        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 'a');
            int f = freq[i];

            if (vowels.indexOf(c) != -1) {
                vMax = Math.max(vMax, f);
            } else {
                cMax = Math.max(cMax, f);
            }
        }

        return vMax + cMax;
    }
}
