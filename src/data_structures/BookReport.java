package data_structures;

import java.util.Map;
import java.util.TreeMap;

/**
 * A simple use case of a TreeMap.
 */
public class BookReport implements Comparable<BookReport> {

    protected String bookTitle;
    protected String studentName;
    protected int numPages;

    public BookReport(String bookTitle, String studentName, int numPages) {
        this.bookTitle = bookTitle;
        this.studentName = studentName;
        this.numPages = numPages;
    }

    @Override
    public String toString() {
        return studentName + " wrote " + numPages + " pages on " + bookTitle + ".";
    }

    @Override
    public int compareTo(BookReport otherBookReport) {
        return numPages - otherBookReport.numPages;
    }

    public static void main(String[] args) {
        BookReport chris = new BookReport("The Cathedral and the Bazaar", "Chris", 50);
        BookReport ada = new BookReport("The Idea Factory", "Ada", 2);
        BookReport toby = new BookReport("Toby Tries a Taco", "Toby", 100);
        BookReport pooh = new BookReport("The Secret Life of Bees", "Pooh", 4);
        TreeMap<BookReport, Integer> reportScores = new TreeMap<>();

        reportScores.put(chris, 87);
        reportScores.put(ada, 30);
        reportScores.put(toby, 30);
        reportScores.put(pooh, 70);

        for (Map.Entry<BookReport, Integer> entry : reportScores.entrySet()) {
            BookReport reportInfo = entry.getKey();
            int score = entry.getValue();
            // sorted by the num of pages, ie the keys, the order determined by the compareTo method
            System.out.println(reportInfo + " " + score + " pts.");
        }
    }

}
