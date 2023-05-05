package org.lab_5;

import org.lab_5.Commands.BaseCommand;
import org.lab_5.Models.Organization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandsMode {

    ArrayList<String> history = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void executeCommand(Hashtable<Integer, Organization> organizationHashtable){
        CommandsManager cm = new CommandsManager(organizationHashtable, history);
        cm.collectionOfCommands();
        Hashtable<String, BaseCommand> commandsMap = cm.getCommandsTable();
        ConsoleLog consoleLog = new ConsoleLog();

        while(true) {
            try {
                consoleLog.consoleResp("Введите команду");
                String[] command = sc.nextLine().split(" ");
                if (command.length > 1) {
                    //TODO
                    CommandExecute commandExecute = commandsMap.get(command[0]).execute(command[1]);
                    consoleLog.consoleRespCommand(commandExecute);

                } else {
                    consoleLog.consoleRespCommand(commandsMap.get(command[0]).execute());
                }
                if(history.size()<15){
                    history.add(command[0]);
                }else{
                    history.remove(0);
                    history.add(command[0]);
                }
            }catch (NullPointerException | IOException e){
                consoleLog.consoleResp("Неизвестная команда. Для справки по всем доступным командам пропишите help");
            }
            catch (NoSuchElementException e){
                break;
            }
        }

    }

}
