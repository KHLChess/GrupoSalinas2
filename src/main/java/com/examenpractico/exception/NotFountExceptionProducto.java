package com.examenpractico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFountExceptionProducto extends RuntimeException{
	public NotFountExceptionProducto(String mensaje) {
		super(mensaje);
	}
}
