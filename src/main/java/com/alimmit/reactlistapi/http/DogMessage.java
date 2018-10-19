package com.alimmit.reactlistapi.http;

public abstract class DogMessage<MESSAGE> {

    private String status;
    private MESSAGE message;

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public MESSAGE getMessage() {
        return message;
    }

    public void setMessage(final MESSAGE message) {
        this.message = message;
    }
}
