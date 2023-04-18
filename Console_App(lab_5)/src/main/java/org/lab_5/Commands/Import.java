package org.lab_5.Commands;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.lab_5.Checker;
import org.lab_5.Models.Organization;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class Import implements BaseCommand{
    private Hashtable organizationTable;
    private static String name = "import";
    public Import(Hashtable organizationTable){this.organizationTable=organizationTable;}

    public static String getName(){
        return name;
    }

    public String getDescription() {
        return null;
    }

    public void execute(Object... o) throws IOException {
        String fileName = o[0].toString();
        File file = new File(fileName);
        Checker checker = new Checker();
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        Organization[] listOfOrganization = objectMapper.readValue(file, Organization[].class);
        int listOfOrganizationLength = listOfOrganization.length;
        for (int key = 0; key < listOfOrganizationLength; key++) {
            if (!checker.checkingOrganization(listOfOrganization[key])) {
                organizationTable.put(listOfOrganization[key].getId(), listOfOrganization[key]);
            }
        }
    }
}
