package com.training.springbootbuyitem.entity.response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorMessage {

    private String traceId;
    private String operation;
    private int code;
    private String message;

    public ErrorMessage() {
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
