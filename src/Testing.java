import java.io.*;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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

            double score = Algorithms.jaro(original_gene, mutations.get(i));
            if (score >= max) {
                jaro_matches++;
                System.out.println(mutations.get(i) + " " + Algorithms.jaro(original_gene, mutations.get(i)));
            }

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

        //damerauLevenshtein
        max = Collections.max(scores);

        int damerauLevenshtein_matches = 0;
        for (int i = 0; i < mutations.size(); ++i) {

            if (Algorithms.damerauLevenshteinDistance(original_gene, mutations.get(i)) >= max)
                damerauLevenshtein_matches++;
        }
        results.add(damerauLevenshtein_matches);

        //Optimal String Alignment
        max = Collections.max(scores);
        OptimalStringAlignment opti = new OptimalStringAlignment();
        int OptimalStringAlignment_matches = 0;
        for (int i = 0; i < mutations.size(); ++i) {

            if (OptimalStringAlignment.editDistance(original_gene, mutations.get(i), 3) >= max)
                OptimalStringAlignment_matches++;
        }
        results.add(OptimalStringAlignment_matches);

        return results;
    }

    /* Prints the testing results for the given mutations sets
     *
     */
    static void print_results_of_tests
    (String original_gene, ArrayList<String> gene_sequence,
     ArrayList<ArrayList<String>> mutations_sets, ArrayList<String> mutations_sets_names) {

        ArrayList<ArrayList<Integer>> matches = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < mutations_sets.size(); ++i) {
            matches.add(Testing.test_accuracy_of_methods(original_gene, gene_sequence, mutations_sets.get(i)));
        }


        System.out.println("          Jaro,    Levenshtein,   Jaccard,   damerauLevenshtein, Optimal Algn");
        for (int i = 0; i < mutations_sets.size(); ++i) {
            System.out.print(mutations_sets_names.get(i) + ":  ");
            System.out.print(Integer.toString(matches.get(i).get(0)) + "/"
                    + Integer.toString(mutations_sets.get(i).size()) + "      ");
            System.out.print(Integer.toString(matches.get(i).get(1)) + "/"
                    + Integer.toString(mutations_sets.get(i).size()) + "          ");
            System.out.print(Integer.toString(matches.get(i).get(2)) + "/"
                    + Integer.toString(mutations_sets.get(i).size()) + "           ");
            System.out.print(Integer.toString(matches.get(i).get(3)) + "/"
                    + Integer.toString(mutations_sets.get(i).size()) + "     ");
            System.out.print(Integer.toString(matches.get(i).get(4)) + "/"
                    + Integer.toString(mutations_sets.get(i).size()) + "");
            System.out.println("");
        }


    }

    static void print_test_results_for_n_errors
            (String original_gene, ArrayList<String> gene_sequence, int number_of_errors) {
        ArrayList<ArrayList<String>> mutations_sets = new ArrayList<ArrayList<String>>();
        ArrayList<String> mutations_sets_names = new ArrayList<>();

        for (int i = 1; i <= number_of_errors; ++i) {
            ArrayList<String> mutations1 = Mutation_Generation.generate_substitution_mutation_permutations(original_gene, i);
            mutations_sets.add(mutations1);
            mutations_sets_names.add(i + " Subst.");

            ArrayList<String> mutationsd1 = Mutation_Generation.generate_deletion_mutation_permutations(original_gene, i);
            mutations_sets.add(mutationsd1);
            mutations_sets_names.add(i + " Delete");

            ArrayList<String> mutationsi1 = Mutation_Generation.generate_insertion_mutation_permutations(original_gene, i);
            mutations_sets.add(mutationsi1);
            mutations_sets_names.add(i + " Insert");

        }


        Testing.print_results_of_tests(original_gene, gene_sequence, mutations_sets, mutations_sets_names);
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

    static void time_algorithms(String original_gene, ArrayList<String> gene_sequence) {

        long startTime = System.nanoTime();
        for (int i = 0; i < gene_sequence.size(); ++i) {
            Algorithms.jaro(original_gene, gene_sequence.get(i));
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
        //System.out.println("Jaro time: " + duration + "ms");
        System.out.print(duration + " ");

        startTime = System.nanoTime();
        for (int i = 0; i < gene_sequence.size(); ++i) {
            Algorithms.levenshtein_distance(original_gene, gene_sequence.get(i));
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
        //System.out.println("Levenshtein time: " + duration + "ms");
        System.out.print(duration + " ");

        startTime = System.nanoTime();
        for (int i = 0; i < gene_sequence.size(); ++i) {
            Algorithms.jaccard_similarity(original_gene, gene_sequence.get(i));
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
        //System.out.println("Jaccard time: " + duration + "ms");
        System.out.print(duration + " ");

        startTime = System.nanoTime();
        for (int i = 0; i < gene_sequence.size(); ++i) {
            Algorithms.damerauLevenshteinDistance(original_gene, gene_sequence.get(i));
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
        //System.out.println("damerauLevenshtein time: " + duration + "ms");
        System.out.print(duration + " ");

        startTime = System.nanoTime();
        OptimalStringAlignment opti = new OptimalStringAlignment();
        for (int i = 0; i < gene_sequence.size(); ++i) {
            OptimalStringAlignment.editDistance(original_gene, gene_sequence.get(i), 3);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
       // System.out.println("Optimal Algn time: " + duration + "ms");
        System.out.print(duration + " ");
    }


    static ArrayList<String> make_random_gene_strings(int number_of_characters, int number_of_strings, Random rand) {

        ArrayList<String> strings = new ArrayList<String>();


        for (int i = 0; i < number_of_strings; ++i) {

            strings.add(make_random_gene_string(number_of_characters, rand));
        }
        return strings;


    }

    public static String make_random_gene_string(int number_of_characters, Random rand) {
        StringBuilder temp = new StringBuilder();
        for (int j = 0; j < number_of_characters; ++j) {
            int num = rand.nextInt(4) + 1;
            if (num == 1)
                temp.append("a");
            else if (num == 2)
                temp.append("c");
            else if (num == 3)
                temp.append("c");
            else
                temp.append("g");
        }
        return temp.toString();
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
