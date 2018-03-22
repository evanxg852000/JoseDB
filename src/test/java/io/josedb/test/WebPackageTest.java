package io.josedb.test;

import org.junit.Test;

import io.josedb.framework.web.HttpRequestHandler;
import io.josedb.framework.web.Route;

import static org.junit.Assert.*;

import java.util.Arrays;


public class WebPackageTest {
	
	private class DummyHandler extends HttpRequestHandler {
		
	}
	
	@Test
	public void RequestRoute(){
		Route route = new Route("/test", null, Arrays.asList("GET", "Post"));
		assertEquals("Route.isOk should be false", false, route.isOk());
		
		route = new Route("/test/{name}", new DummyHandler(), Arrays.asList("get", "Post"));
		assertEquals("isOk should be true", true, route.isOk());
		assertEquals("Spec should match", route.getSpec(), "/test/{name}");
		assertEquals("Methods should match", route.getSupportedMethods(), Arrays.asList("GET", "POST"));
		assertEquals("Params should be [name]", route.getParams(), Arrays.asList("name"));
		assertEquals("Regex should be correctly generated", route.getPattern().toString(), "^\\/test(\\/(?<name>\\w+))$");
		
		route = new Route("/_config/{dbname}/{?name}/", new DummyHandler(), Arrays.asList("get", "pOSt", "put"));
		assertEquals("Spec should match", route.getSpec(), "/_config/{dbname}/{?name}/");
		assertEquals("Methods should match", route.getSupportedMethods(), Arrays.asList("GET", "POST", "PUT"));
		assertEquals("Params should be [dbname, name]", route.getParams(), Arrays.asList("dbname", "name"));
		assertEquals("Regex should be correctly generated", route.getPattern().toString(), "^\\/_config(\\/(?<dbname>\\w+))(\\/(?<name>\\w+)?)$");
	}

}
