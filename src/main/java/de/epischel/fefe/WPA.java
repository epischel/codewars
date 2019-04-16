package de.epischel.fefe;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * see http://ptrace.fefe.de/wp/
 */
public class WPA {
	
	public static void main(String[] args) throws IOException {
		
		long time = System.currentTimeMillis();

		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(InputData.INPUT_FILE_NAME)));
        HashMap<String,int[]> countMap = new HashMap<String,int[]>(0xFFFF);
        readWords(in, countMap);

        countMap.entrySet().stream()
                // Reverse comparation to get the biggest counts first
                .sorted((a,b)->b.getValue()[0]-a.getValue()[0])
                .map(entry -> entry.getValue()[0]+ " " + entry.getKey())
                .forEach(System.out::println);
        
        System.out.println(System.currentTimeMillis()-time);
    }

	private static void readWords(BufferedReader in, HashMap<String, int[]> countMap) throws IOException {
		String line;
		while(((line=in.readLine())!=null)) {
        	final String[] wordsInLine = line.split("\\s");
        	for(final String word : wordsInLine) {
          	  if (countMap.containsKey(word)) {
        		  countMap.get(word)[0]++;
        	  } else {
        		countMap.put(word, new int[] {1}) ; 
        	  }
        	}
        }
	}	

}
