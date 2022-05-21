package com.manager.product.exceptionhandling;

import com.manager.product.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class ProductExceptionHandling extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public ProductExceptionHandling(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("server.error", "Server Error", details);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundError(Exception ex) {
        List<String> details = new ArrayList<>();

        details.add(this.getMessage(ex.getMessage()));

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                this.getMessage(ex.getMessage()),
                details);
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> details = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }

        ErrorResponse error = new ErrorResponse(
                "Validation Failed",
                "validation.failed",
                details);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.CANADA);
    }

}
