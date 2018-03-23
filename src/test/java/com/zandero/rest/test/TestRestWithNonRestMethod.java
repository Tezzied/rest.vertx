package com.zandero.rest.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 */
@Path("/mixed")
public class TestRestWithNonRestMethod {

	@Path("/echo")
	@GET
	public String echo() {

		return hello();
	}

	public String hello() {
		return "hello";
	}
}
