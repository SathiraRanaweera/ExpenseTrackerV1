package com.expense.tracker.exception;

import com.expense.tracker.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.sql.SQLException;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn("Requested method not supported.\n{}", Objects.requireNonNull(request.getUserPrincipal()).getName());
        ex.printStackTrace();
        return ResponseEntity.ok().body(new ApiResponse(false, status.value(), "Requested method not supported"));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn("Requested media type not supported.\n{}", Objects.requireNonNull(request.getUserPrincipal()).getName());
        ex.printStackTrace();
        return ResponseEntity.ok().body(new ApiResponse(false, status.value(), "Requested media type not supported"));
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn("Required path variables not found.\n{}", Objects.requireNonNull(request.getUserPrincipal()).getName());
        ex.printStackTrace();
        return ResponseEntity.ok().body(new ApiResponse(false, status.value(), "Required path variables not found"));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn("Missing Servlet Request Parameter.\n{}", Objects.requireNonNull(request.getUserPrincipal()).getName());
        ex.printStackTrace();
        return ResponseEntity.ok().body(new ApiResponse(false, status.value(), "Missing Servlet Request Parameter"));
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn("Requested type mismatch.\n{}", Objects.requireNonNull(request.getUserPrincipal()).getName());
        ex.printStackTrace();
        return ResponseEntity.ok().body(new ApiResponse(false, status.value(), "Requested type mismatch"));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn("Message Not Readable.\n{}", Objects.requireNonNull(request.getUserPrincipal()).getName());
        ex.printStackTrace();
        return ResponseEntity.ok().body(new ApiResponse(false, status.value(), "Message Not Readable"));
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Object> handleUserNotFoundException(AppException ex) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.ok().body(new ApiResponse(false, HttpStatus.BAD_REQUEST.value(), "User Not Found"));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> BadRequestException(BadRequestException ex) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.ok().body(new ApiResponse(false, HttpStatus.BAD_REQUEST.value(), "Bad Request"));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> ResourceNotFoundException(ResourceNotFoundException ex) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.ok().body(new ApiResponse(false, HttpStatus.BAD_REQUEST.value(), "Resource Not Found"));
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> sqlException(SQLException ex) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.ok().body(new ApiResponse(false, HttpStatus.BAD_REQUEST.value(), "Data base error. Please try again later."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherException(Exception ex) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.ok().body(new ApiResponse(false, HttpStatus.BAD_REQUEST.value(), "Internal server error. Please try again later."));
    }
}