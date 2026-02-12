package com.akash.webApp.Model;


public class ApiResponse {
    private String message;
    private String data;
    
    public ApiResponse(String message, String data) {
        this.message = message;
        this.data = data;
    }
    
    // Getters
    public String getMessage() { return message; }
    public String getData() { return data; }
}
