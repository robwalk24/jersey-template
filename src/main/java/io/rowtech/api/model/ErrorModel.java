package io.rowtech.api.model;

public class ErrorModel {
    private String error;

    public ErrorModel(String error){
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
