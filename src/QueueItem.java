import java.util.ArrayList;

/**
 * Class used to store the list of words that will make up a word square and the characters left from the initial
 * input.
 * Items will be added one at a time and the letters that an item is made up of will be removed from the input
 * characters.
 * */
public class QueueItem {

    public ArrayList<String> items = new ArrayList<>();
    public String inputCharacters = "";

}
