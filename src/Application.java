import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {

        String original_gene = "acgcttcgca";
        ArrayList<String> gene_sequence = Testing.read_gene_sequence_from_file("src/test/pUC19");
        ArrayList<ArrayList<String>> mutations_sets = new ArrayList<ArrayList<String>>();
        ArrayList<String> mutations_sets_names = new ArrayList<>();

        ArrayList<String> mutations1 = Mutation_Generation.generate_substitution_mutation_permutations(original_gene, 1);
        mutations_sets.add(mutations1);
        mutations_sets_names.add("Single Subst.");

        ArrayList<String> mutations2 = Mutation_Generation.generate_deletion_mutation_permutations(original_gene, 1);
        mutations_sets.add(mutations2);
        mutations_sets_names.add("Single Delete");

        ArrayList<String> mutations3 = Mutation_Generation.generate_insertion_mutation_permutations(original_gene, 1);
        mutations_sets.add(mutations3);
        mutations_sets_names.add("Single Insert");

        Testing.print_results_of_tests(original_gene, gene_sequence, mutations_sets,  mutations_sets_names);


//        ArrayList<Integer> matches = Testing.test_accuracy_of_methods(original_gene, gene_sequence, mutations);
//
//
//        System.out.println("Jaro Matches: " + Integer.toString(matches.get(0)) + "/" + Integer.toString(mutations.size()));
//        System.out.println("Levenshtein Matches: " + Integer.toString(matches.get(1)) + "/" + Integer.toString(mutations.size()));
//        System.out.println("Jaccard Matches: " + Integer.toString(matches.get(2)) + "/" + Integer.toString(mutations.size()));


    }
}
