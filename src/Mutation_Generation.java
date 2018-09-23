import java.util.ArrayList;

public class Mutation_Generation {

    //Single Parts
    public static ArrayList<String> generate_insertion_mutation_permutations
            (String original_gene, int number_of_insertions) {
        ArrayList<String> insertion_error_permutations = new ArrayList<String>();

        original_gene = original_gene.toLowerCase();
        insertion_error_permutations.add(original_gene);
        int start = 0;
        for (int i = 1; i <= number_of_insertions; ++i) {

            int end = insertion_error_permutations.size();
            for (int j = start; j < end; ++j) {

                for (int k = 0; k <= insertion_error_permutations.get(j).length(); ++k) {

                    for (int n = 0; n < 4; ++n) {
                        StringBuilder temp = new StringBuilder(insertion_error_permutations.get(j));
                        if (n == 0)
                            temp.insert(k, 'a');
                        else if (n == 1)
                            temp.insert(k, 'c');
                        else if (n == 2)
                            temp.insert(k, 't');
                        else
                            temp.insert(k, 'g');

                        if (!insertion_error_permutations.contains(temp.toString()))
                            insertion_error_permutations.add(temp.toString());
                    }
                }
            }
            start = end;


        }

        insertion_error_permutations.remove(original_gene);
        return insertion_error_permutations;
    }

    public static ArrayList<String> generate_deletion_mutation_permutations
            (String original_gene, int number_of_deletions) {
        ArrayList<String> deletion_error_permutations = new ArrayList<String>();
        original_gene = original_gene.toLowerCase();


        deletion_error_permutations.add(original_gene);
        int start = 0;
        for (int i = 1; i <= number_of_deletions; ++i) {

            int end = deletion_error_permutations.size();
            for (int j = start; j < end; ++j) {

                for (int k = 0; k < deletion_error_permutations.get(j).length(); ++k) {
                    StringBuilder temp = new StringBuilder(deletion_error_permutations.get(j));
                    temp.deleteCharAt(k);

                    if (!deletion_error_permutations.contains(temp.toString()))
                        deletion_error_permutations.add(temp.toString());

                }
            }
            start = end;


        }

        deletion_error_permutations.remove(original_gene);


        return deletion_error_permutations;
    }

    public static ArrayList<String> generate_substitution_mutation_permutations
            (String original_gene, int number_of_substitutions) {
        ArrayList<String> substitution_error_permutations = new ArrayList<String>();

        original_gene = original_gene.toLowerCase();
        substitution_error_permutations.add(original_gene);
        int start = 0;
        for (int i = 1; i <= number_of_substitutions; ++i) {

            int end = substitution_error_permutations.size();
            for (int j = start; j < end; ++j) {

                for (int k = 0; k < substitution_error_permutations.get(j).length(); ++k) {

                    for (int n = 0; n < 4; ++n) {
                        StringBuilder temp = new StringBuilder(substitution_error_permutations.get(j));
                        if (n == 0) {
                            if (!substitution_error_permutations.get(j).equals("a"))
                                temp.replace(k, k + 1, "a");
                        } else if (n == 1) {
                            if (!substitution_error_permutations.get(j).equals("c"))
                                temp.replace(k, k + 1, "c");
                        } else if (n == 2) {
                            if (!substitution_error_permutations.get(j).equals("t"))
                                temp.replace(k, k + 1, "t");
                        } else {
                            if (!substitution_error_permutations.get(j).equals("g"))
                                temp.replace(k, k + 1, "g");
                        }
                        if (!substitution_error_permutations.contains(temp.toString()))
                            substitution_error_permutations.add(temp.toString());
                    }
                }
            }
            start = end;


        }

        substitution_error_permutations.remove(original_gene);
        return substitution_error_permutations;
    }


    //Two Parts
    public static ArrayList<String> generate_insertion_then_deletion_mutation_permutations
            (String original_gene, int number_of_insertions, int number_of_deletions) {

        ArrayList<String> insertion_permutations = generate_insertion_mutation_permutations(original_gene, number_of_insertions);

        ArrayList<String> permutations = new ArrayList<String>();
        for (int i = 0; i < insertion_permutations.size(); ++i) {
            ArrayList<String> temp_permutations = generate_deletion_mutation_permutations(insertion_permutations.get(i), number_of_deletions);

            for (int j = 0; j < temp_permutations.size(); ++j) {
                if(!permutations.contains(temp_permutations.get(j)))
                    permutations.add(temp_permutations.get(j));
            }
        }

        return permutations;

    }

    public static ArrayList<String> generate_insertion_then_substitution_mutation_permutations
            (String original_gene, int number_of_insertions, int number_of_substitutions) {

        ArrayList<String> insertion_permutations = generate_insertion_mutation_permutations(original_gene, number_of_insertions);

        ArrayList<String> permutations = new ArrayList<String>();
        for (int i = 0; i < insertion_permutations.size(); ++i) {
            ArrayList<String> temp_permutations = generate_substitution_mutation_permutations(insertion_permutations.get(i), number_of_substitutions);

            for (int j = 0; j < temp_permutations.size(); ++j) {
                if(!permutations.contains(temp_permutations.get(j)))
                    permutations.add(temp_permutations.get(j));
            }
        }

        return permutations;

    }

    public static ArrayList<String> generate_deletion_then_substitution_mutation_permutations
            (String original_gene, int number_of_deletions, int number_of_substitutions) {

        ArrayList<String> deletion_permutations = generate_deletion_mutation_permutations(original_gene, number_of_deletions);

        ArrayList<String> permutations = new ArrayList<String>();
        for (int i = 0; i < deletion_permutations.size(); ++i) {
            ArrayList<String> temp_permutations = generate_substitution_mutation_permutations(deletion_permutations.get(i), number_of_substitutions);

            for (int j = 0; j < temp_permutations.size(); ++j) {
                if(!permutations.contains(temp_permutations.get(j)))
                    permutations.add(temp_permutations.get(j));
            }
        }

        return permutations;

    }

}
