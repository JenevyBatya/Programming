package org.lab;

public class ConsoleLog {
    public void consoleRespCommand(CommandExecute commandExecute){
        System.out.println(commandExecute.getResponse());
    }
    public void consoleResp(String response){
        System.out.println(response);
    }
}
