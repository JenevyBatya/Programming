package org.lab;

import org.lab.Commands.*;
import org.lab.Models.Organization;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;

public class CommandsManager {
//TODO

    private Hashtable<String, BaseCommand> commandsTable = new Hashtable();
    private Hashtable<Integer, Organization> organizationTable = new Hashtable();
    private Hashtable<String, BaseCommand> commandsTableForLocking = new Hashtable();
    private ArrayList<String> history = new ArrayList<>();
    private Connection connection;

    public CommandsManager(Hashtable organizationTable, ArrayList<String> history, Connection connection) {
        this.organizationTable = organizationTable;
        this.history = history;
        this.connection = connection;
    }


    public void collectionOfCommands() {
        commandsTable.put(Clear.getName(), new Clear(organizationTable, connection));
        commandsTableForLocking.put(Clear.getName(), new Clear(organizationTable, connection));
        commandsTable.put(Help.getName(), new Help(organizationTable, connection));
        commandsTable.put(Exit.getName(), new Exit(organizationTable, connection));
        commandsTable.put(History.getName(), new History(organizationTable,history, connection));
        commandsTable.put(Info.getName(), new Info(organizationTable, connection));
        commandsTable.put(Save.getName(), new Save(organizationTable, connection));
        commandsTable.put(Show.getName(), new Show(organizationTable, connection));
        commandsTable.put(Insert.getName(), new Insert(organizationTable, connection));
        commandsTableForLocking.put(Insert.getName(), new Insert(organizationTable, connection));
        commandsTable.put(AverageOfAnnualTurnover.getName(), new AverageOfAnnualTurnover(organizationTable, connection));
        commandsTable.put(ExecuteScript.getName(), new ExecuteScript(organizationTable, history, connection));
        commandsTableForLocking.put(ExecuteScript.getName(), new ExecuteScript(organizationTable, history, connection));
        commandsTable.put(MinByType.getName(), new MinByType(organizationTable, connection));
        commandsTable.put(PrintAscending.getName(), new PrintAscending(organizationTable, connection));
        commandsTable.put(RemoveKey.getName(), new RemoveKey(organizationTable, connection));
        commandsTableForLocking.put(RemoveKey.getName(), new RemoveKey(organizationTable, connection));
        commandsTable.put(RemoveLower.getName(), new RemoveLower(organizationTable, connection));
        commandsTableForLocking.put(RemoveLower.getName(), new RemoveLower(organizationTable, connection));
        commandsTable.put(ReplaceIfGreater.getName(), new ReplaceIfGreater(organizationTable, connection));
        commandsTableForLocking.put(ReplaceIfGreater.getName(), new ReplaceIfGreater(organizationTable, connection));
        commandsTable.put(Update.getName(), new Update(organizationTable, connection));
        commandsTableForLocking.put(Update.getName(), new Update(organizationTable, connection));
//        commandsTable.put(Import.getName(), new Import(organizationTable,connection));
    }

    public Hashtable getCommandsTable() {
        return commandsTable;
    }

    public Hashtable<String, BaseCommand> getCommandsTableForLocking() {
        return commandsTableForLocking;
    }
}
