
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    /**
     * Parses a text file and for every valid line creates a Sentence object.
     *
     * @param filename the fully qualified name of the file to parse
     * @return a list of valid sentences from the file or empty list if the file is null or can not
     * be read
     */
    public static List<Sentence> readFile(String filename) {
        List<Sentence> sentences = new ArrayList<>();
        if (filename == null) {
            return sentences;
        }
        Path file = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {

                if (isValidSentence(line)) {
                    int score = Integer.parseInt(line.substring(0, 2).trim());
                    String text = line.substring(line.indexOf(' ') + 1);
                    sentences.add(new Sentence(score, text));
                }
            }
        } catch (IOException x) {
            return sentences;
        }
        return sentences;
    }

    /**
     * Determines if the string can represent a valid Sentence object.
     *
     * @param sentence the sentence to test
     * @return true if the string can represent a valid Sentence, false otherwise
     */
    private static boolean isValidSentence(String sentence) {
        if (sentence.length() < 3) {
            return false;
        }
        int offset = (sentence.charAt(0) == '-') ? 1 : 0;
        if (Character.isDigit(sentence.charAt(0 + offset))) {
            int score = Integer.parseInt(sentence.substring(0, 2).trim());
            if (score < -2 || score > 2) {
                return false;
            }
        } else {
            return false;
        }
        if (!Character.isSpaceChar(sentence.charAt(1 + offset))
                || !Character.isLetter(sentence.charAt(2 + offset))) {
            return false;
        }
        return true;
    }

    /**
     * Finds all of the individual words of each Sentence in the List and creates Word objects for
     * each distinct word. The Word objects keep track of the number of occurrences of that word in
     * all Sentences, and the total cumulative score of all Sentences in which it appears.
     *
     * @param sentences the Sentence object from which to extract words
     * @return a Set of valid Words from the sentences or an empty set if the sentences list is null
     * or empty
     */
    public static Set<Word> allWords(List<Sentence> sentences) {
        if (sentences == null || sentences.isEmpty()) {
            return new HashSet<>(0);
        }
        HashMap<Integer, Word> wordMap = new HashMap<>(sentences.size() * 3);
        for (Sentence sentence : sentences) {
            if (sentence != null) {
                String[] stringWords = sentence.getText().toLowerCase().split(" ");
                for (String stringWord : stringWords) {
                    if (Character.isLetter(stringWord.charAt(0))) {
                        Word word = new Word(stringWord);
                        word.increaseTotal(sentence.getScore()); // also increments count
                        int key = word.hashCode();

                        if (wordMap.containsKey(key)) {
                            wordMap.get(key).increaseTotal(sentence.getScore());
                        } else {
                            wordMap.put(key, word);
                        }
                    }
                }
            }
        }
        return new HashSet<>(wordMap.values());
    }

    /**
     * Calculates the average sentiment score for a Word, and then places the text of the Word (as
     * key) and calculated score (as value) in a Map.
     *
     * @param words the Word objects whose score to calculate
     * @return a map of the words and their sentiment scores or an empty map if the word list is
     * null or empty
     */
    public static Map<String, Double> calculateScores(Set<Word> words) {
        if (words == null || words.isEmpty()) {
            return new HashMap<>(0);
        }
        HashMap<String, Double> wordMap = new HashMap<>(words.size());
        for (Word word : words) {
            if (word != null) {
                wordMap.put(word.getText(), word.calculateScore());
            }
        }
        return wordMap;
    }

    /**
     * This method uses the given mapping to calculate and return the average score of all the words
     * in the input sentence.
     *
     * @param wordScores the mapping of words to their average sentiment score
     * @param sentence the string whose score to calculate
     * @return the average score of all the words in the input sentence
     */
    public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
        if (wordScores == null || wordScores.isEmpty() || sentence == null || sentence.isEmpty()) {
            return 0;
        }
        String[] stringWords = sentence.toLowerCase().split(" ");
        boolean validWord = false;
        int totalWords = 0;
        double totalScore = 0;

        for (String stringWord : stringWords) {
            if (Character.isLetter(stringWord.charAt(0))) {
                validWord = true;
                totalWords++;
                totalScore += wordScores.getOrDefault(stringWord, 0.0);
            }
        }
        return (validWord) ? (totalScore / totalWords) : 0;
    }

}
