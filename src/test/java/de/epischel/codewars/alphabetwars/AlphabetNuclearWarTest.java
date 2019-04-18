package de.epischel.codewars.alphabetwars;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AlphabetNuclearWarTest {

    @Test
    public void SampleTestCases() {
        String[][] tests = {
        		{"[a][b][c]", "abc"},
            {"[a]#[b]#[c]", "ac"},
            {"[a]#b#[c][d]", "d"},            
            {"##a[a]b[c]#", "c"},
            {"abde[fgh]ijk", "abdefghijk"},
            {"ab#de[fgh]ijk", "fgh"},
            {"ab#de[fgh]ij#k", ""},
            {"##abde[fgh]ijk", ""},
            {"##abcde[fgh]", ""},
            {"abcde[fgh]", "abcdefgh"},
            {"##abde[fgh]ijk[mn]op", "mn"},
            {"#abde[fgh]i#jk[mn]op", "mn"},
            {"[ab]adfd[dd]##[abe]dedf[ijk]d#d[h]#", "abijk"}
        };
        for (String[] test : tests)
            assertEquals("For " + test[0], test[1], AlphabetNuclearWar.alphabetWar(test[0]));
    }	
	
}
