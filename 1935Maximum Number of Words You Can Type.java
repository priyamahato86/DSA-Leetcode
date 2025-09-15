// There is a malfunctioning keyboard where some letter keys do not work. All other keys on the keyboard work properly.

// Given a string text of words separated by a single space (no leading or trailing spaces) and a string brokenLetters of all distinct letter keys that are broken, return the number of words in text you can fully type using this keyboard.


##CODE:
class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] broken = new boolean[26];
        for (char ch : brokenLetters.toCharArray()) {
            broken[ch - 'a'] = true;
        }

        int result = 0;
        boolean foundBroken = false;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch == ' ') { 
                if (!foundBroken) {
                    result++;
                }
                foundBroken = false; 
            } else if (broken[ch - 'a']) {
                foundBroken = true; 
            }
        }

        
        if (!foundBroken) {
            result++;
        }

        return result;
    }
}
