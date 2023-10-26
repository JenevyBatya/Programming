package org.lab;

public class Request {
    private String command;
    private String arg;
    private String data;
    private String username;
    private String password;
    private boolean auth;
    public Request(String command,String arg,String data){
        this.command=command;
        this.data=data;
        this.arg=arg;
    }

    public Request( boolean auth,String command, String username, String password) {
        this.command = command;
        this.username = username;
        this.password = password;
        this.auth = auth;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public String getCommand() {
        return command;
    }

    public String getArg() {
        return arg;
    }

    public String getData() {
        return data;
    }
}
