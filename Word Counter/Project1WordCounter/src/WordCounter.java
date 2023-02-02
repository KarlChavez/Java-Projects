import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Given a file, user outputs a single well-formed HTML file displaying table
 * listing the words and their corresponding counts
 *
 * @author Karl Chavez
 */
public final class WordCounter {

    /**
     * Compares words alphabetically while ignoring cases
     *
     * @requires [the relation computed by order.compare is total pre-order]
     *
     * @ensures this = [#this ordered by the relation computed by order.compare]
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2); //Ignores Cases
        }
    }

    /**
     *
     * Obtain words and its occurrences in a given file and stores it inside a
     * Map
     *
     * @param input
     *            strings in the file
     *
     * @return A Map of each word and its occurrences
     *
     * @requires {@code input} consist of strings
     *
     * @ensures Every word in the file is included in the Map with its
     *          occurrences
     */
    public static Map<String, Integer> obtainWords(SimpleReader input) {

        Map<String, Integer> mapWords = new Map1L<>(); //Map of Strings and Integers
        while (!input.atEOS()) {
            String line = input.nextLine(); //Obtaining lines from file
            int len = line.length(); //length of string

            String word; //Contains a word in the string

            int i; //Variable for first loop
            int k; //Variable for second loop
            for (i = 0; i < len; i++) {
                if (Character.isLetter(line.charAt(i))) //Start of a word
                {
                    for (k = i; k < len; k++) {
                        if (k == len - 1) { //Reached the end of the string and last character isn't a character
                            if (!Character.isLetter(line.charAt(k))) {
                                word = line.substring(i, k);
                                if (mapWords.hasKey(word)) {
                                    mapWords.replaceValue(word,
                                            mapWords.value(word) + 1);
                                } else {
                                    mapWords.add(word, 1);
                                }
                            } else { //Reached the end of the string and last character is a character
                                word = line.substring(i, k + 1);
                                if (mapWords.hasKey(word)) {
                                    mapWords.replaceValue(word,
                                            mapWords.value(word) + 1);
                                } else {
                                    mapWords.add(word, 1);
                                }
                            }
                            i = k;
                        } else { //Finding words in string
                            if (!Character.isLetter(line.charAt(k))) {
                                word = line.substring(i, k);
                                if (mapWords.hasKey(word)) {
                                    mapWords.replaceValue(word,
                                            mapWords.value(word) + 1);
                                } else {
                                    mapWords.add(word, 1);
                                }
                                i = k;
                                k = len; //Break

                            }
                        }
                    }
                }
            }
        }
        return mapWords;
    }

    /**
     *
     * Prints out each word in the Map with its count
     *
     * @param out
     *            the output stream
     * @param mapWords
     *            Map the contains each word and its occurrences
     *
     * @requires {@code out} is an open file
     *
     * @updates {@code out} and {@code mapWords}
     *
     * @ensures Each word and its occurrences are displayed in the table of
     *          {code out}
     */
    public static void printWords(SimpleWriter out,
            Map<String, Integer> mapWords) {

        Queue<String> sortedWords = new Queue1L<>(); //Queue that contains all the words and are sorted
        Map<String, Integer> temp = mapWords.newInstance(); //Temporary Map variable
        temp.transferFrom(mapWords);

        while (temp.size() > 0) { //Takes a random pair and adds the word onto the queue
            Map.Pair<String, Integer> any = temp.removeAny();
            sortedWords.enqueue(any.key()); //Adds it to the queue
            mapWords.add(any.key(), any.value()); //Adds it back to the original Map
        }

        Comparator<String> alpha = new StringLT(); //Sorting alphabetically
        sortedWords.sort(alpha); //Sorts the queue

        String word; //Holds a word in a queue
        while (sortedWords.length() > 0) //Iterates each word in the queue and displays the word and the count in the HTML
        {
            out.println("<tr>");
            word = sortedWords.dequeue();
            out.println("<td>" + word + "</td>");
            out.println("<td>" + mapWords.value(word) + "</td>");
            out.println("</tr");
        }
    }

    /**
     *
     * Prints Header in HTML
     *
     * @param out
     *            the output stream
     *
     * @param fileName
     *            the name of the input file
     *
     * @updates {code @out}
     *
     * @requires {code @out} is an open file
     *
     * @ensures the header of the HTML is included in {code @out}
     */
    public static void printHeader(SimpleWriter out, String fileName) {
        out.println("<html>");
        out.println("<style>");
        out.println("table, th, td {");
        out.println("  border:1px solid black;");
        out.println("<}>");
        out.println("</style>");
        out.println("<body>");
        out.println("<h2>" + "Words Counted in " + fileName + "<h2>");
        out.println("<table style=\"width:10%\">");
        out.println("<tr>");
        out.println("<th>Words</th>");
        out.println("<th>Counts</th>");
        out.println("</tr>");
    }

    /**
     *
     * Prints Closing Header in HTML
     *
     * @param out
     *            the output stream
     *
     * @updates {code @out}
     *
     * @requires {code @out} is an open file
     *
     * @ensures the closing header of the HTML is included in {code @out}
     */
    public static void printClosing(SimpleWriter out) {
        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        //Input file
        out.println("Name of an input file: ");
        String inputFile = in.nextLine();
        SimpleReader infile = new SimpleReader1L(inputFile);

        //Output file
        out.println("Name of an output file: ");
        String outputFile = in.nextLine();
        SimpleWriter outfile = new SimpleWriter1L(outputFile);

        //Prints Header of the HTML file
        printHeader(outfile, inputFile);

        //Map containing all the words and its count
        Map<String, Integer> mapWords = obtainWords(infile);

        //Prints each word and their count on the HTML file
        printWords(outfile, mapWords);

        //Prints Closing of the HTML file
        printClosing(outfile);

        //Closing files
        in.close();
        out.close();
        infile.close();
        outfile.close();
    }
}
