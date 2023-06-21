package org.lab_5;

public class Request {
    private String command;
    private String arg;
    private String data;
    public Request(String command,String arg,String data){
        this.command=command;
        this.data=data;
        this.arg=arg;
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
