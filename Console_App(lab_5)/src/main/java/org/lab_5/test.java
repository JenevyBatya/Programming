package org.lab_5;
import org.junit.Test;
import org.lab_5.Commands.BaseCommand;
import org.lab_5.Models.Organization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class test {
    @Test
    public void test1() throws IOException {
        Hashtable<Integer, Organization> organizationHashtable = new Hashtable<>();
        CommandsManager cm = new CommandsManager(organizationHashtable, null);
        cm.collectionOfCommands();
        Hashtable<String, BaseCommand> commandsMap = cm.getCommandsTable();
        commandsMap.get("import").execute("C:\\Users\\theal\\IdeaProjects\\Lab_5.1\\src\\data.json");
        commandsMap.get("show").execute();

        assertEquals(4,organizationHashtable.size());
    }
    @Test
    public void test2() {
    }

}
