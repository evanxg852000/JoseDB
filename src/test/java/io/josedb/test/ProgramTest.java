package io.josedb.test;

import org.junit.Test;

import io.josedb.Program;

import static org.junit.Assert.*;

public class ProgramTest {
    @Test public void testSomeLibraryMethod() {
        assertEquals("Should match version", "0.0.1", Program.VERSION);
    }
}
