import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {

       ArrayList<String> genes = Testing.read_gene_sequence_from_file("src/test/pUC19");

       for (int i = 0; i < genes.size(); ++i) {
           System.out.println(genes.get(i));
       }
    }
}
