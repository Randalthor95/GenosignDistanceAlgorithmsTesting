import java.util.ArrayList;
import java.util.Random;

public class Application {

    public static void main(String[] args) {

        String original_gene = "acgcttcgca";
        ArrayList<String> gene_sequence = Testing.read_gene_sequence_from_file("src/test/pUC19");

        Testing.print_test_results_for_n_errors(original_gene, gene_sequence, 3);

//        Random rand = new Random();
//        for(int i = 26; i < 101; ++i) {
//            Testing.time_algorithms(Testing.make_random_gene_string(i, rand),
//                    Testing.make_random_gene_strings(i, 1000000, rand));
//            System.out.println();
//        }


    }
}
