// https://leetcode.com/problems/implement-trie-prefix-tree/description/

// Use same private function searchPrefix to use for both functions search and startsWith
// Can make the links HashMap, to handle generic cases.

class Trie {
    private TrieNode root;

    /**
     * Initialize the trie data structure.
     */
    public Trie() {
        root = new TrieNode();
    }
    
    /**
     * Inserts a word into the trie.
     * Time Complexity: O(m) where m is the length of the word
     */
    public void insert(String word) {
        TrieNode node = root;
        
        for (char c : word.toCharArray()) {
            if (!node.containsKey(c)) {
                node.put(c, new TrieNode());
            }
            node = node.get(c);
        }
        
        node.setEnd();
    }
    
    /**
     * Returns true if the word is in the trie.
     * Time Complexity: O(m) where m is the length of the word
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }
    
    /**
     * Returns true if there is any word in the trie that starts with the given prefix.
     * Time Complexity: O(m) where m is the length of the prefix
     */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
    
    /**
     * Helper method to search a prefix or whole word in the trie
     * Returns the node where search ends or null if not found
     */
    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        
        for (char c : prefix.toCharArray()) {
            if (node.containsKey(c)) {
                node = node.get(c);
            } else {
                return null;
            }
        }
        
        return node;
    }
    
    /**
     * Private TrieNode class to represent each node in the Trie
     */
    private class TrieNode {
        // R links to node children (one for each lowercase letter)
        private TrieNode[] links;
        private final int R = 26; // assuming lowercase English letters only
        
        // isEnd flag to mark the end of a word
        private boolean isEnd;
        
        public TrieNode() {
            links = new TrieNode[R];
        }
        
        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }
        
        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }
        
        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }
        
        public void setEnd() {
            isEnd = true;
        }
        
        public boolean isEnd() {
            return isEnd;
        }
    }
}

/**
 * Usage example:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 */
