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

//This class is to pasre the FATSA files 
public class FastaParsing {
	
	public static ArrayList<String> gene_sequence(String fasta_file){
		ArrayList<String> gene_squence;
		String fileContent = "";
		//read the contents of FASTA file
		try {
			fileContent = new String(Files.readAllBytes(Paths.get(fasta_file)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//THIS Part Extract the geneSequence between "ORIGIN" and "\\"
		String wordToFind = "ORIGIN";
		String tempSeq = null;
		String contentuptoorigin = null;
		Pattern word = Pattern.compile(wordToFind);
		Matcher match = word.matcher(fileContent);

		// Match keyword "ORIGIN"
		while (match.find()) {
			// temporary sequence
			tempSeq = fileContent.substring((match.end()), fileContent.length());
		}
        //Remove white spaces, digits and special characters
		tempSeq = tempSeq.replaceAll("\\s", "");
		String tempSeq1=tempSeq.replaceAll("\\d","");
		tempSeq1=tempSeq1.replaceAll("\\W","");
	//	System.out.println(tempSeq1);
		//Build substrings of 10 bytes from extracted gene Sequence
		gene_squence=splitEqually(tempSeq1,10);
	//	System.out.println(gene_squence);
		return gene_squence;
	} //End of Function
    private static ArrayList<String> splitEqually(String text, int size) {
        // Give the list the right capacity to start with. 
    	ArrayList<String> ret = new ArrayList<String>((text.length() + size - 1) / size);
       // System.out.println(text);
        for (int start = 0; start < text.length(); start += size) {
            ret.add(text.substring(start, Math.min(text.length(), start + size)));
        }
        return ret;
    } //End of split function

}
