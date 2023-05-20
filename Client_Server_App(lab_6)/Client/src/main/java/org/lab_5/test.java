//package org.lab_5;
//import org.junit.Test;
//import org.lab_5.Commands.BaseCommand;
//import org.lab_5.Models.*;
//import java.io.*;
//import java.util.*;
//import static org.junit.Assert.assertEquals;
//
//
//public class test {
//
//    @Test
//    public void testImportCommand() throws IOException {
//        Hashtable<Integer, Organization> organizationHashtable = new Hashtable<>();
//        CommandsManager cm = new CommandsManager(organizationHashtable, null);
//        cm.collectionOfCommands();
//        Hashtable<String, BaseCommand> commandsMap = cm.getCommandsTable();
//        commandsMap.get("import").execute("C:\\Users\\theal\\IdeaProjects\\Lab_5.1\\src\\data.json");
//
//        assertEquals(4, organizationHashtable.size());
//    }
//
//    @Test
//    public void testSizeOfCommandMap() {
//
//        CommandsManager cm = new CommandsManager(null, null);
//        cm.collectionOfCommands();
//        Hashtable<String, BaseCommand> commandsMap = cm.getCommandsTable();
//        assertEquals(17, commandsMap.size());
//    }
//
//
//    @Test
//
//    public void testNotImportedOrganization() {
//        FileImportMode fileImportMode = new FileImportMode();
//        SystemOutputStream out = new SystemOutputStream();
//        System.setOut(new PrintStream(out));
//        Hashtable<Integer, Organization> organizationHashtable = fileImportMode.importMode("C:\\Users\\theal\\IdeaProjects\\Lab_5.1\\src\\data.json");
//        assertEquals("В процессе обработки 1 organization не была занесена в коллекцию из-за недопустимых значений4 organizations были успешно занесены в коллекцию и готовы к дальнейшей работе", out.toString());
//
//    }
//
//
//    @Test
//    public void testHelp() throws IOException {
//        FileImportMode fileImportMode = new FileImportMode();
//        Hashtable<Integer, Organization> organizationHashtable = fileImportMode.importMode("C:\\Users\\theal\\IdeaProjects\\Lab_5.1\\src\\data.json");
//        CommandsManager commandsManager = new CommandsManager(organizationHashtable, null);
//        commandsManager.collectionOfCommands();
//        Hashtable<String, BaseCommand> commandHashtable = commandsManager.getCommandsTable();
//        SystemOutputStream out = new SystemOutputStream();
//        System.setOut(new PrintStream(out));
//        commandHashtable.get("help").execute(null);
//        String descriptions = "";
//        for (String name: commandHashtable.keySet()){
//            if(name.equals("import")){
//                continue;
//            }
//            descriptions+=commandHashtable.get(name).getDescription();
//        }
//        assertEquals(descriptions, out.toString());
//    }
//
//    @Test
//    public void testInfo() throws IOException {
//        FileImportMode fileImportMode = new FileImportMode();
//        Hashtable<Integer, Organization> organizationHashtable = fileImportMode.importMode("C:\\Users\\theal\\Desktop\\итмо\\Прога итмо\\Console_App(lab_5)\\src\\Show.json");
//        CommandsManager commandsManager = new CommandsManager(organizationHashtable, null);
//        commandsManager.collectionOfCommands();
//        Hashtable<String, BaseCommand> commandHashtable = commandsManager.getCommandsTable();
//        SystemOutputStream out = new SystemOutputStream();
//        System.setOut(new PrintStream(out));
//        commandHashtable.get("info").execute(null);
//        assertEquals("Тип коллекции: java.util.HashtableРазмер коллекции: 3", out.toString());
//    }
//
//    @Test
//    public void testPrintAscending() throws IOException {
//        FileImportMode fileImportMode = new FileImportMode();
//        Hashtable<Integer, Organization> organizationHashtable = fileImportMode.importMode("C:\\Users\\theal\\Desktop\\итмо\\Прога итмо\\Console_App(lab_5)\\src\\Show.json");
//        CommandsManager commandsManager = new CommandsManager(organizationHashtable, null);
//        commandsManager.collectionOfCommands();
//        Hashtable<String, BaseCommand> commandHashtable = commandsManager.getCommandsTable();
//        SystemOutputStream out = new SystemOutputStream();
//        System.setOut(new PrintStream(out));
//        commandHashtable.get("print_ascending").execute(null);
//        assertEquals("id_12 Прекрасный Грегори Хаус, Annual turnover: 2980.0, Organization type: GOVERNMENTid_1 Грегори и сыновья, Annual turnover: 250000.0, Organization type: COMMERCIALid_45 Queen, Annual turnover: 345678.0, Organization type: PUBLIC", out.toString());
//
//    }
//
//    @Test
//    public void testRemoveKeyUnsuccessful() throws IOException {
//        FileImportMode fileImportMode = new FileImportMode();
//        Hashtable<Integer, Organization> organizationHashtable = fileImportMode.importMode("C:\\Users\\theal\\IdeaProjects\\Lab_5.1\\src\\data.json");
//        CommandsManager commandsManager = new CommandsManager(organizationHashtable, new ArrayList<>());
//        commandsManager.collectionOfCommands();
//        Hashtable<String, BaseCommand> commandHashtable = commandsManager.getCommandsTable();
//        SystemOutputStream out = new SystemOutputStream();
//        System.setOut(new PrintStream(out));
//        int idForRemoval = 1;
//        commandHashtable.get("remove_key").execute(idForRemoval);
//        assertEquals("Организации с id_"+idForRemoval+" не существует",out.toString());
//
//    }
//    @Test
//    public void testRemoveKeySuccessful() throws IOException {
//        FileImportMode fileImportMode = new FileImportMode();
//        Hashtable<Integer, Organization> organizationHashtable = fileImportMode.importMode("C:\\Users\\theal\\IdeaProjects\\Lab_5.1\\src\\data.json");
//        CommandsManager commandsManager = new CommandsManager(organizationHashtable, new ArrayList<>());
//        commandsManager.collectionOfCommands();
//        Hashtable<String, BaseCommand> commandHashtable = commandsManager.getCommandsTable();
//        SystemOutputStream out = new SystemOutputStream();
//        System.setOut(new PrintStream(out));
//        int idForRemoval = 125;
//        commandHashtable.get("remove_key").execute(idForRemoval);
//        assertEquals("Организация с id_"+idForRemoval+ " была успешно удалена из реестра",out.toString());
//
//    }
//
//    @Test
//    public void testShow() throws IOException {
//        FileImportMode fileImportMode = new FileImportMode();
//        Hashtable<Integer, Organization> organizationHashtable = fileImportMode.importMode("C:\\Users\\theal\\Desktop\\итмо\\Прога итмо\\Console_App(lab_5)\\src\\Show.json");
//        CommandsManager commandsManager = new CommandsManager(organizationHashtable, new ArrayList<>());
//        commandsManager.collectionOfCommands();
//        Hashtable<String, BaseCommand> commandHashtable = commandsManager.getCommandsTable();
//        SystemOutputStream out = new SystemOutputStream();
//        System.setOut(new PrintStream(out));
//        commandHashtable.get("show").execute();
//        assertEquals("id_45 Queen, Annual turnover: 345678.0, Organization type: PUBLICid_12 Прекрасный Грегори Хаус, Annual turnover: 2980.0, Organization type: GOVERNMENTid_1 Грегори и сыновья, Annual turnover: 250000.0, Organization type: COMMERCIAL",out.toString());
//
//
//    }
//}
//
//    // Вспомогательный класс для перехвата вывода в System.out
//    class SystemOutputStream extends ByteArrayOutputStream {
//
//        private final String lineSeparator = System.lineSeparator();
//
//        @Override
//        public String toString() {
//            return new String(this.toByteArray()).replace(lineSeparator, "");
//        }
//
//    }
//
//
//
