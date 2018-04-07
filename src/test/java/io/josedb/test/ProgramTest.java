package io.josedb.test;

import org.junit.Test;

import io.josedb.utils.JoseDbInfo;
import junit.framework.TestCase;

public class ProgramTest extends TestCase{
    @Test 
    public void testSomeLibraryMethod() {
        assertEquals("Should match version", "0.0.1", JoseDbInfo.getVersion());
    }
}
