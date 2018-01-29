/**
 * Pulled in as part of the Trie class.
 * Credit for this work is cited in Trie.java.
 * */

public class TrieNode {
    TrieNode[] arr;
    boolean isEnd;
    // Initialize your data structure here.
    public TrieNode() {
        this.arr = new TrieNode[26];
    }
}
