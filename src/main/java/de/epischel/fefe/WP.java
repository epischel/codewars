package de.epischel.fefe;

import java.io.*;
import java.util.*;

/**
 * see http://ptrace.fefe.de/wp/
 */
public class WP {
	
	

	public static void main(String[] args) throws IOException {
		
		long time = System.currentTimeMillis();

        Reader in = new BufferedReader(new InputStreamReader(new FileInputStream(InputData.INPUT_FILE_NAME)));
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.resetSyntax();
        tokenizer.wordChars(33,126);
        tokenizer.whitespaceChars(1,32);

        HashMap<String,Integer> countMap = new HashMap<String,Integer>(0xFFFF);
        while( tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
          if (tokenizer.ttype == StreamTokenizer.TT_WORD) {
                countMap.merge(tokenizer.sval, 1, (number, one) -> number + one);
          }
        }       

        Comparator<Map.Entry<String, Integer>> comparingByFrequency = Map.Entry.comparingByValue();
        countMap.entrySet().stream()
                // Reverse comparation to get the biggest counts first
                .sorted(comparingByFrequency.reversed())
                .map(entry -> entry.getValue()+ " " + entry.getKey());
                //.forEach(System.out::println);
        
        System.out.println(System.currentTimeMillis()-time);
    }


}
