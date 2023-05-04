package org.lab_5.Commands;


import org.lab_5.Models.Organization;
import org.lab_5.Models.OrganizationType;
import org.lab_5.OrganizationRegistration;
import org.lab_5.RandomNumber;

import java.io.IOException;
import java.util.Hashtable;



public class Insert implements BaseCommand{
    private Hashtable<Integer, Organization> organizationTable;;
    public Insert(Hashtable organizationTable){
        this.organizationTable = organizationTable;
    }
    private static String name = "insert";

    private String description = name + ": добавление нового элемента с заданным ключом\n";
    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public void execute(Object... o){
        OrganizationRegistration organizationRegistration = new OrganizationRegistration();
        Organization organization = organizationRegistration.registerNewOrganization(RandomNumber.createRandomNum(organizationTable), organizationTable, "insert");
        if (organization.getId()!=0){
            organizationTable.put(organization.getId(), organization);
        }
    }


}
