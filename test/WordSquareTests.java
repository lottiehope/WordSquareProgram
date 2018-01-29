import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * A set of tests that ensure the internal methods are running correctly.
 * */
public class WordSquareTests {

    @Test
    public void testWordFitsInInput() {
        WordSquareCreator creator = new WordSquareCreator(4, "eeeeddoonnnsssrv");
        Assert.assertEquals(true, creator.checkWordFits("rose", "eeeeddoonnnsssrv"));
    }

    @Test
    public void testWordDoesNotFitInInput() {
        WordSquareCreator creator = new WordSquareCreator(4, "eeeeddoonnnsssrv");
        Assert.assertEquals(false, creator.checkWordFits("yellow", "eeeeddoonnnsssrv"));
    }

    @Test
    public void testPrefixGivenTwoWords() {
        WordSquareCreator creator = new WordSquareCreator(4, "aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy");
        ArrayList<String> tempWords = new ArrayList<>();
        tempWords.add("test");
        tempWords.add("four");
        Assert.assertEquals("su", creator.getPrefix(tempWords));
    }

    @Test
    public void testPrefixGivenThreeWords() {
        WordSquareCreator creator = new WordSquareCreator(4, "aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy");
        ArrayList<String> tempWords = new ArrayList<>();
        tempWords.add("test");
        tempWords.add("four");
        tempWords.add("five");
        Assert.assertEquals("tre", creator.getPrefix(tempWords));
    }

}
