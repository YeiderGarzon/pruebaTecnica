package com.yeider.pruebaTecnica.exceptions;

public class NotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4730615787661511083L;

	public NotFoundException(String message) {
        super(message);
    }
}
