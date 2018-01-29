import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Initialising class, responsible for determining when word squares are to be created.
 * */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter man to manually enter values or auto to run automatically");

        String choice = scanner.nextLine();

        if(choice.toLowerCase().equals("man")) {
            while(true) {
                System.out.println("Enter string to find word square: ");

                String stringInput = scanner.next();

                if(stringInput.equals("stop")) {
                    break;
                }

                System.out.println("Enter the size of the word square: ");

                String lengthInput = scanner.next();

                //Create word square based on user input
                WordSquareCreator creator = new WordSquareCreator(Integer.valueOf(lengthInput), stringInput);
                creator.getWordSquare(stringInput);
            }
        }
        else {
            try(FileInputStream inputStream = new FileInputStream("resources/autoRun.txt")) {
                String autoInput = IOUtils.toString(inputStream);
                String[] tests = autoInput.split("\n");
                int iterator = 1;
                //Creates a word square for each line in autoRun.txt
                for(String test : tests) {
                    //Time was added here to show the complexity/simplicity of the the program
                    long startTime = System.nanoTime();

                    System.out.println("Running task " + iterator);
                    String[] lengthAndInput = test.split(",");
                    System.out.println();
                    WordSquareCreator creator = new WordSquareCreator(Integer.valueOf(lengthAndInput[0]), lengthAndInput[1]);
                    creator.getWordSquare(lengthAndInput[1]);
                    System.out.println();

                    long endTime = System.nanoTime();
                    long duration = (endTime - startTime);
                    double seconds = (double)duration / 1000000000.0;

                    System.out.println("Time: " + Double.toString(seconds) + " seconds");
                    iterator++;
                }
            } catch(Exception e) {
                System.out.println("Error reading in automated run file");
            }

        }
    }
}
