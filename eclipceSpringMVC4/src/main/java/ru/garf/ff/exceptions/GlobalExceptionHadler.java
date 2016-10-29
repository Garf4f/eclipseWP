package ru.garf.ff.exceptions;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHadler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHadler.class);

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOEx in Global")
	@ExceptionHandler(IOException.class)
	public void handleIOException() {
		logger.error("IOException in globalHendler!");
	}

}
