package data_structures;

import java.util.LinkedList;
import java.util.Scanner;

public class LinkedListExampleUse {

    public static void main(String[] args) {

        LinkedList<Integer> numbers = new LinkedList<>();

        Scanner in = new Scanner(System.in);
        int input = 0;

        while (true) {
            System.out.println("Enter a number(0 to end): ");
            input = in.nextInt();
            if (input == 0) {
                break;
            }
            numbers.add(input);
        }
        System.out.println("Enter a number: ");
        input = in.nextInt();
        if (numbers.contains(input)) {
            System.out.println(input + " is in the list");
        } else {
            System.out.println(input + " is NOT in the list");
        }

    }
}
