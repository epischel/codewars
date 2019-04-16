package de.epischel.codewars.alphabetwars;

import java.util.HashMap;
import java.util.Map;

/**
 * see https://www.codewars.com/kata/alphabet-war
 */
public class First {
	
	private static final Map<Character, Integer> MAP = new HashMap<>();
	
	static {
		MAP.put('w', -4);
		MAP.put('p', -3);
		MAP.put('b', -2);
		MAP.put('s', -1);
		MAP.put('m', 4);
		MAP.put('q', 3);
		MAP.put('d', 2);
		MAP.put('z', 1);
	}
	
	private static int getFromMap(final int i) {
		Integer value = MAP.get((char)i);
		return value!=null?value:0;
	}
	
	public static String alphabetWar(String fight){
		final int sum = fight.chars().map((i)-> getFromMap(i)).sum();
		if(sum<0) {
			return "Left side wins!";
		} else if (sum>0) {
			return "Right side wins!";
		}
        return "Let's fight again!";
    }

}
