import java.util.*;


/**
 * @author Vishnu Prasad Korada
 * We can implement the Autocomplete system using a Trie. A Trie stands for Re'TRIE'val of values in a custom tree. We also use trie in FP growth algorithm.
 */



class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean isEnd;

    public TrieNode() {
        this.isEnd = false;
        this.children = new HashMap<>();
    }
}

class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insertWord(String s) {

        if (s.isEmpty() || s.isBlank()) {
            System.out.println("Empty word cannot be added.");
            return;
        }

        TrieNode current = root;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        current.isEnd = true;
        System.out.println("Word added successfully into the trie!");
    }

    public List<String> advancedSearch(String prefix) {
        List<String> words = new ArrayList<>();

        TrieNode current = root; 

        for(int i=0; i<prefix.length(); i++) {
            current = current.children.get(prefix.charAt(i));
            if(current == null) return words;
        }

        searchWords(current, words, prefix);
    
        

        return words;
    }

    private void searchWords(TrieNode current, List<String> words, String word) {
        if (current == null) return;
        
        if(current.isEnd) {
            words.add(word);
        }
        Map<Character, TrieNode> children = current.children;
        for (Character c: children.keySet()) {
            searchWords(children.get(c), words, word + String.valueOf(c));

        }

    }

}


public class AutoComplete {

    public static void main(String args[]){
        Trie trie = new Trie();
        trie.insertWord("then");
        trie.insertWord("the");
        trie.insertWord("their");
        trie.insertWord("there");
        trie.insertWord("tree");
        System.out.println(trie.advancedSearch("the"));

    }
}
