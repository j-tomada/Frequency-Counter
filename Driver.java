/**
 * Driver Program used to test the FrequencyCount class
 * User will enter in a text file via the command line
 * Program will use the FrequencyCount class to read in the text file and
 * display the most/least frequently used words
 * @author Joseph Tomada
 * @date 11/20/2020
 * @redID 824031774
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;

public class Driver {

    /**
     * Private method for transferring Strings from a textFile into a list
     * Will throw FileNotFoundException in case main method fails to catch one
     * @param textFile
     * @return newList
     * @throws FileNotFoundException
     */
    private static List<String> createList(File textFile) throws FileNotFoundException {
        List<String> newList = new ArrayList<>();
        Scanner fileScnr = new Scanner(textFile);
        String currLine;

        while(fileScnr.hasNext()) {
            currLine = fileScnr.nextLine();

            if(!currLine.equals("")) { //Blank lines
                currLine = currLine.replaceAll("\t", "");
                currLine = currLine.replaceAll("\n", ""); //Ensures newLine and tab aren't read

                String[] lineArray = currLine.split(" ");

                for (String s : lineArray) {
                    if (!(s.contains(" ") || s.equals(""))) { //Fixes bug where blanks were counted as tokens
                        newList.add(s);
                    }
                }
            }

        }

        return newList;
    }

    public static void main (String[] args) {
        if (args.length != 1) { //Program will not continue unless command line it properly utilized
            System.out.println("Use: java Driver file.txt");
        }
        else {
            try { //Try/catch used to catch a FileNotFound Exception
                File textFile = new File(args[0]);

                if (!textFile.exists()) { //Throw  FileNotFound if it does not exist
                    throw new FileNotFoundException();
                }

                List<String> textList = createList(textFile); //Can Throw FileNotFound Exception
                FrequencyCount frequency = new FrequencyCount(textList, 1);

                System.out.println("Most frequent tokens (head) degree of one: ");

                for(String key : frequency.head()) { //head printed here
                    System.out.print(key + " -> " +frequency.count(key));
                    System.out.print(" Percent: " +frequency.percent(key));
                    System.out.println();
                }

                System.out.println();
                System.out.println("Least frequent tokens (tail) degree of one: ");

                for(String key : frequency.tail()) { //tail printed here
                    System.out.print(key + "-> " +frequency.count(key));
                    System.out.print(" Percent: " +frequency.percent(key));
                    System.out.println();
                }

                System.out.println();
                System.out.println("Extracting tokens to a degree of four");

                frequency = new FrequencyCount(textList, 4);
                System.out.println("Creating random poem\n");

                System.out.println(frequency.randomToken());
                System.out.println(frequency.randomToken() + frequency.randomToken());
                System.out.println(frequency.randomToken());
            }
            catch (FileNotFoundException excpt) {
                System.out.println("Error: file has not been found (Maybe spelling error)");
            }
        }
    }
}
