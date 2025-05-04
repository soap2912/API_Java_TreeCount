package com.example.Api_TreeCount_Fiap.DTOs.UserDTO;

public class LoginResponseDTO {

    private String token;
    private String message;
    private boolean success;

    public LoginResponseDTO(boolean success, String message, String token) {
        this.success = success;
        this.token = token;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
