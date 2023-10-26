package org.lab;

import org.lab.Commands.*;
import org.lab.Models.Organization;

import java.util.Hashtable;

public class CommandsManager {
//TODO

    private Hashtable<String, BaseCommand> commandsTable = new Hashtable();
    private Hashtable<String,CommandWithDetails> withOrganizationData = new Hashtable();
    private Hashtable<String,CommandWithDetails> withOrganizationDetails = new Hashtable<>();
    private Hashtable<Integer, Organization> organizationTable = new Hashtable();


    public void collectionOfCommands(){
        commandsTable.put(Clear.getName(), new Clear(organizationTable));
        commandsTable.put(Help.getName(), new Help(organizationTable));
        commandsTable.put(Exit.getName(), new Exit(organizationTable));
        commandsTable.put(History.getName(), new History(organizationTable));
        commandsTable.put(Info.getName(), new Info(organizationTable));
        commandsTable.put(Show.getName(), new Show(organizationTable));
        commandsTable.put(Insert.getName(), new Insert(organizationTable));
        withOrganizationData.put(Insert.getName(), new Insert(organizationTable));
        commandsTable.put(AverageOfAnnualTurnover.getName(), new AverageOfAnnualTurnover(organizationTable));
        commandsTable.put(ExecuteScript.getName(), new ExecuteScript(organizationTable));
        commandsTable.put(MinByType.getName(), new MinByType(organizationTable));
        commandsTable.put(PrintAscending.getName(), new PrintAscending(organizationTable));
        commandsTable.put(RemoveKey.getName(),new RemoveKey(organizationTable));
        commandsTable.put(RemoveLower.getName(), new RemoveLower(organizationTable));
        commandsTable.put(ReplaceIfGreater.getName(), new ReplaceIfGreater(organizationTable));
        withOrganizationDetails.put(ReplaceIfGreater.getName(), new ReplaceIfGreater(organizationTable));
        commandsTable.put(Update.getName(), new Update(organizationTable));
        withOrganizationData.put(Update.getName(), new Update(organizationTable));
    }
    public Hashtable getCommandsTable(){
        return commandsTable;
    }

    public Hashtable<String, CommandWithDetails> getWithOrganizationData() {
        return withOrganizationData;
    }

    public Hashtable<String, CommandWithDetails> getWithOrganizationDetails() {
        return withOrganizationDetails;
    }
}
