import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {

        String original_gene = "acgcttcgca";
        ArrayList<String> gene_sequence = Testing.read_gene_sequence_from_file("src/test/pUC19");
        ArrayList<String> mutations = Mutation_Generation.generate_substitution_mutation_permutations(original_gene, 1);

        ArrayList<Integer> matches = Testing.test_accuracy_of_methods(original_gene, gene_sequence, mutations);

        for (int i = 0; i < matches.size(); ++i) {
            System.out.println("Matches: " + Integer.toString(matches.get(i)) + "/" + Integer.toString(mutations.size()));
        }

    }
}
