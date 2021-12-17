package com.capitole.infraestructure.rest.exceptions;

import java.util.List;

public class ErrorMessage {

    private int statusCode;
    private List<String> message;
    private String description;


    public ErrorMessage(int statusCode, List<String> message, String description) {
        this.statusCode = statusCode;
        this.message = message;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public List<String> getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
