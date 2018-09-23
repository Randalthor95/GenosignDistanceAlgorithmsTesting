import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {
        ArrayList<String> mutations = Testing.generate_substitution_mutation_permutations("act", 2);

        for(int i = 0 ; i < mutations.size(); ++i) {
            System.out.println(mutations.get(i));
        }

    }
}
