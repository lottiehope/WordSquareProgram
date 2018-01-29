import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class that takes an input and prints a word square that is valid.
 * */
public class WordSquareCreator {

    private Trie wordTrie;
    private int wordSize;

    public WordSquareCreator(int wordLength, String inputCharacters) {
        WordChecker wc = new WordChecker();
        wordSize = wordLength;
        wordTrie = wc.findValidWords(wordLength, inputCharacters);
    }

    /**
     * Given a list of words, it will find the prefix for the next word, e.g. for {shop,rose} the prefix would be 'os'
     * */
    protected String getPrefix(ArrayList<String> foundWords) {
        int noOfWordsFound = foundWords.size();
        String prefix = "";
        try {
            for (String word : foundWords) {
                prefix += word.charAt(noOfWordsFound);
            }
        } catch (StringIndexOutOfBoundsException e) {
            // This will happen when the word square is full

            // .. Log this to say that a word square got to 4 values, but not checked to see if valid yet
        }
        return prefix;
    }

    /**
     * Returns a list of words that begin with a pre-defined prefix.
     * */
    private ArrayList<String> findWordsThatStartWith(String beginningChars) {
        ArrayList<String> possibleSolutions = new ArrayList<>();
        for(String word : wordTrie.possibleWords) {
            if (word.startsWith(beginningChars)) {
                possibleSolutions.add(word);
            }
        }

        return possibleSolutions;
    }

    /**
     * Checks to ensure a word fits in with the characters available in the input string.
     * */
    protected boolean checkWordFits(String word, String charList) {
        ArrayList<String> availableChars = new ArrayList<>();
        for(char character : charList.toCharArray()) {
            availableChars.add(String.valueOf(character));
        }

        for (char character : word.toCharArray()) {
            if (availableChars.contains(String.valueOf(character))) {
                availableChars.remove(String.valueOf(character));
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * If a word is added to a queue item, the input characters needs to be updated with the new available characters.
     * This method removes all of the characters of a word from the input string and returns the new string - the word.
     * */
    private String removeCharactersFromInputString(String word, String charList) {
        ArrayList<String> availableChars = new ArrayList<>();
        for(char character : charList.toCharArray()) {
            availableChars.add(String.valueOf(character));
        }

        for (char character : word.toCharArray()) {
            availableChars.remove(String.valueOf(character));
        }

        String result = "";
        for(String letter : availableChars) {
            result += letter;
        }

        return result;
    }

    /**
     * Tries to create word squares, beginning with adding an item to the queue for every potential word in the
     * dictionary. Then iterating through them to see if there is the potential to continue the word square with a
     * valid outcome. It will print the word square once the queue item contains the maximum number of words.
     * */
    protected void getWordSquare(String inputCharacters) {

        Queue<QueueItem> queue = new LinkedList<>();
        QueueItem item = new QueueItem();
        item.inputCharacters = inputCharacters;

        queue.add(item);

        for(String word : wordTrie.possibleWords) {
            QueueItem originItem = new QueueItem();
            originItem.items.add(word);
            originItem.inputCharacters = removeCharactersFromInputString(word, inputCharacters);
            queue.add(originItem);
        }

        while(!queue.isEmpty()) {

            //Get the top item off the queue
            QueueItem currentItem = queue.remove();

            //If it had wordsize words then done
            if(currentItem.items.size() == wordSize) {
                for(String word : currentItem.items) {
                    System.out.println(word);
                }
                break;
            }

            //Get the new prefix based on how many words are in the queue item
            String prefix = getPrefix(currentItem.items);

            //Find all the words that start with that prefix
            for(String nextWord : findWordsThatStartWith(prefix)) {

                QueueItem tempQueueItem = new QueueItem();
                tempQueueItem.items.addAll(currentItem.items);
                tempQueueItem.inputCharacters = currentItem.inputCharacters;

                ArrayList<String> tempChosenWords = new ArrayList<>();

                //Add words currently in item
                tempChosenWords.addAll(tempQueueItem.items);

                //Add the next word to try
                tempChosenWords.add(nextWord);

                //Create prefix with the stored words
                String newPrefix = getPrefix(tempChosenWords);

                //If there are no words with that prefix then skip it
                if(tempChosenWords.size() < wordSize) {
                    if (!wordTrie.startsWith(newPrefix)) {
                        continue;
                    }
                }
                //If there aren't enough characters to make the word, skip it
                if(!checkWordFits(nextWord, currentItem.inputCharacters)) {
                    continue;
                }

                //Add the current item to the queue
                tempQueueItem.inputCharacters = removeCharactersFromInputString(nextWord, tempQueueItem.inputCharacters);
                tempQueueItem.items.add(nextWord);
                queue.add(tempQueueItem);
            }
        }
    }
}
