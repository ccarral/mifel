package com.mifel.service.crypto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class CryptoResponse {
    public boolean success = false;
    public String encryptedBase64;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String failureReason;

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getEncryptedBase64() {
        return encryptedBase64;
    }

    public void setEncryptedBase64(String encryptedBase64) {
        this.encryptedBase64 = encryptedBase64;
    }
}
