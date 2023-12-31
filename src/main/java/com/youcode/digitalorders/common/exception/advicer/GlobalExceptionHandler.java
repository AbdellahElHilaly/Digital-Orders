package com.youcode.digitalorders.common.exception.advicer;

import com.youcode.digitalorders.common.exception.response.ErrorResponse;
import com.youcode.digitalorders.common.exception.response.ErrorSimpleResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GlobalExceptionHandler {

    private final ErrorResponse errorResponse;
    private final ErrorSimpleResponse errorSimpleResponse;

    private  final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception, HttpServletRequest request) {

        Map<String, java.util.List<String>> errorDetails = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        org.springframework.validation.FieldError::getField,
                        Collectors.mapping(org.springframework.context.support.DefaultMessageSourceResolvable::getDefaultMessage, Collectors.toList())
                ));

        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setMessage("Validation Failed");
        errorResponse.setDetails(errorDetails);
        errorResponse.setPath(request.getRequestURI());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorSimpleResponse> handleNoSuchElementException(
            NoSuchElementException exception, HttpServletRequest request) {

        errorSimpleResponse.setTimestamp(LocalDateTime.now());
        errorSimpleResponse.setMessage("Resource Not Found");
        errorSimpleResponse.setDetails(Collections.singletonList(exception.getMessage()));
        errorSimpleResponse.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorSimpleResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorSimpleResponse> handleDataIntegrityViolationException(
            DataIntegrityViolationException exception, HttpServletRequest request) {

        errorSimpleResponse.setTimestamp(LocalDateTime.now());
        errorSimpleResponse.setMessage("Data Integrity Violation");
        errorSimpleResponse.setDetails(Arrays.asList(exception.getMessage().split(";")));
        errorSimpleResponse.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorSimpleResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorSimpleResponse> handleException(
            Exception exception, HttpServletRequest request) {

        logger.error("Exception occurred", exception);

        errorSimpleResponse.setTimestamp(LocalDateTime.now());
        errorSimpleResponse.setMessage("Internal Server Error");
        errorSimpleResponse.setDetails(Collections.singletonList(exception.getMessage()));
        errorSimpleResponse.setPath(request.getRequestURI());


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSimpleResponse);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponses> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatusCode status = ex.getStatusCode();
        String message = ex.getReason();

        ErrorResponses errorResponse = new ErrorResponses(status.value(), message);
        return ResponseEntity.status(status).body(errorResponse);
    }

    private record ErrorResponses(int statusCode, String message) {
    }
}
