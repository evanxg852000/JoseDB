package io.josedb.test;

import org.junit.Test;

import io.josedb.Program;
import io.josedb.web.Route;

import static org.junit.Assert.*;


public class WebPackageTest {
	
	@Test
	public void RequestRoute(){
		assertEquals("Route.isOk should be false", false, (new Route("/test", null, new String[]{"GET", "POST"})).isOk() );
	}

}
