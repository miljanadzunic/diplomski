package rs.diplomski.sys.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rs.diplomski.sys.exception.CustomException;
import rs.diplomski.sys.model.ErrorMessage;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	 @ExceptionHandler(CustomException.class)
	  public final ResponseEntity<ErrorMessage> handleCustomException(CustomException ex) {
	    LOGGER.error(ExceptionUtils.getStackTrace(ex));
	    return new ResponseEntity<ErrorMessage>(new ErrorMessage(ExceptionUtils.getStackTrace(ex)), HttpStatus.CONFLICT);
	  }
}
