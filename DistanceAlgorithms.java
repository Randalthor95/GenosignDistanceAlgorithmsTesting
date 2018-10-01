

public class DistanceAlgorithms {
	 private static int Minimum (int a, int b, int c) {
		  int mi;

		    mi = a;
		    if (b < mi) {
		      mi = b;
		    }
		    if (c < mi) {
		      mi = c;
		    }
		    return mi;

		  }
    public static double jaro(String s1, String s2) {

        if (s1.length() == 0 && s2.length() == 0)
            return 1;
        int max_matching_distance = Math.max(s1.length(), s2.length()) / 2;
        max_matching_distance = max_matching_distance - 1;
        int matches = 0;
        int transpositions = 0;


        boolean[] s1_matches = new boolean[s1.length()];
        boolean[] s2_matches = new boolean[s2.length()];

        for (int i = 0; i < s1.length(); ++i) {
            int start = Integer.max(0, i - max_matching_distance);
            int end = Integer.min(i + max_matching_distance + 1, s2.length());

            for (int j = start; j < end; j++) {
                if (s2_matches[j]) continue;
                if (s1.charAt(i) != s2.charAt(j)) continue;
                s1_matches[i] = true;
                s2_matches[j] = true;
                matches++;
                break;
            }
        }
        if (matches == 0) return 0;

        int k = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (!s1_matches[i]) continue;
            while (!s2_matches[k]) k++;
            if (s1.charAt(i) != s2.charAt(k)) transpositions++;
            k++;
        }

        return (((double) matches / s1.length()) +
                ((double) matches / s2.length()) +
                (((double) matches - transpositions / 2.0) / matches)) / 3.0;
    }

    //returns -1 if length_of_prefix_l is negative or greater than four
    //returns -1 if weight_of_scaling_factor_p is zero or greater than 0.25
    public static double jaro_winkler(String s1, String s2, int length_of_prefix_l, double weight_of_scaling_factor_p) {
        if (length_of_prefix_l > 4 || length_of_prefix_l < 1)
            return -1;
        if (weight_of_scaling_factor_p > 0.25 || weight_of_scaling_factor_p < 0)
            return -1;

        double jaro = jaro(s1, s2);
        return jaro + (length_of_prefix_l * weight_of_scaling_factor_p * (1 - jaro));


    }
    public static int LD (String s, String t) {
    	  int d[][]; // matrix
    	  int Str1Len; // length of s
    	  int Str2Len; // length of t
    	  int i; // iterates through s
    	  int j; // iterates through t
    	  char s_i; // ith character of s
    	  char t_j; // jth character of t
    	  int cost; // cost

    	    // Step 1

    	  Str1Len = s.length ();
    	  Str2Len = t.length ();
    	    if (Str1Len == 0) {
    	      return Str2Len;
    	    }
    	    if (Str2Len == 0) {
    	      return Str1Len;
    	    }
    	    d = new int[Str1Len+1][Str2Len+1];

    	    // Step 2

    	    for (i = 0; i <= Str1Len; i++) {
    	      d[i][0] = i;
    	    }

    	    for (j = 0; j <= Str2Len; j++) {
    	      d[0][j] = j;
    	    }

    	    // Step 3

    	    for (i = 1; i <= Str1Len; i++) {

    	      s_i = s.charAt (i - 1);

    	      // Step 4

    	      for (j = 1; j <= Str2Len; j++) {

    	        t_j = t.charAt (j - 1);

    	        // Step 5

    	        if (s_i == t_j) {
    	          cost = 0;
    	        }
    	        else {
    	          cost = 1;
    	        }

    	        // Step 6

    	        d[i][j] = Minimum (d[i-1][j]+1, d[i][j-1]+1, d[i-1][j-1] + cost);

    	      }

    	    }

    	    // Step 7

    	    return d[Str1Len][Str2Len];

    	  }
}
