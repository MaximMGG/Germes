package org.itsimulator.germes.app.rest.service.base;

import jakarta.ws.rs.core.Response;

/**
 * Base class for all REST web-services
 * 
 * @author Maxim
 */

public abstract class BaseResource {
	/**
	 * Shared Response that should be returned of requested operation 
	 * returns no data;
	 */
	protected final Response NOT_FOUND;
	
	/**
	 * Returned if client sends request in invalid or unsupported format
	 */
	protected final Response BAD_REQEST;
	
	public BaseResource() {
		NOT_FOUND = Response.status(Response.Status.NOT_FOUND).build();
		
		BAD_REQEST = Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	/**
	 * Returns operation result as Response object 
	 * 
	 * @param result
	 * @return
	 */
	protected Response ok(Object result) {
		return Response.ok(result).build();
	}

}
