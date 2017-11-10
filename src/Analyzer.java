
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Note: Homework tests require that the class being tested is in the default package, and also that
 * the test files and .jar are in the root directory.
 *
 * SD2x Homework #3 Implement the methods below according to the specification in the assignment
 * description. Please be sure not to change the method signatures!
 *
 * In this assignment, you will write a program that will analyze the sentiment (positive or
 * negative) of a sentence based on the words it contains by implementing methods that use the List,
 * Set, and Map interfaces from the Java Collections API.
 *
 * Sentiment analysis is a task from the field of computational linguistics that seeks to determine
 * the general attitude of a given piece of text. For instance, we would like to have a program that
 * could look at the text “This assignment was joyful and a pleasure” and realize that it was a
 * positive statement while “It made me want to pull out my hair” is negative.
 *
 * One algorithm that we can use for this is to assign a numeric value to any given word based on
 * how positive or negative that word is and then determine the overall sentiment of the statement
 * based on the average values of the words.
 *
 * To determine the sentiment of an individual word, we can use a corpus of statements, each of
 * which has an overall score(from -2 to 2) already assigned to it. The sentiment of an individual
 * word equals the average of the statements in which that word appears.
 *
 * To determine the overall sentiment of the word “fun,” we take the average of the sentences in
 * which it appears. For example, (0 + 1 + -1 + 2 + 2) / 5 = 0.8. Then, given a new sentence, we can
 * determine its sentiment by computing the average of the sentiments of the individual words it
 * contains. The sentiment of any previously unseen word would be 0.
 *
 * Your program will be evaluated using a set of ~8,500 movie reviews that have been provided to you
 * in the file reviews.txt.
 *
 *
 * Activity Part 1. Reading the corpus as an input file
 *
 * Implement the readFile method in Analyzer.java. This method should take the name of the file to
 * read and read it one line at a time, creating Sentence objects and putting them into the List.
 * Note that the method returns a List containing Sentence objects.
 *
 * Your code should ignore (and not create a Sentence object for) any line that is not
 * well-formatted, which we assume to mean “starts with an int between -2 and 2 (inclusive), has a
 * single whitespace, and then is followed by more text.” However, if the file cannot be opened for
 * reading or if the input filename is null, this method should return an empty List.
 *
 *
 * Activity Part 2. Calculating the sentiment of each word
 *
 * Implement the allWords method in Analyzer.java (the same file as Part 1). This method should find
 * all of the individual tokens/words in the text field of each Sentence in the List and create Word
 * objects for each distinct word. The Word objects should keep track of the number of occurrences
 * of that word in all Sentences, and the total cumulative score of all Sentences in which it
 * appears. This method should then return a Set of those Word objects.
 *
 * If the input List of Sentences is null or is empty, the allWords method should return an empty
 * Set. If a Sentence object in the input List is null, this method should ignore it and process the
 * non-null Sentences.
 *
 * Keep in mind that when you tokenize the text of each Sentence, you will be getting Strings, but
 * the Set that this method returns needs to include Word objects. However, if two Strings are
 * equal, they should be combined into the same Word object, which should track the cumulative score
 * of all Sentences containing that String.
 *
 * As you may have noticed in the data file we provided, some tokens start with punctuation and the
 * first word of each sentence starts with a capital letter. In producing the Set of Words, your
 * program should ignore any token that does not start with a letter. Also, this method should
 * convert all strings to lowercase so that it is case-insensitive. Do not assume that the strings
 * in the Sentence objects have already been converted to lowercase.
 *
 *
 * Activity Part 3. Storing the sentiment of each word
 *
 * Implement the calculateScores method in Analyzer.java (the same file as Parts 1 and 2). This
 * method should iterate over each Word in the input Set, use the Word’s calculateScore method to
 * get the average sentiment score for that Word, and then place the text of the Word (as key) and
 * calculated score (as value) in a Map.
 *
 * If the input Set of Words is null or is empty, the calculateScores method should return an empty
 * Map. If a Word object in the input Set is null, this method should ignore it and process the
 * non-null Words.
 *
 *
 * Activity Part 4. Determining the sentiment of a sentence
 *
 * Finally, implement the calculateSentenceScore method in Analyzer.java. This method should use the
 * Map to calculate and return the average score of all the words in the input sentence.
 *
 * Note that you will need to tokenize/split the sentence, as you did in Part 2. If a word in the
 * sentence does not start with a letter, then you can ignore it, but if it starts with a letter and
 * is not present in the Map, assign it a score of 0.
 *
 * You may assume that all words in the Map consist only of lowercase letters (since this would have
 * been done in previous steps) but do not assume that all words in the input sentence consist only
 * of lowercase letters; you should convert them to lowercase before checking whether they’re
 * contained in the Map.
 *
 * If the input Map is null or empty, or if the input sentence is null or empty or does not contain
 * any valid words, this method should return 0.
 *
 * @author mirko
 */
public class Analyzer {

    /*
     * Implement this method in Part 1
     */
    public static List<Sentence> readFile(String filename) {

        /* IMPLEMENT THIS METHOD! */
        return null; // this line is here only so this code will compile if you don't modify it
    }

    /*
     * Implement this method in Part 2
     */
    public static Set<Word> allWords(List<Sentence> sentences) {

        /* IMPLEMENT THIS METHOD! */
        return null; // this line is here only so this code will compile if you don't modify it
    }

    /*
     * Implement this method in Part 3
     */
    public static Map<String, Double> calculateScores(Set<Word> words) {

        /* IMPLEMENT THIS METHOD! */
        return null; // this line is here only so this code will compile if you don't modify it
    }

    /*
     * Implement this method in Part 4
     */
    public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {

        /* IMPLEMENT THIS METHOD! */
        return 0; // this line is here only so this code will compile if you don't modify it
    }

    /*
     * This method is here to help you run your program.
     * You may modify it as needed.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify the name of the input file");
            System.exit(0);
        }
        String filename = args[0];
        System.out.print("Please enter a sentence: ");
        Scanner in = new Scanner(System.in);
        String sentence = in.nextLine();
        in.close();
        List<Sentence> sentences = Analyzer.readFile(filename);
        Set<Word> words = Analyzer.allWords(sentences);
        Map<String, Double> wordScores = Analyzer.calculateScores(words);
        double score = Analyzer.calculateSentenceScore(wordScores, sentence);
        System.out.println("The sentiment score is " + score);
    }

}
