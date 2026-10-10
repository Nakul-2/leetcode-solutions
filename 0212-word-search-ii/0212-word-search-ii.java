
class Solution {
    
    // Define the TrieNode structure inside the solution class
    static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word; // Stores the complete word at the leaf node for easy retrieval
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        
        // Traverse every cell on the board as a potential starting point
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    private void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        // Boundary check and validation if the cell is already visited ('#')
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) {
            return; // Prune branch if char doesn't match any Trie path or is visited
        }
        
        // Move deeper into the Trie
        p = p.next[c - 'a'];
        
        // Found a complete word matching a path on the board
        if (p.word != null) {
            res.add(p.word);
            p.word = null; // De-duplicate: Ensure we don't pick the same word twice
        }

        // Mark the current cell as visited to prevent reuse in the current path
        board[i][j] = '#';
        
        // Explore all 4 adjacent directions
        dfs(board, i - 1, j, p, res); // Up
        dfs(board, i + 1, j, p, res); // Down
        dfs(board, i, j - 1, p, res); // Left
        dfs(board, i, j + 1, p, res); // Right
        
        // Backtrack: Restore the original character for other search paths
        board[i][j] = c;
    }

    // Helper method to build the Prefix Tree from the dictionary list
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) {
                    p.next[i] = new TrieNode();
                }
                p = p.next[i];
            }
            p.word = w; // Store the word at the terminal node
        }
        return root;
    }
}
