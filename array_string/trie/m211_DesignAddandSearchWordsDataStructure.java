// https://leetcode.com/problems/design-add-and-search-words-data-structure/description/

// Solution 1: basic trie
// NOTICE: the "char c;" in TrieNode is not needed

class WordDictionary {
    class TrieNode {
        char c;
        Map<Character, TrieNode> children;
        boolean isWord;

        TrieNode(char c, boolean isWord) {
            this.c = c;
            this.isWord = isWord;
            this.children = new HashMap<>();
        }
    }

    TrieNode root;

    public WordDictionary() {
        root = new TrieNode('0', false);
    }
    
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            } else {
                TrieNode newNode = new TrieNode(c, false);
                node.children.put(c, newNode);
                node = newNode;
            }
        }
        node.isWord = true;
    }
    
    public boolean search(String word) {
        return search(root, word);
    }

    private boolean search(TrieNode curRoot, String word) {
        TrieNode node = curRoot;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.') {
                for (TrieNode nextRoot : node.children.values()) {
                    if (search(nextRoot, word.substring(i + 1, word.length()))) {
                        return true;
                    }
                }
                // if all children cannot match => return false;
                return false;
            } else {
                node = node.children.get(c);
                if (node == null) {
                    return false;
                }
            }
        }
        return node.isWord;
    }
}

// Solution 2: trie optimized for lower case characters
class WordDictionary {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }

    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }
    
    public boolean search(String word) {
        return search(root, word);
    }

    private boolean search(TrieNode curRoot, String word) {
        TrieNode node = curRoot;
        int l = word.length();
        for (int i = 0; i < l; i++) {
            char c = word.charAt(i);
            if (c == '.') {
                for (TrieNode nextRoot : node.children) {
                    if (nextRoot == null) continue;
                    if (search(nextRoot, word.substring(i + 1, l))) {
                        return true;
                    }
                }
                // if all children cannot match => return false;
                return false;
            } else {
                node = node.children[c - 'a'];
                if (node == null) {
                    return false;
                }
            }
        }
        return node.isWord;
    }
}
