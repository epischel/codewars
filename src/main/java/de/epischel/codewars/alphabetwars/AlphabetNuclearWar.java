package de.epischel.codewars.alphabetwars;

/**
 * see https://www.codewars.com/kata/alphabet-wars-nuclear-strike/java
  */
public class AlphabetNuclearWar {
    
	public static String alphabetWar(String battlefield) {
        if (battlefield.indexOf('#')<0) return battlefield.replaceAll("\\[|\\]", "");
        if (battlefield.indexOf('[')<0) return ""; // no shelters but bombs
        int pos = 0;
        Shelter currentShelter = null;
        for(char c : battlefield.toCharArray()) {
        	if (c=='[') {
        		currentShelter = new Shelter(pos,currentShelter);
        	} else if (c==']') {
        		currentShelter.endPosition=pos;
        	} else if (currentShelter!=null && currentShelter.isInShelter()) {
        		currentShelter.addCharacter(c);
        	}
        	pos++;
        }
        pos = 0;
        for(char c : battlefield.toCharArray()) {
        	if (c=='#') {
        		currentShelter.detonateBombAt(pos);
        	}
        	pos++;
        }        
        return currentShelter.getSurvingLetters();
    }
}

class Shelter {
	int startPosition = -1;
	int endPosition = -1;
	Shelter shelterLeft;
	Shelter shelterRight;
	int nrOfBombs= 0;
	String letters = "";
	
	Shelter(int startPosition, Shelter shelterLeft) {
		this.startPosition = startPosition;
		this.shelterLeft = shelterLeft;
		if (shelterLeft!=null) shelterLeft.shelterRight=this;
	}

	public String getSurvingLetters() {
		final String predecessors = shelterLeft!=null?shelterLeft.getSurvingLetters():"";
		if (nrOfBombs>=2) {
			return predecessors;
		}
		return predecessors+letters;
	}

	public void detonateBombAt(int position) {
		if (startPosition>position && shelterLeft==null) {
			nrOfBombs++;
		} else if (endPosition<position && shelterRight==null) {
			nrOfBombs++;
		} else if (startPosition<position && shelterRight!=null && shelterRight.startPosition>position) {
			nrOfBombs++;
		} else if (endPosition>position && shelterLeft!=null && shelterLeft.endPosition<position) {
			nrOfBombs++;
		}
		if (shelterLeft!=null) {
			shelterLeft.detonateBombAt(position);
		}
	}

	public void addCharacter(char c) {
		letters += c;
	}

	public boolean isInShelter() {
		return endPosition<0;
	}
}