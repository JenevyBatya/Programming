//package org.lab_5;
//import com.fasterxml.jackson.core.exc.StreamReadException;
//import com.fasterxml.jackson.databind.DatabindException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.lab_5.Models.Organization;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Hashtable;
//import java.util.Scanner;
//
//public class fileImportMode {
//    Scanner sc = new Scanner(System.in);
//    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//    Organization[] listOfOrganization = new Organization[0];
//    ConsoleLog consoleLog = new ConsoleLog();
//    Checker checker = new Checker();
//    Hashtable<Integer, Organization> organizationHashtable = new Hashtable<>();
//    public Hashtable<Integer, Organization> importMode(String fileName){
//        while (true) {
//            if (fileName.equals("/cancel")) {
//                break;
//            } else {
//                try {
//                    File file = new File(fileName);
//                    listOfOrganization = objectMapper.readValue(file, Organization[].class);
//                    break;
//                } catch (FileNotFoundException e) {
//                    consoleLog.consoleResp("Файл не найден");
//                    consoleLog.consoleResp("Введите полный путь до файла или /cancel для выхода из режима загрузки данных");
////                    System.out.println("Файл не найден");
////                    System.out.println("Введите полный путь до файла или /cancel для выхода из режима загрузки данных");
//                    fileName = sc.nextLine();
//                } catch (StreamReadException e) {
//                    throw new RuntimeException(e);
//                } catch (DatabindException e) {
//                    throw new RuntimeException(e);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//
//
//        }
//        int invalidValues = 0;
//        int listOfOrganizationLength = listOfOrganization.length;
//        for (int key = 0; key < listOfOrganizationLength; key++) {
//            if (!checker.checkingOrganization(listOfOrganization[key])) {
//                organizationHashtable.put(listOfOrganization[key].getId(), listOfOrganization[key]);
//            } else {
//                invalidValues++;
//            }
//        }
//        if (invalidValues != 0) {
//            if (invalidValues == 1) {
//                System.out.println("В процессе обработки " + invalidValues + " organization не была занесена в коллекцию из-за недопустимых значений");
//            } else {
//                System.out.println("В процессе обработки " + invalidValues + " organizations не были занесены в коллекцию из-за недопустимых значений");
//            }
//        }
//
//        if (listOfOrganizationLength == 0) {
//            System.out.println("Время заполнить коллекцию");
//        } else if (listOfOrganizationLength - invalidValues == 1) {
//            System.out.println(listOfOrganization.length - invalidValues + " organization была успешно занесена в коллекцию и готова к дальнейшей работе");
//        } else {
//            System.out.println(listOfOrganization.length - invalidValues + " organizations были успешно занесены в коллекцию и готовы к дальнейшей работе");
//        }
//        return organizationHashtable;
//
//    }
//}
////    public Organization importMode(String fileName){
////        while (true) {
////
////            System.out.println("Введите полный путь до файла или /cancel для выхода из режима загрузки данных");
////            fileName = sc.nextLine();
////            if (fileName.equals("/cancel")) {
////                break;
////            } else {
////                try {
////                    File file = new File(fileName);
////                    listOfOrganization = objectMapper.readValue(file, Organization[].class);
////                    break;
////                } catch (FileNotFoundException e) {
////                    System.out.println("Файл не найден");
////                }
////            }
////        }
////    }