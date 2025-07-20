// Due to a bug, there are many duplicate folders in a file system. You are given a 2D array paths, where paths[i] is an array representing an absolute path to the ith folder in the file system.

// For example, ["one", "two", "three"] represents the path "/one/two/three".
// Two folders (not necessarily on the same level) are identical if they contain the same non-empty set of identical subfolders and underlying subfolder structure. The folders do not need to be at the root level to be identical. If two or more folders are identical, then mark the folders as well as all their subfolders.

// For example, folders "/a" and "/b" in the file structure below are identical. They (as well as their subfolders) should all be marked:
// /a
// /a/x
// /a/x/y
// /a/z
// /b
// /b/x
// /b/x/y
// /b/z
// However, if the file structure also included the path "/b/w", then the folders "/a" and "/b" would not be identical. Note that "/a/x" and "/b/x" would still be considered identical even with the added folder.
// Once all the identical folders and their subfolders have been marked, the file system will delete all of them. The file system only runs the deletion once, so any folders that become identical after the initial deletion are not deleted.

// Return the 2D array ans containing the paths of the remaining folders after deleting all the marked folders. The paths may be returned in any order.

 
##CODE:



class Solution {
    static class TrieNode {
        Map<String, TrieNode> children = new HashMap<>();
        String name;
        boolean isDeleted = false;
        
        TrieNode(String name) {
            this.name = name;
        }
    }

    Map<String, List<TrieNode>> serialMap = new HashMap<>();
    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        TrieNode root = new TrieNode("");
        
        // 1. Build the Trie
        for (List<String> path : paths) {
            TrieNode node = root;
            for (String folder : path) {
                node.children.putIfAbsent(folder, new TrieNode(folder));
                node = node.children.get(folder);
            }
        }

        // 2. Serialize Subtrees
        serialize(root);

        // 3. Mark Duplicates
        for (List<TrieNode> group : serialMap.values()) {
            if (group.size() > 1) {
                for (TrieNode node : group) {
                    node.isDeleted = true;
                }
            }
        }

        // 4. Collect Remaining Paths
        List<List<String>> result = new ArrayList<>();
        dfs(root, new ArrayList<>(), result);
        return result;
    }

    // Post-order serialization
    private String serialize(TrieNode node) {
        if (node.children.isEmpty()) return "";

        List<String> serials = new ArrayList<>();
        for (String childName : node.children.keySet()) {
            TrieNode child = node.children.get(childName);
            String serial = serialize(child);
            serials.add("(" + childName + serial + ")");
        }

        Collections.sort(serials);  // order-independent comparison
        String fullSerial = String.join("", serials);

        serialMap.computeIfAbsent(fullSerial, k -> new ArrayList<>()).add(node);
        return fullSerial;
    }

    // DFS to collect valid paths
    private void dfs(TrieNode node, List<String> path, List<List<String>> result) {
        for (TrieNode child : node.children.values()) {
            if (!child.isDeleted) {
                path.add(child.name);
                result.add(new ArrayList<>(path));
                dfs(child, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
    }
