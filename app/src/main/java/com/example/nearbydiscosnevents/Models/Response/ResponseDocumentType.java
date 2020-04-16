package com.example.nearbydiscosnevents.Models.Response;

import com.example.nearbydiscosnevents.Models.DocumentType;



import java.util.List;

public class ResponseDocumentType {

    private int httpCode;
    private boolean status;
    private List<DocumentType> message;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public List<DocumentType> getMessage() {
        return message;
    }

    public void setMessage(List<DocumentType> message) {
        this.message = message;
    }


}
