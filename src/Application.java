public class Application {

    public static void main(String[] args) {
        System.out.println(Algorithms.jaro(   "MARTHA",      "MARHTA"));
        System.out.println(Algorithms.jaro(    "DIXON",    "DICKSONX"));
        System.out.println(Algorithms.jaro("JELLYFISH",  "SMELLYFISH"));
        System.out.println(Algorithms.jaro("JELLYFISH",  "JELLYFISH"));
        System.out.println(Algorithms.jaro("JELLYFISH",  "aaaaa"));


        System.out.println(Algorithms.jaro_winkler(   "MARTHA",      "MARHTA", 3, .25));
        System.out.println(Algorithms.jaro_winkler(   "MARTHA",      "MARHTA", 3, .25));
        System.out.println(Algorithms.jaro_winkler(   "MARTHA",      "HTAMAR", 3, .25));
        System.out.println(Algorithms.jaro_winkler(   "MARTHA",      "MARHTA", 3, .25));

    }
}
