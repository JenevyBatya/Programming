package org.lab;

import org.lab.Models.Organization;

public class Checker {
    public boolean checkingOrganization(Organization organization){

        boolean invalidValue = false;
        try {
            if (organization.getId() <= 0) {
                invalidValue = true;
                System.out.println("id");
            } else if ((organization.getName().isEmpty()) || organization.getName() == null) {
                invalidValue = true;
                System.out.println("name");
            } else if (organization.getCoordinates() == null || organization.getCoordinates().getX() < (-612) || organization.getCoordinates().getY() > 420) {
                invalidValue = true;
                System.out.println("coordinated");
            } else if (organization.getCreationDate() == null) {
                invalidValue = true;
                System.out.println("date");
            } else if (organization.getAnnualTurnover() <= 0 || organization.getAnnualTurnover() == null) {
                invalidValue = true;
                System.out.println("annualTurnover");
            } else if (organization.getEmployeesCount() != null && organization.getEmployeesCount() <= 0) {
                invalidValue = true;
                System.out.println("employees");
            } else if (organization.getPostalAddress() == null || organization.getPostalAddress().getStreet() == null || (organization.getPostalAddress().getStreet() != null && organization.getPostalAddress().getStreet().length() > 34) || organization.getPostalAddress().getTown() == null || organization.getPostalAddress().getTown().getX() == null || organization.getPostalAddress().getTown().getY() == null) {
                invalidValue = true;
                System.out.println("address");
            }
        }catch (NullPointerException e){
            invalidValue = true;
        }
        return invalidValue;
    }

}
