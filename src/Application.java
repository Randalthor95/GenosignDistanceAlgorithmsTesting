import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {

        String original_gene = "acgcttcgca";
        ArrayList<String> gene_sequence = Testing.read_gene_sequence_from_file("src/test/pUC19");
        ArrayList<String> mutations = Mutation_Generation.generate_substitution_mutation_permutations(original_gene, 1);

        ArrayList<Integer> matches = Testing.test_accuracy_of_methods(original_gene, gene_sequence, mutations);


            System.out.println("Jaro Matches: " + Integer.toString(matches.get(0)) + "/" + Integer.toString(mutations.size()));
        System.out.println("Levenshtein Matches: " + Integer.toString(matches.get(1)) + "/" + Integer.toString(mutations.size()));
        System.out.println("Jaccard Matches: " + Integer.toString(matches.get(2)) + "/" + Integer.toString(mutations.size()));


    }
}
