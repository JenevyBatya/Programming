package org.lab_5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lab_5.Commands.BaseCommand;
import org.lab_5.Commands.CommandWithDetails;
import org.lab_5.Commands.Exit;
import org.lab_5.Models.Organization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class CommandsMode {
    ArrayList<String> history = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    Socket socket;

    public CommandsMode() {
    }

    public CommandsMode(Socket socket) {
        this.socket = socket;
    }

    public void executeCommand(BufferedReader input, PrintWriter output){
        CommandsManager cm = new CommandsManager();
        cm.collectionOfCommands();
        Hashtable<String, BaseCommand> commandsMap = cm.getCommandsTable();
        Hashtable<String, CommandWithDetails> withOrganizationData = cm.getWithOrganizationData();
        Hashtable<String,CommandWithDetails> withOrganizationDetails = cm.getWithOrganizationDetails();
        ConsoleLog consoleLog = new ConsoleLog();
        CommandExecute commandExecute = null;
        Request inputObject;
        ObjectMapper objectMapper = new ObjectMapper();
        ReceptionReader receptionReader = new ReceptionReader();

        while(true) {

            try {
                consoleLog.consoleResp("Введите команду");
                String[] command = sc.nextLine().split(" ");
                if (command.length > 1){
                    if (withOrganizationData.containsKey(command[0])){
                        commandExecute = withOrganizationData.get(command[0]).execute(command[1]);

                        //TODO
                    }else if(withOrganizationDetails.containsKey(command[0])){
                        commandExecute = withOrganizationDetails.get(command[0]).execute(command[1]);

                    } else{

                        commandExecute = commandsMap.get(command[0]).execute(command[1]);
                    }
                    if (commandExecute.isSuccess()){
                        //send data
                        inputObject = new Request(command[0],command[1],commandExecute.getResponse());
                        String json = objectMapper.writeValueAsString(inputObject);
                        output.println(json);
                        String message = input.readLine();
                        CommandExecute commandExecute1 = objectMapper.readValue(message, CommandExecute.class);
                        consoleLog.consoleRespCommand(commandExecute1);


                    }else{
                        consoleLog.consoleRespCommand(commandExecute);}

                }else{
                    if (withOrganizationData.containsKey(command[0])){
                        commandExecute = withOrganizationData.get(command[0]).execute();


                        //TODO
                    }else if(withOrganizationDetails.containsKey(command[0])){
                        commandExecute = withOrganizationDetails.get(command[0]).execute();

                    }
                    else{
                        commandExecute = commandsMap.get(command[0]).execute();
                    }

                    if (commandExecute.isSuccess()){
                        //send data
                        inputObject = new Request(command[0],null,commandExecute.getResponse());
                        String json = objectMapper.writeValueAsString(inputObject);
                        output.println(json);
                        String message = input.readLine();
                        CommandExecute commandExecute1 = objectMapper.readValue(message, CommandExecute.class);
                        consoleLog.consoleRespCommand(commandExecute1);
                        if (command[0].equals(Exit.getName())){
                            break;
                        }

                    }else{
                        consoleLog.consoleRespCommand(commandExecute);}

                }
            }catch (NullPointerException | IOException e){
                consoleLog.consoleResp("Неизвестная команда. Для справки по всем доступным командам пропишите help");
            }
            catch (NoSuchElementException e){
                break;
            }
        }

        }
//        public void commandsModeOff(){
//        try {
//            System.out.println("Программа сбросила соединение с сервером");
//            socket.close();
//        }catch (IOException e){
//            System.out.println(e);
//        }
//
//        }

}
