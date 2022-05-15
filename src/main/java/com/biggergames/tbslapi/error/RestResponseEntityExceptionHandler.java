package com.biggergames.tbslapi.error;


import com.biggergames.tbslapi.client.response.DefaultResponse;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new DefaultResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase()), addHeader(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new DefaultResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "Eksik Parametre"), addHeader(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ServiceResultException.class})
    protected ResponseEntity<DefaultResponse> processRuntimeException(ServiceResultException ex, WebRequest request) {
        return new ResponseEntity<>(new DefaultResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "Teknik Bir Aksaklik"), addHeader(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NotReadablePropertyException.class})
    protected ResponseEntity<DefaultResponse> notReadablePropertyException(NotReadablePropertyException ex, WebRequest request) {
        return new ResponseEntity<>(new DefaultResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "Tip Uyusmazligi."), addHeader(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
        return new ResponseEntity<>(new DefaultResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), errors), addHeader(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new DefaultResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "Tip Uyusmazligi"), addHeader(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new DefaultResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "Tip Uyusmazligi"), addHeader(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<DefaultResponse> processRuntimeException(ConstraintViolationException ex, WebRequest request) {
        String errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
        return new ResponseEntity<>(new DefaultResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), errors), addHeader(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseEntity<Object> responseEntity = super.handleExceptionInternal(ex, body, headers, status, request);
        return new ResponseEntity<>(new DefaultResponse(responseEntity.getStatusCodeValue(), responseEntity.getStatusCode().getReasonPhrase(), ""), addHeader(), HttpStatus.BAD_REQUEST);
    }

    private HttpHeaders addHeader() {
        return new HttpHeaders();
    }
}

