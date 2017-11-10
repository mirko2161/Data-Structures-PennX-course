package data_structures;

import java.util.HashMap;
import java.util.Scanner;

public class HashMapExampleUse {

    public static void main(String[] args) {

        // maps each word to the number of occurences
        HashMap<String, Integer> wordCount = new HashMap<>();
        Scanner in = new Scanner(System.in);
        String input = null;

        while (true) {
            System.out.print("Enter a string(q to end): ");
            input = in.nextLine();
            if (input.equals("q")) {
                break;
            }
            if (wordCount.containsKey(input)) {
                int count = wordCount.get(input);
                wordCount.put(input, count + 1);
            } else {
                wordCount.put(input, 1);
            }
        }

        System.out.print("Enter another string: ");
        input = in.nextLine();

        if (wordCount.containsKey(input)) {
            int count = wordCount.get(input);
            System.out.println(input + " appears " + count + " times");
        } else {
            System.out.println(input + " is not in the map");
        }

    }

}
