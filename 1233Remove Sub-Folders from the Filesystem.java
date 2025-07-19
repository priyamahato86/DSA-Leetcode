// Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.

// If a folder[i] is located within another folder[j], it is called a sub-folder of it. A sub-folder of folder[j] must start with folder[j], followed by a "/". For example, "/a/b" is a sub-folder of "/a", but "/b" is not a sub-folder of "/a/b/c".

// The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.

// For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.

##CODE:
class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> result= new ArrayList<>();
        result.add(folder[0]);

        for(int i=1;i<folder.length;i++){
            String input= result.get(result.size()-1);
            input+='/';

            if(!folder[i].startsWith(input)){
                result.add(folder[i]);
            }
        }
        return result;
    }
}
