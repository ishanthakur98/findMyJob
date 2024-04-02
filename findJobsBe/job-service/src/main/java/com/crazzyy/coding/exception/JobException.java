package com.crazzyy.coding.exception;

import java.time.LocalDateTime;

public class JobException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String errorCode;

    private String errorDescription;

    private String errorSource = "Job Service";

    private String requestURI ;

    private String method;

    private LocalDateTime localDateTime;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorSource() {
        return errorSource;
    }

    public void setErrorSource(String errorSource) {
        this.errorSource = errorSource;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public JobException(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public JobException(String errorCode, String errorDescription, String errorSource, String requestURI, String method, LocalDateTime localDateTime) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.errorSource = errorSource;
        this.requestURI = requestURI;
        this.method = method;
        this.localDateTime = localDateTime;
    }

    public JobException(String message, String errorCode, String errorDescription, String errorSource, String requestURI, String method, LocalDateTime localDateTime) {
        super(message);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.errorSource = errorSource;
        this.requestURI = requestURI;
        this.method = method;
        this.localDateTime = localDateTime;
    }

    public JobException(String message, Throwable cause, String errorCode, String errorDescription, String errorSource, String requestURI, String method, LocalDateTime localDateTime) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.errorSource = errorSource;
        this.requestURI = requestURI;
        this.method = method;
        this.localDateTime = localDateTime;
    }

    public JobException(Throwable cause, String errorCode, String errorDescription, String errorSource, String requestURI, String method, LocalDateTime localDateTime) {
        super(cause);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.errorSource = errorSource;
        this.requestURI = requestURI;
        this.method = method;
        this.localDateTime = localDateTime;
    }

    public JobException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode, String errorDescription, String errorSource, String requestURI, String method, LocalDateTime localDateTime) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.errorSource = errorSource;
        this.requestURI = requestURI;
        this.method = method;
        this.localDateTime = localDateTime;
    }

    public JobException() {
        super();
    }
}
