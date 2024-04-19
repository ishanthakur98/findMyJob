package com.crazzy.coding.company.enums;

public enum Service {



    JOB_SERVICE("JOB-SERVICE") , COMPANY_SERVICE("COMPANY-SERVICE") , REVIEW_SERVICE("REVIEW-SERVICE");

    private String val;

    Service(String val) {
        this.val = val;
    }

    public String value() {
        return val;
    }
}
