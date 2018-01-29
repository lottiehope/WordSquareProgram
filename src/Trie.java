import java.util.ArrayList;


/**
 * Implemented as a class instead of importing a library as there wasn't the correct functionality in the
 * corresponding library. Given more time, this would be personally implemented or an applicable library would be found.
 * However, due to time constraints, this was the chosen solution to optimise the time taken to create a word square.
 * */
// Source:  https://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public ArrayList<String> possibleWords = new ArrayList<>();

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode p = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            int index = c-'a';
            if(p.arr[index]==null){
                TrieNode temp = new TrieNode();
                p.arr[index]=temp;
                p = temp;
            }else{
                p=p.arr[index];
            }
        }
        p.isEnd=true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode p = searchNode(word);
        if(p==null){
            return false;
        }else{
            if(p.isEnd)
                return true;
        }

        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode p = searchNode(prefix);
        if(p==null){
            return false;
        }else{
            return true;
        }
    }

    public TrieNode searchNode(String s){
        TrieNode p = root;
        for(int i=0; i<s.length(); i++){
            char c= s.charAt(i);
            int index = c-'a';
            if(p.arr[index]!=null){
                p = p.arr[index];
            }else{
                return null;
            }
        }

        if(p==root)
            return null;

        return p;
    }
}
