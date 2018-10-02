import java.io.*;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collections;

public class Testing {

    /* Tests accuracy of distance methods. Outputs an ArrayList containing the number of best matches for each method
    * Entry 0=Jaro, ...
    * For each mutation if the mutation receives the highest score when compared to the rest of the genes it is
    * considered a match as this would indicate the algorithm correctly guessing which entry was the mutated
    * signed tag. The matches for each algorithm are summed.
    */
    static ArrayList<Integer> test_accuracy_of_methods
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


        //Levenshtein
        max = Collections.max(scores);

        int levenshtein_matches = 0;
        for (int i = 0; i < mutations.size(); ++i) {

            if (Algorithms.levenshtein_distance(original_gene, mutations.get(i)) >= max)
                levenshtein_matches++;

        }
        results.add(levenshtein_matches);

        //Jaccard
        max = Collections.max(scores);

        int Jaccard_matches = 0;
        for (int i = 0; i < mutations.size(); ++i) {

            if (Algorithms.jaccard_similarity(original_gene, mutations.get(i)) >= max)
                Jaccard_matches++;
        }
        results.add(Jaccard_matches);

        return results;
    }

    /* Prints the testing results for the given mutations sets
    *
     */
    static void print_results_of_tests
    (String original_gene, ArrayList<String> gene_sequence,
     ArrayList<ArrayList<String>> mutations_sets, ArrayList<String> mutations_sets_names) {

        ArrayList<ArrayList<Integer>> matches = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < mutations_sets.size(); ++i) {
            matches.add(Testing.test_accuracy_of_methods(original_gene, gene_sequence, mutations_sets.get(i)));
        }


        System.out.println("          Jaro,    Levenshtein,   Jaccard");
        for(int i = 0; i< mutations_sets.size(); ++i) {
            System.out.print(mutations_sets_names.get(i) + ":  ");
            System.out.print( Integer.toString(matches.get(i).get(0)) + "/"
                    + Integer.toString(mutations_sets.get(i).size()) + "      ");
            System.out.print( Integer.toString(matches.get(i).get(1)) + "/"
                    + Integer.toString(mutations_sets.get(i).size()) + "          ");
            System.out.print( Integer.toString(matches.get(i).get(2)) + "/"
                    + Integer.toString(mutations_sets.get(i).size()) + " ");
            System.out.println("");
        }


    }

    static void print_test_results_for_n_errors
            (String original_gene, ArrayList<String> gene_sequence, int number_of_errors) {
        ArrayList<ArrayList<String>> mutations_sets = new ArrayList<ArrayList<String>>();
        ArrayList<String> mutations_sets_names = new ArrayList<>();

        for(int i = 1; i <= number_of_errors; ++i) {
            ArrayList<String> mutations1 = Mutation_Generation.generate_substitution_mutation_permutations(original_gene, i);
            mutations_sets.add(mutations1);
            mutations_sets_names.add(i +" Subst.");

            ArrayList<String> mutationsd1 = Mutation_Generation.generate_deletion_mutation_permutations(original_gene, i);
            mutations_sets.add(mutationsd1);
            mutations_sets_names.add(i +" Delete");

            ArrayList<String> mutationsi1 = Mutation_Generation.generate_insertion_mutation_permutations(original_gene, i);
            mutations_sets.add(mutationsi1);
            mutations_sets_names.add(i + " Insert");

        }


        Testing.print_results_of_tests(original_gene, gene_sequence, mutations_sets,  mutations_sets_names);
    }


    /* Reads in gene sequences from a file, assume genes are on seperate lines
    */
    static ArrayList<String> read_gene_sequence_from_file(String input_file) {
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
    private static void clean_file(String input_file) {
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

    private static boolean isNumeric(String str) {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

}
