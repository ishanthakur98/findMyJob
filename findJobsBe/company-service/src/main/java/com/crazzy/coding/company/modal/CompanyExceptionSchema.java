package com.crazzy.coding.company.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CompanyExceptionSchema {

    private String errorCode;

    private String errorDescription;

    private String errorSource = "Company Service";

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


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }


}
