package com.example.app2.SampleProject;

public class SMSRequest {

    public String UserApiKey;
    public String SecretKey;

    public SMSRequest(String UserApiKey, String SecretKey) {

        this.UserApiKey = UserApiKey;
        this.SecretKey = SecretKey;
    }

    @Override
    public String toString() {
        return "UserApiKey=" + UserApiKey + ", SecretKey=" + SecretKey;
    }
}
