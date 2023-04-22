package org.lab_5;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.lab_5.Commands.BaseCommand;
import org.lab_5.Models.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;


import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;



public class test {
    Console console = mock(Console.class);

//    Hashtable<Integer, Organization> organizationHashtableMock = Mockito.mock(Hashtable.class);
//    Mockito.when(organizationHashtableMock.put(Mockito.anyInt(), Mockito.any(Organization.class))).thenReturn(null);

//    @Mock
//    private Hashtable<Integer, Organization> organizationHashtableMock;
//
//    @Spy
//    private Hashtable<Integer, Organization> organizationHashtableSpy = new Hashtable<>();
//    private CommandsMode commandsMode;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private InputStream systemIn;
    private PrintStream systemOut;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    @Before
    public void setUp() {
        systemIn = System.in;
        systemOut = System.out;
    }

    @After
    public void tearDown() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void test1() throws IOException {
        Hashtable<Integer, Organization> organizationHashtable = new Hashtable<>();
        CommandsManager cm = new CommandsManager(organizationHashtable, null);
        cm.collectionOfCommands();
        Hashtable<String, BaseCommand> commandsMap = cm.getCommandsTable();
        commandsMap.get("import").execute("C:\\Users\\theal\\IdeaProjects\\Lab_5.1\\src\\data.json");

        assertEquals(4, organizationHashtable.size());
    }

    @Test
    public void test2() {

        CommandsManager cm = new CommandsManager(null, null);
        cm.collectionOfCommands();
        Hashtable<String, BaseCommand> commandsMap = cm.getCommandsTable();


        assertEquals(17, commandsMap.size());
    }



    @Test
    public void testExecuteCommand() throws IOException {
        Console console = mock(Console.class);
        FileImportMode fileImportMode = new FileImportMode();
        Scanner sc = new Scanner(System.in);
        Hashtable<Integer,Organization> organizationHashtable = fileImportMode.importMode("C:\\Users\\theal\\IdeaProjects\\Lab_5.1\\src\\data.json");
        CommandsMode commandsMode = new CommandsMode();
        commandsMode.executeCommand(organizationHashtable);

//
//
//        String inputString = "help\n";
//        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
//        System.setIn(inputStream);
//        commandsMode.executeCommand(organizationHashtable);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PrintStream printStream = new PrintStream(outputStream);
//        System.setOut(printStream);
//
//        commandsMode.executeCommand(organizationHashtable);
//
//        List<String> expectedOutput = Arrays.asList(
//                "> You entered: hello",
//                ""
//        );
//
//        String[] actualOutputArray = outputStream.toString().split(System.lineSeparator());
//        List<String> actualOutput = Arrays.asList(actualOutputArray);
//        assertEquals(expectedOutput, actualOutput);



        ////        verify(console).print(
////                "В процессе обработки 1 organization не была занесена в коллекцию из-за недопустимых значений\n"
////        );
        when(sc.nextLine()).thenReturn("help");
        System.setIn("help\n");
        verify(console).print("heeelp");
//        verify(console, times(1)).print("В процессе обработки 1 organization не была занесена в коллекцию из-за недопустимых значений\n");




//        ByteArrayInputStream in = new ByteArrayInputStream("help\ninfo\nhelp\nexit".getBytes());
//        System.setIn(in);
//
//        // Создаем имитацию вывода команд
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));

//        Hashtable<Integer, Organization> organizationHashtable = new Hashtable<>();
//        organizationHashtable.put(1, new Organization(4, "NameForTest2", new Coordinates(100, 100L), LocalDate.now(), 660000d, "TestFullName", 6, OrganizationType.PUBLIC, new Address("Snowdin", new Location(1L, 1f, 1))));

//        String input = "show\nhelp\ninfo\nhistory\nexit\n";
//        System.setIn(new ByteArrayInputStream(input.getBytes()));

//

//        assertEquals("1. name1\nВведите команду\n", outContent.toString());

//        public void executeCommandTest() {
//            doReturn(null).when(organizationHashtableSpy).put(anyInt(), any(Organization.class));
//            doReturn(new Organization(4,"NameForTest2", new Coordinates(100,100L), LocalDate.now(),660000d, "TestFullName", 6, OrganizationType.PUBLIC, new Address("Snowdin", new Location(1L,1f,1))))
//                    .when(organizationHashtableSpy).get(1);
//            doReturn(new Organization(7,"NameForTest2", new Coordinates(100,100L), LocalDate.now(),660000d, "TestFullName", 6, OrganizationType.PUBLIC, new Address("Snowdin", new Location(1L,1f,1))))
//                    .when(organizationHashtableSpy).get(2);
//
//            doReturn(new BaseCommand() {
//                @Override
//                public String getDescription() {
//                    return null;
//                }
//
//                @Override
//                public void execute(Object... o) throws IOException {
//
//                }
//
//                @Override
//                public void execute() {
//                    System.out.println("help");
//                }
//            }).when(commandsMode).getCommandByName("help");
//
//            commandsMode.executeCommand(organizationHashtableMock);
//            verify(commandsMode).getCommandByName("help");
//        }


//        String input = "help\ninfo\nexit\n";
//        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
//
//        Scanner scanner = new Scanner(inputStream);
//
//
//
//        FileImportMode fileImportMode = mock(FileImportMode.class);
////        fileImportMode.importMode("C:\\Users\\theal\\IdeaProjects\\Lab_5.1\\src\\data.json");
//        when(fileImportMode.importMode("C:\\Users\\theal\\IdeaProjects\\Lab_5.1\\src\\data.json")).thenReturn();
//
////        when(scanner.readLine()).thenReturn("help","exit");
////        when(scanner.readLine()).thenReturn("exit");


    }
    @Test
    public void test3(){
        CommandsManager commandsManager = mock(CommandsManager.class);
    }
}

