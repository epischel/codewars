package de.epischel.fefe;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * see http://ptrace.fefe.de/wp/
 */
public class WPP {
	
	public static void main(String[] args) throws IOException {
		
		long time = System.currentTimeMillis();

		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(InputData.INPUT_FILE_NAME)));
		
		Map<String,Integer> countMap = readWords(in);

        Comparator<Map.Entry<String, Integer>> comparingByFrequency = Map.Entry.comparingByValue();
        countMap.entrySet().stream()
                // Reverse comparation to get the biggest counts first
                .sorted(comparingByFrequency.reversed())
                .map(entry -> entry.getValue()+ " " + entry.getKey());
//                .forEach(System.out::println);
        
        System.out.println(System.currentTimeMillis()-time);
    }

	private static Map<String,Integer> readWords(BufferedReader in) throws IOException {
		HashMap<String,Integer> countMap = new HashMap<String,Integer>(0xFFFF);
		String line;
		while(((line=in.readLine())!=null)) {
        	final String[] wordsInLine = line.split(" ");
        	for(final String word : wordsInLine) {
        		countMap.merge(word, 1, (number, one) -> number + one);
        	}
		}
		return countMap;
	}
	
	
	private static Map<String,Integer> readWordsP(BufferedReader in) throws IOException {
		String line;
		Map<String,Integer> countMap = new ConcurrentHashMap<String,Integer>(0xFFFF);
		while(((line=in.readLine())!=null)) {
        	final String[] wordsInLine = line.split("\\s");
        	Stream.of(wordsInLine).parallel().forEach(word ->
        		countMap.merge(word, 1, (number, one) -> number + one));
		}
		return countMap;
	}

}
