package org.lab;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.lab.Commands.BaseCommand;
import org.lab.Commands.Exit;
import org.lab.Models.Organization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CommandsMode {

    ArrayList<String> history = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    private Lock lock;

    public void executeCommand(Hashtable<Integer, Organization> organizationHashtable, BufferedReader input, PrintWriter output, Integer userId, Connection connection) throws JsonProcessingException {
        CommandsManager cm = new CommandsManager(organizationHashtable, history,connection);
        cm.collectionOfCommands();
        lock = new ReentrantLock();
        Hashtable<String, BaseCommand> commandsMap = cm.getCommandsTable();
        Hashtable<String, BaseCommand> commandsTableForLocking = cm.getCommandsTableForLocking();
        ConsoleLog consoleLog = new ConsoleLog();
        RequestReader requestReader = new RequestReader();
        String command, arg, data;
        RequestAnswer requestAnswer = new RequestAnswer();
        String sql;
        CommandExecute outputHashtable = new CommandExecute(null, true,null,organizationHashtable);
        System.out.println(outputHashtable.getHashtable().get(1));
//        output.println(requestAnswer.sendPackage(outputHashtable));
        System.out.println("adsvd");

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        while (true) {
            try {

                String message = input.readLine();
                CommandExecute commandExecute;

                Request request = requestReader.read(message);
                request.setUserId(userId);
                System.out.println("Received message from client: " + request.getCommand() + "\n"
                        + "Id: " + request.getArg() + "\n"
                        + "data: " + request.getData() + "\n"
                        + "User id: " + request.getUserId());
                command = request.getCommand();
                if (command.equals(Exit.getName())) {
                    commandExecute = forkJoinPool.invoke(new SaveCommandTask(commandsMap.get("save"), new Request("C:\\Users\\theal\\IdeaProjects\\Lab_5.1\\src\\output.json")));
                    output.println(requestAnswer.sendPackage(commandExecute));
                    break;
                } else {
                    if (commandsTableForLocking.containsKey(command)) {
                        lock.lock();
                    }
                    BaseCommand baseCommand = commandsMap.get(command);
                    baseCommand.setUserId(userId);
                    CommandTask commandTask = new CommandTask(baseCommand, request);
                    commandExecute = forkJoinPool.invoke(commandTask);
                    if(commandsTableForLocking.containsKey(command)){
                        lock.unlock();
                    }
                    output.println(requestAnswer.sendPackage(commandExecute));

//                    lock.unlock();

                }
//                output.println(requestAnswer.sendPackage(commandExecute));

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
    static class CommandTask extends RecursiveTask<CommandExecute> {
        private final BaseCommand baseCommand;
        private final Request request;

        public CommandTask(BaseCommand baseCommand, Request request) {
            this.baseCommand = baseCommand;
            this.request = request;
        }

        @Override
        protected CommandExecute compute() {
            try {
                return baseCommand.execute(request);
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class SaveCommandTask extends RecursiveTask<CommandExecute> {
        private final BaseCommand baseCommand;
        private final Request request;

        public SaveCommandTask(BaseCommand baseCommand, Request request) {
            this.baseCommand = baseCommand;
            this.request = request;
        }

        @Override
        protected CommandExecute compute() {
            try {
                return baseCommand.execute(request);
            } catch (IOException | SQLException e) {
                System.out.println("kalvc");
                throw new RuntimeException(e);
            }
        }
    }
}
