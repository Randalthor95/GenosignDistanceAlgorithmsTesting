import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {

        String original_gene = "acgcttcgca";
        ArrayList<String> gene_sequence = Testing.read_gene_sequence_from_file("src/test/pUC19");

        Testing.print_test_results_for_n_errors(original_gene, gene_sequence, 3);

        Testing.time_algorithms(original_gene, Testing.make_random_gene_strings(10, 1000000));



    }
}
