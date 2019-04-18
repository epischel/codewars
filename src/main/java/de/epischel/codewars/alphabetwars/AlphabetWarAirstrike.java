package de.epischel.codewars.alphabetwars;

import java.util.HashMap;
import java.util.Map;

/**
 * see https://www.codewars.com/kata/alphabet-war-airstrike-letters-massacre
 */
public class AlphabetWarAirstrike {
	
	private static final Map<Character, Integer> MAP = new HashMap<>();
	
	static {
		int i = 0;
		for(char c: "wpbs_zdqm".toCharArray()) { 
			MAP.put(c, (i++)-4 );
		}
	}
	
	private static int getFromMap(final int i) {
		Integer value = MAP.get((char)i);
		return value!=null?value:0;
	}
	
	public static String alphabetWar(String fight){
		String bombed = resolveBombs(fight);
		final int sum = bombed.chars().map((i)-> getFromMap(i)).sum();
		if(sum<0) {
			return "Left side wins!";
		} else if (sum>0) {
			return "Right side wins!";
		}
        return "Let's fight again!";
    }

	private static String resolveBombs(String fight) {
		final char[] chars = fight.toCharArray();
		for(int i = 0; i < chars.length; i++) {
			if (chars[i]!='*') continue;
			// kill left letter
			if (i>0) chars[i-1] = '_';
			// kill right letter unless it's a bomb
			if (i<chars.length-1 && chars[i+1]!='*') chars[i+1] = '_';
		}
		return String.copyValueOf(chars);
	}

}
