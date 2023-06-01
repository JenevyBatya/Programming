package org.lab_5;

import org.lab_5.Commands.*;
import org.lab_5.Models.Organization;
import org.lab_5.Models.OrganizationType;

import java.util.ArrayList;
import java.util.Hashtable;

public class CommandsManager {
//TODO

    private Hashtable<String, BaseCommand> commandsTable = new Hashtable();
    private Hashtable<Integer, Organization> organizationTable = new Hashtable();
    private ArrayList<String> history = new ArrayList<>();
    public CommandsManager(Hashtable organizationTable, ArrayList<String> history){
        this.organizationTable=organizationTable;
        this.history=history;
    }



    public void collectionOfCommands(){
        commandsTable.put(Clear.getName(), new Clear(organizationTable));
        commandsTable.put(Help.getName(), new Help(organizationTable));
        commandsTable.put(Exit.getName(), new Exit(organizationTable));
        commandsTable.put(History.getName(), new History(organizationTable, history));
        commandsTable.put(Info.getName(), new Info(organizationTable));
        commandsTable.put(Save.getName(), new Save(organizationTable));
        commandsTable.put(Show.getName(), new Show(organizationTable));
        commandsTable.put(Insert.getName(), new Insert(organizationTable));
        commandsTable.put(AverageOfAnnualTurnover.getName(), new AverageOfAnnualTurnover(organizationTable));
        commandsTable.put(ExecuteScript.getName(), new ExecuteScript(organizationTable,history));
        commandsTable.put(MinByType.getName(), new MinByType(organizationTable));
        commandsTable.put(PrintAscending.getName(), new PrintAscending(organizationTable));
        commandsTable.put(RemoveKey.getName(),new RemoveKey(organizationTable));
        commandsTable.put(RemoveLower.getName(), new RemoveLower(organizationTable));
        commandsTable.put(ReplaceIfGreater.getName(), new ReplaceIfGreater(organizationTable));
        commandsTable.put(Update.getName(), new Update(organizationTable));
//        commandsTable.put(Import.getName(), new Import(organizationTable));
    }
    public Hashtable getCommandsTable(){
        return commandsTable;
    }

}
