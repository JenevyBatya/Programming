package org.lab;

public class CommandExecute {
    private String response;
    private boolean success;
    public CommandExecute(String response, boolean success) {
        this.response=response;
        this.success=success;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
