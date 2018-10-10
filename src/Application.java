import java.util.ArrayList;
import java.util.List;
import java.awt.Window;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

//import org.apache.commons.lang3.StringUtils;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
//import org.apache.commons.lang3.StringUtils;


public class Application {

    public static void main(String[] args) throws IOException {
    	//Parse the fasta file to extract gene sequence
    	ArrayList<String>  gene_sequence=FastaParsing.gene_sequence();
    	//For debugging
    	System.out.println("Gene Sq");
    	System.out.println(gene_sequence);
        String original_gene = "acgcttcgca";
 
      Testing.print_test_results_for_n_errors(original_gene, gene_sequence, 4);


//        ArrayList<Integer> matches = Testing.test_accuracy_of_methods(original_gene, gene_sequence, mutations);
//
//
//        System.out.println("Jaro Matches: " + Integer.toString(matches.get(0)) + "/" + Integer.toString(mutations.size()));
//        System.out.println("Levenshtein Matches: " + Integer.toString(matches.get(1)) + "/" + Integer.toString(mutations.size()));
//        System.out.println("Jaccard Matches: " + Integer.toString(matches.get(2)) + "/" + Integer.toString(mutations.size()));


    }
   
    

}
