package com.falabella.products.api.exceptions;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidBodyException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidBodyException(BindingResult bindingResult) {
        super();
		
    }


}
