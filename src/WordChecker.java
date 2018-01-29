import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class responsible for finding words in the dictionary (located via URL).
 * It will only find words that are of the same length as the word square and will only return possible words given
 * the input to the word square creator.
 *
 * Please do bear in mind, the dictionary is not stored within the project, so there is some latency receiving it
 * via the internet. This can be changed to be stored locally if requested.
 * */
public class WordChecker {

    private String dictionary;

    public WordChecker() {
        readInDictionary();
    }

    /**
     * Opens the dictionary and stores it.
     * */
    private void readInDictionary() {
        try {
            InputStream stream = new URL("http://norvig.com/ngrams/enable1.txt").openStream();
            dictionary = IOUtils.toString(new InputStreamReader(stream, Charset.forName("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error retrieving dictionary");
        }
    }

    /**
     * Adds words to the tree that contain valid characters based on the input string.
     * */
    protected Trie findValidWords(int length, String characters) {
        //return getAllPossibleWords(length, characters);
        Scanner dictScanner = new Scanner(dictionary);
        Trie trie = new Trie();
        while (dictScanner.hasNextLine()) {
            String line = dictScanner.nextLine();
            if (line.length() == length){
                if(checkWordContainsCorrectCharacters(line, characters)){
                    trie.possibleWords.add(line);
                    trie.insert(line);
                }
            }
        }
        dictScanner.close();
        return trie;
    }

    /**
     * Checks to ensure the input string contains all the characters needed to create a word.
     * */
    private boolean checkWordContainsCorrectCharacters(String line, String characters) {
        char[] availableChars = characters.toCharArray();
        ArrayList<String> availableCharsAsStrings = new ArrayList<>();
        boolean possible = true;
        for(char character : availableChars) {
            availableCharsAsStrings.add(String.valueOf(character));
        }
        for(char character : line.toCharArray()){
            if(availableCharsAsStrings.contains(String.valueOf(character))){
                availableCharsAsStrings.remove(String.valueOf(character));
            } else { possible = false; }
        }
        return possible;
    }
    
    
}
