/**
 * Program primarily utilizes the Hashmap data structure
 * Designed to read in a list of strings and update the amount of which each string occurs
 * @author Joseph Tomada
 * @date 11/20/2020
 * @redID 824031774
 */

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class FrequencyCount {
    private final HashMap<String, Integer> tokens;
    private final List<String> text;
    private List<String> sortedKeys;

    /**
     * Used almost never
     */
    public FrequencyCount () {
        tokens = new HashMap<>();
        text = null;
    }

    /**
     * Constructor w/ parameters
     * Instantiates a HashMap
     * Keys: String based on degree (Ex. degree of four means four words)
     * Value: Integer value representing the frequency that which the key has been found
     * @param text
     * @param degree
     */
    public FrequencyCount (List<String> text, int degree) {
        tokens = new HashMap<>();
        this.text = text;

        StringBuilder key = new StringBuilder();
        int i = 0;

        while (i < text.size()) {
            for (int j = 0; j < degree && i < text.size(); ++j) {
                 key.append(text.get(i).toLowerCase()).append(" ");
                 ++i;
            }
            add(key.toString());
            key = new StringBuilder();
        }
        sortedKeys = sortedMap(); //used by the head() and tail() methods
    }

    /**
     * Primarily used by the head() and tail() methods
     * Sorts the Hashmap's keys based on its values (lowest to highest value)
     * Will utilize java Array class in order to sort the values
     * @return sortedList
     */
    private List<String> sortedMap () {
        List<String> sortedList = new ArrayList<>();
        HashMap<Integer, ArrayList<String>> tempMap = new HashMap<>();

        for (String token : tokens.keySet()) {

            if(!tempMap.containsKey(count(token))) {
                tempMap.put(count(token), new ArrayList<>());

            }
            tempMap.get(count(token)).add(token);

        }

        //source: https://www.baeldung.com/java-hashmap-sort
        List<Integer> tempList = new ArrayList<>(tempMap.keySet());
        Collections.sort(tempList);

        for (Integer tempKey : tempList) {
            sortedList.addAll(tempMap.get(tempKey));
        }

        return sortedList;
    }

    /**
     * Method utilizes the sortedMap and returns the most frequently
     * used key at a maximum of 20 keys
     * starts from highest to lowest frequencies
     * @return head
     */
    public List<String> head() {
        List<String> head = new ArrayList<>();
        final int SIZE = 20;

        for(int i = 0; i < SIZE && i < sortedKeys.size(); ++i) {
            head.add(sortedKeys.get(sortedKeys.size() - 1 - i));
        }
        return head;
    }

    /**
     * Method utilizes the sortedMap and returns the least
     * frequently used keys at a maximum of 20 keys
     * Descends from lowest to highest frequencies
     * @return tail
     */
    public List<String> tail() {
        List<String> tail = new ArrayList<>();
        final int SIZE = 20;

        for (int i = 0; i < SIZE && i < sortedKeys.size(); ++i) {
            tail.add(0, sortedKeys.get(i));
        }
        return tail;
    }

    /**
     * Method takes in a token returns its frequency
     * If token does not exist, method will, instead, return 0
     * @param token
     * @return frequency
     */
    public int count(String token) {
        if (tokens.containsKey(token)) {
            return (int) tokens.get(token);
        }
        else {
            return 0;
        }
    }

    /**
     * Method reads in a token and returns the percentage
     * of which it appears
     * Accomplishes this by dividing the amount of words the token has used by total word count
     * 4 significant figures
     * @param token
     * @return double percent
     */
    public double percent(String token) {
        DecimalFormat decFormat = new DecimalFormat("####0.000");
        double percent;
        String[] words = token.split(" ");
        final int WORD_COUNT = text.size();
        final int TOKEN_WORD_COUNT = words.length;

        percent = (double) count(token) * TOKEN_WORD_COUNT / WORD_COUNT;
        return Double.parseDouble(decFormat.format(percent));

    }

    /**
     * Method utilizes java Random class
     * Creates a random index and iterates through the keySet
     * until it is at the given index
     * String from the index is returned
     * @return randomToken
     */
    public String randomToken() {
        Random rand = new Random();
        String randomToken = "";
        int index = rand.nextInt(tokens.size());
        int i = 0;

        for(String key : tokens.keySet()) {
            if (i == index) {
                randomToken = key;
                break;
            }
            ++i;
        }

        return randomToken;
    }

    /**
     * Method takes in a token and updates its frequency
     * If the key already exists, the value is incremented
     * Else, a new token is created.
     * @param token
     * @return frequency
     */
    public int add(String token) {
        if (tokens.containsKey(token)) {
            tokens.put(token, tokens.get(token) + 1);
            return tokens.get(token) + 1;
        }
        else {
            tokens.put(token, 1);
            return 1;
        }
    }

}