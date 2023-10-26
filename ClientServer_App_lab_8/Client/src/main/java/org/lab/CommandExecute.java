package org.lab;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.lab.Models.Organization;

import java.util.Hashtable;

public class CommandExecute {
    private String response;
    private boolean success;
    private boolean newCommand;
    private Integer detail;
    private Hashtable<Integer, Organization> hashtable;

    @JsonCreator
    @JsonIgnoreProperties(ignoreUnknown = true)
    public CommandExecute(@JsonProperty("response") String response, @JsonProperty("success") boolean success,@JsonProperty("hashtable") Hashtable<Integer, Organization> hashtable) {
        this.response = response;
        this.success = success;
        this.hashtable=hashtable;

    }

    public CommandExecute(String response, boolean success) {
        this.response = response;
        this.success = success;
    }
    //    @JsonCreator
//    @JsonIgnoreProperties(ignoreUnknown = true)
//    public CommandExecute(@JsonProperty("hashtable") Hashtable<Integer, Organization> hashtable) {
//        this.hashtable=hashtable;
//
//    }

    public Hashtable<Integer, Organization> getHashtable() {
        return hashtable;
    }

    public void setHashtable(Hashtable<Integer, Organization> hashtable) {
        this.hashtable = hashtable;
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

    public Integer getDetail() {
        return detail;
    }

    public void setDetail(Integer detail) {
        this.detail = detail;
    }
}
