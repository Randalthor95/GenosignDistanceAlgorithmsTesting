import java.io.*;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Testing {

    /* Tests accuracy of distance methods. Outputs an ArrayList containing the number of best matches for each method
    * Entry 0=Jaro, ...
    * For each mutation if the mutation receives the highest score when compared to the rest of the genes it is
    * considered a match as this would indicate the algorithm correctly guessing which entry was the mutated
    * signed tag. The matches for each algorithm are summed.
    */
    public static ArrayList<Integer> test_accuracy_of_methods
            (String original_gene, ArrayList<String> gene_sequence, ArrayList<String> mutations) {
        ArrayList<Integer> results = new ArrayList<Integer>(1);


        //Jaro
        ArrayList<Double> scores = new ArrayList<Double>();

        for (int i = 0; i < gene_sequence.size(); ++i) {
            scores.add(Algorithms.jaro(original_gene, gene_sequence.get(i)));
        }
        double max = Collections.max(scores);

        int jaro_matches = 0;
        for (int i = 0; i < mutations.size(); ++i) {

            if (Algorithms.jaro(original_gene, mutations.get(i)) >= max)
                jaro_matches++;

        }
        results.add(jaro_matches);


        //Next Algorihtm

        return results;
    }

    /* Reads in gene sequences from a file, assume genes are on seperate lines
    */
    public static ArrayList<String> read_gene_sequence_from_file(String input_file) {
        ArrayList<String> genes = new ArrayList<String>();

        try {
            File file = new File(input_file);
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(file));


            String current_line;
            while ((current_line = br.readLine()) != null) {
                String[] words = current_line.split(" ", 0);
                for (int i = 0; i < words.length; ++i) {
                    genes.add(words[i]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return genes;

    }

    /* Removes number from a file containg gene sequences. Use to clean genes file from a .gb
    */
    public static void clean_file(String input_file) {
        try {

            File file = new File(input_file);

            BufferedReader br = null;

            br = new BufferedReader(new FileReader(file));


            String current_line;
            ArrayList<String> genes = new ArrayList<String>();
            while ((current_line = br.readLine()) != null) {
                String[] words = current_line.split(" ", 0);
                for (int i = 0; i < words.length; ++i) {
                    if (!isNumeric(words[i]))
                        genes.add(words[i]);
                }
            }
            br.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(input_file));
            for (int i = 0; i < genes.size(); ++i) {
                writer.append(genes.get(i));
                writer.append("\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static boolean isNumeric(String str) {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

}
