package com.biggergames.tbslapi.service;

import org.springframework.http.HttpStatus;

public class ServiceResult<E> {

    private E value;
    private String code;
    private String message;
    private HttpStatus status;

    public ServiceResult() {
    }

    public ServiceResult(E value, String message) {
        this.value = value;
        this.message = message;
    }

    public ServiceResult(HttpStatus status, E value) {
        this.status = status;
        this.value = value;
    }

    public ServiceResult(E value) {
        this.status = HttpStatus.OK;
        this.value = value;
    }

    public ServiceResult(HttpStatus status) {
        this.status = status;
    }

    public ServiceResult(HttpStatus status, String message) {
        this.status = status;
        this.message = message;

    }

    public Boolean isSuccess() {
        return status != null && (status.equals(HttpStatus.OK) || status.equals(HttpStatus.CREATED));
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
