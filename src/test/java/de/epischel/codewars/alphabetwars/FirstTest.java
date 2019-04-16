package de.epischel.codewars.alphabetwars;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FirstTest {
    @Test
    public void BasicTest() {
       assertEquals("Right side wins!", First.alphabetWar("z"));
       assertEquals("Let's fight again!", First.alphabetWar("zdqmwpbs"));
       assertEquals("Right side wins!", First.alphabetWar("zzzzs"));
       assertEquals("Left side wins!", First.alphabetWar("wwwwwwz"));
    }
}
