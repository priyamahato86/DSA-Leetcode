// Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.

// For a given query word, the spell checker handles two categories of spelling mistakes:

// Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
// Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
// Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
// Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
// Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
// Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
// Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
// Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
// In addition, the spell checker operates under the following precedence rules:

// When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
// When the query matches a word up to capitlization, you should return the first such match in the wordlist.
// When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
// If the query has no matches in the wordlist, you should return the empty string.
// Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].

##CODE:
class Solution {
    Set<String> exactWords = new HashSet<>();                 // stores original words
    Map<String, String> caseMap = new HashMap<>();            // lowercase : original word
    Map<String, String> vowelMap = new HashMap<>();           // masked vowels : original word

    private String toLower(String s) {
        return s.toLowerCase();
    }

    private String maskVowels(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                sb.append('*');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private String checkForMatch(String query) {
        // 1. Exact match
        if (exactWords.contains(query)) {
            return query;
        }

        // 2. Case-insensitive match
        String lowerQuery = toLower(query);
        if (caseMap.containsKey(lowerQuery)) {
            return caseMap.get(lowerQuery);
        }

        // 3. Vowel-insensitive match
        String maskedQuery = maskVowels(lowerQuery);
        if (vowelMap.containsKey(maskedQuery)) {
            return vowelMap.get(maskedQuery);
        }

        // 4. No match
        return "";
    }
    public String[] spellchecker(String[] wordlist, String[] queries) {
        exactWords.clear();
        caseMap.clear();
        vowelMap.clear();

        // Preprocess wordlist
        for (String word : wordlist) {
            exactWords.add(word);

            String lowerWord = toLower(word);
            caseMap.putIfAbsent(lowerWord, word);  // keep only first occurrence

            String maskedWord = maskVowels(lowerWord);
            vowelMap.putIfAbsent(maskedWord, word); // keep only first occurrence
        }

        String[] result = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = checkForMatch(queries[i]);
        }
        return result;
    }
}
