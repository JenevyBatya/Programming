package org.lab;
import org.lab.Models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

public class FileImportMode {
//    Scanner sc = new Scanner(System.in);
//    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//    Organization[] listOfOrganization = new Organization[0];
//    ConsoleLog consoleLog = new ConsoleLog();
//    Checker checker = new Checker();
    private Statement statement;

    public FileImportMode(Statement statement) {
        this.statement = statement;
    }

    Hashtable<Integer, Organization> organizationHashtable = new Hashtable<>();
    public Hashtable<Integer, Organization> importMode() throws SQLException {
        String organizationBd = "select * from organization";
        ResultSet resultSet = statement.executeQuery(organizationBd);
        while(resultSet.next()){
            Organization organization = new Organization(resultSet.getInt("id"), resultSet.getString("name"),
                    new Coordinates(resultSet.getInt("coordinateX"), resultSet.getLong("coordinateY")),
                    resultSet.getDate("creationDate").toLocalDate(), resultSet.getDouble("annualTurnover"), resultSet.getString("fullName"),
                    resultSet.getInt("employeesCount"), OrganizationType.valueOf(resultSet.getString("type")),
                    new Address(resultSet.getString("street"), new Location(resultSet.getLong("locationX"), resultSet.getFloat("locationY"), resultSet.getFloat("locationZ"))));
        organizationHashtable.put(organization.getId(), organization);
        }

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
        return organizationHashtable;

    }
}
//    public Organization importMode(String fileName){
//        while (true) {
//
//            System.out.println("Введите полный путь до файла или /cancel для выхода из режима загрузки данных");
//            fileName = sc.nextLine();
//            if (fileName.equals("/cancel")) {
//                break;
//            } else {
//                try {
//                    File file = new File(fileName);
//                    listOfOrganization = objectMapper.readValue(file, Organization[].class);
//                    break;
//                } catch (FileNotFoundException e) {
//                    System.out.println("Файл не найден");
//                }
//            }
//        }
//    }