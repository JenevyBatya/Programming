package org.lab_5;

import org.lab_5.Commands.BaseCommand;
import org.lab_5.Commands.Exit;
import org.lab_5.Models.Organization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CommandsMode {

    ArrayList<String> history = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    private Lock lock;


    public void executeCommand(Hashtable<Integer, Organization> organizationHashtable, BufferedReader input, PrintWriter output, Integer userId, Connection connection) {
        AtomicReference<CommandsManager> cm = new AtomicReference<>(new CommandsManager(organizationHashtable, history, connection));
        cm.get().collectionOfCommands();
        lock = new ReentrantLock();
        AtomicReference<Hashtable<String, BaseCommand>> commandsMap = new AtomicReference<>(cm.get().getCommandsTable());
        AtomicReference<Hashtable<String, BaseCommand>> commandsTableForLocking = new AtomicReference<>(cm.get().getCommandsTableForLocking());
        ConsoleLog consoleLog = new ConsoleLog();
        RequestReader requestReader = new RequestReader();
        String command, arg, data;
        RequestAnswer requestAnswer = new RequestAnswer();

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        Thread checkerThread = new Thread(() -> {
            while (true) {
                try {
                    // Создаем экземпляр класса Checker и вызываем метод для проверки состояния переменной
                    Checker checker = new Checker();
                    boolean result = checker.checkDatabaseVariableState(connection);

                    // Если результат проверки вернулся true, обновляем organizationHashtable
                    if (result) {
                        Statement statement = connection.createStatement();
                        cm.get().collectionOfCommands();
                        commandsMap.set(cm.get().getCommandsTable());
                        commandsTableForLocking.set(cm.get().getCommandsTableForLocking());

                        statement.executeUpdate("update Charger set value=false where id=1");
                        // Обновление organizationHashtable
                        // ...
                    }

                    // Приостанавливаем выполнение потока на 1500 мс
                    Thread.sleep(1500);
                } catch (InterruptedException | SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        // Запускаем поток для проверки состояния переменной в базе данных
        checkerThread.start();

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
                    commandExecute = forkJoinPool.invoke(new SaveCommandTask(commandsMap.get().get("save"), new Request("C:\\Users\\theal\\IdeaProjects\\Lab_5.1\\src\\output.json")));
                    output.println(requestAnswer.sendPackage(commandExecute));
                    break;
                } else {
                    if (commandsTableForLocking.get().containsKey(command)) {
                        lock.lock();
                    }
                    BaseCommand baseCommand = commandsMap.get().get(command);
                    baseCommand.setUserId(userId);
                    CommandTask commandTask = new CommandTask(baseCommand, request);
                    commandExecute = forkJoinPool.invoke(commandTask);
                    if (commandsTableForLocking.get().containsKey(command)) {
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
                throw new RuntimeException(e);
            }
        }
    }

    static class Checker {
        public boolean checkDatabaseVariableState(Connection connection) throws SQLException {
            // Здесь происходит проверка состояния переменной в базе данных
            // Верните true, если состояние переменной удовлетворяет условию, иначе верните false
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT value FROM Charger WHERE id = 1");
            boolean value = false;
            if (resultSet.next()) {
                value = resultSet.getBoolean("value");
            }
            return value;
        }
    }
}
