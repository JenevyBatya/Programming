package org.lab;

import org.lab.Commands.BaseCommand;
import org.lab.Commands.Exit;
import org.lab.Models.Organization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandsMode {

    ArrayList<String> history = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void executeCommand(Hashtable<Integer, Organization> organizationHashtable, BufferedReader input, PrintWriter output) {
        CommandsManager cm = new CommandsManager(organizationHashtable, history);
        cm.collectionOfCommands();
        Hashtable<String, BaseCommand> commandsMap = cm.getCommandsTable();
        ConsoleLog consoleLog = new ConsoleLog();
        RequestReader requestReader = new RequestReader();
        String command, arg, data;
        RequestAnswer requestAnswer = new RequestAnswer();

        while (true) {
            try {

                String message = input.readLine();
                CommandExecute commandExecute;

                Request request = requestReader.read(message);
                System.out.println("Received message from client: " + request.getCommand() + "\n"
                        + "Id: " + request.getArg() + "\n"
                        + "data: " + request.getData());
                command = request.getCommand();
                if(command.equals(Exit.getName())){
                    commandExecute = commandsMap.get("save").execute(new Request("C:\\Users\\theal\\IdeaProjects\\Lab_5.1\\src\\output.json"));
                    output.println(requestAnswer.sendPackage(commandExecute));
                    break;
                }else {
                    commandExecute = commandsMap.get(command).execute(request);
                }
                output.println(requestAnswer.sendPackage(commandExecute));

                if (history.size() < 15) {
                    history.add(command);
                } else {
                    history.remove(0);
                    history.add(command);
                }

            } catch (NoSuchElementException e) {
                break;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
