package ru.sber.arch.restExaple.exceptions.register;

public class RegisterError extends Throwable {
    private int statusCode;
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RegisterError() {
    }

    public RegisterError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
