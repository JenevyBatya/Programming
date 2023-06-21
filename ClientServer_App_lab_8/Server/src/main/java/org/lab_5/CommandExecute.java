package org.lab_5;

import org.lab_5.Models.Organization;

import java.util.Hashtable;
import java.time.LocalDate;

public class CommandExecute {
    private String response;
    private boolean success;
    private Integer detail;
    private Hashtable<Integer, Organization> hashtable;
    public CommandExecute(String response, boolean success) {
        this.response=response;
        this.success=success;
    }

    public CommandExecute(String response, boolean success, Integer detail,Hashtable<Integer, Organization> hashtable) {
        this.response = response;
        this.success = success;
        this.detail = detail;
        this.hashtable=hashtable;
    }
//    public CommandExecute(Hashtable<Integer, Organization> hashtable){
//        this.hashtable=hashtable;
//    }

    public Hashtable<Integer, Organization> getHashtable() {
        return hashtable;
    }

    public void setHashtable(Hashtable<Integer, Organization> hashtable) {
        this.hashtable = hashtable;
    }

    public Integer getDetail() {
        return detail;
    }

    public void setDetail(Integer detail) {
        this.detail = detail;
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
