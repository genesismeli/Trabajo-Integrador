package com.dh.apiDentalClinic.enums;

public enum DiagnosisStatus {
    ACTIVE("active"),
    RECOVERED("recovered"),
    RECURRENCE("recurrence"),
    RELAPSE("relapse"),
    INACTIVE("inactive"),
    REMISSION("remission"),
    RESOLVED("resolved");

    private final String value;

    DiagnosisStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
