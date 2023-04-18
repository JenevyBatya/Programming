package org.lab_5;


import org.lab_5.Models.*;

import java.io.IOException;
import java.util.*;
import java.time.LocalDate;

class EmptyLine extends NullPointerException {
}

class CancelMode extends IOException {
    @Override
    public String toString(){
        return "Проверка cancel";
    }
}

class WrongValue extends Exception {
}


public class OrganizationRegistration {


    //TODO
    public static Organization registerNewOrganization(int randomId, Hashtable<Integer, Organization> organizationTable, String command) {
        boolean mandatoryPoints = true;
        String nothing = null;
        Scanner sc = new Scanner(System.in);
        List<String> organizationTypes = Arrays.asList("COMMERCIAL", "PUBLIC", "GOVERNMENT", "TRUST", "PRIVATE_LIMITED_COMPANY");
        System.out.println("Чтобы выйти из режима регистрации новой организации, введите /cancel");
        int id = randomId;
        String name = null;
        int xCoordinates = 0;//Значение поля должно быть больше -612
        Long yCoordinates = null;//Максимальное значение поля: 420, Поле не может быть null
        Double annualTurnover = null;//Поле не может быть null, Значение поля должно быть больше 0
        String fullName = null;//Поле может быть null
        Integer employeeCount = null;//Поле может быть null, Значение поля должно быть больше 0
        String organizationType = null;//Поле может быть null
        String street = null;//Длина строки не должна быть больше 34, Поле не может быть null
        Long xLocation = null;//Поле не может быть null
        Float yLocation = null;//Поле не может быть null
        float zLocation = 0;
        OrganizationType organizationType_type = null;

        if (command.equals("insert")) {

//Handling organization's name

            while (true) {
                try {
                    System.out.println("Введите название организации(обязательно): ");
                    try {
                        name = sc.nextLine();//Поле не может быть null, Строка не может быть пустой
                        if (name.isEmpty()) {
                            throw new EmptyLine();
                        } else if (name.equals("/cancel")) {
                            mandatoryPoints = false;
                            //                        System.out.println("проверка выхода");
                            throw new CancelMode();
                        } else {

                            break;
                        }
                    } catch (EmptyLine e) {
                        System.out.println("Вы не ввели название организации");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }

            }


            //Handling xCoordinates
            while (true) {
                try {
                    System.out.println("Введите координаты организации по X(обязательно): ");
                    try {
                        String xSCoordinates = sc.nextLine();//Значение поля должно быть больше -612
                        if (xSCoordinates.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();

                        } else {
                            xCoordinates = Integer.parseInt(xSCoordinates);
                            if (xCoordinates < (-612)) {
                                throw new NumberFormatException();
                            } else {
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели координаты. Значение поля должно быть больше -612");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling yCoordinates
            while (true) {
                try {
                    System.out.println("Введите координаты организации по Y(обязательно): ");
                    try {
                        String ySCoordinates = sc.nextLine();//Максимальное значение поля: 420, Поле не может быть null
                        if (ySCoordinates.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        } else {
                            yCoordinates = Long.parseLong(ySCoordinates);
                            if (yCoordinates > 420) {
                                throw new NumberFormatException();
                            } else {
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели координаты. Максимальное значение поля: 420");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling annualTurnover
            while (true) {
                try {
                    System.out.println("Задекларируйте ежегодный оборот компании(обязательно): ");
                    try {
                        String annualTurnoverS = sc.nextLine();//Поле не может быть null, Значение поля должно быть больше 0
                        if (annualTurnoverS.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        } else {
                            annualTurnover = Double.parseDouble(annualTurnoverS);
                            if (annualTurnover <= 0) {
                                throw new NumberFormatException();
                            } else {
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели данные. Значение поля должно быть больше 0");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling fullName
            while (true) {
                try {
                    System.out.println("Введите полное название организации(необязательно): ");
                    try {
                        fullName = sc.nextLine();//Поле может быть null
                        if (fullName.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        } else {
                            break;
                        }
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling employeeCount
            while (true) {
                try {
                    System.out.println("Введите количество работников(необязательно): ");
                    try {
                        String employeeCountS = sc.nextLine();//Поле может быть null, Значение поля должно быть больше 0
                        if (employeeCountS.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        } else if (employeeCountS.isEmpty()) {
                            employeeCount = null;
                            break;
                        } else {
                            employeeCount = Integer.parseInt(employeeCountS);
                            if (employeeCount <= 0) {
                                throw new NumberFormatException();
                            } else {
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели данные. Значение поля должно быть больше 0");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling organisationType
            while (true) {
                try {
                    System.out.println("Выберите тип организации(COMMERCIAL, PUBLIC, GOVERNMENT, TRUST, PRIVATE_LIMITED_COMPANY): ");
                    try {
                        organizationType = sc.nextLine();//Поле может быть null
                        if (organizationType.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        } else {

                            if (!(organizationTypes.contains(organizationType))) {
                                throw new WrongValue();
                            } else {
                                break;
                            }
                        }
                    } catch (WrongValue e) {
                        System.out.println("Вы неверно ввели тип организации");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling street
            while (true) {
                try {
                    System.out.println("Введите улицу, на которой находится организацию: ");
                    try {
                        street = sc.nextLine();//Длина строки не должна быть больше 34, Поле не может быть null
                        if (street.isEmpty() || (street.length() > 34)) {
                            throw new WrongValue();
                        } else if (street.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        } else {
                            break;
                        }

                    } catch (CancelMode e) {
                        throw e;
                    } catch (WrongValue e) {
                        System.out.println("Вы неверно ввели данные. Длина строки не должна быть больше 34");
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling xLocation
            while (true) {
                try {
                    System.out.println("Введите координаты города по X(обязательно): ");
                    try {
                        String xLocationS = sc.nextLine();//Поле не может быть null
                        if (xLocationS.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        } else if (xLocationS.isEmpty()) {
                            throw new EmptyLine();
                        } else {
                            xLocation = Long.parseLong(xLocationS);
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели координаты");
                    } catch (CancelMode e) {
                        throw e;
                    } catch (EmptyLine e) {
                        System.out.println("Вы не ввели координаты города по X");
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling yLocation
            while (true) {
                try {
                    System.out.println("Введите координаты города по Y(обязательно): ");
                    try {
                        String yLocationS = sc.nextLine();//Поле не может быть null
                        if (yLocationS.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        } else if (yLocationS.isEmpty()) {
                            throw new EmptyLine();
                        } else {
                            yLocation = Float.parseFloat(yLocationS);
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели координаты");
                    } catch (CancelMode e) {
                        throw e;
                    } catch (EmptyLine e) {
                        System.out.println("Вы не ввели координаты города по Y");
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling zLocation
            while (true) {
                try {
                    System.out.println("Введите координаты города по Z(необязательно): ");
                    try {
                        String zLocationS = sc.nextLine();//Поле может быть null, Значение поля должно быть больше 0
                        if (zLocationS.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        } else if (zLocationS.isEmpty()) {

                            break;
                        } else {
                            zLocation = Float.parseFloat(zLocationS);
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели данные");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }


            return new Organization(id, name, new Coordinates(xCoordinates, yCoordinates), LocalDate.now(), annualTurnover, fullName, employeeCount, OrganizationType.valueOf(organizationType), new Address(street, new Location(xLocation, yLocation, zLocation)));










        }else{
            while (true) {
                try {
                    System.out.println("Введите новое название организации: ");
                    try {
                        name = sc.nextLine();//Поле не может быть null, Строка не может быть пустой
                        if (name.isEmpty()) {
                            name = organizationTable.get(randomId).getName();
                            break;
                        } else if (name.equals("/cancel")) {
                            mandatoryPoints = false;
                            //                        System.out.println("проверка выхода");
                            throw new CancelMode();
                        } else {

                            break;
                        }

                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }

            }


            //Handling xCoordinates
            while (true) {
                try {
                    System.out.println("Введите новые координаты организации по X: ");
                    try {
                        String xSCoordinates = sc.nextLine();//Значение поля должно быть больше -612
                        if (xSCoordinates.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        }else if(xSCoordinates.isEmpty()){
                            xCoordinates = organizationTable.get(randomId).getCoordinates().getX();
                            break;
                        } else {
                            xCoordinates = Integer.parseInt(xSCoordinates);
                            if (xCoordinates > (-612)) {
                                throw new NumberFormatException();
                            } else {
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели координаты");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling yCoordinates
            while (true) {
                try {
                    System.out.println("Введите новые координаты организации по Y: ");
                    try {
                        String ySCoordinates = sc.nextLine();//Максимальное значение поля: 420, Поле не может быть null
                        if (ySCoordinates.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        }else if(ySCoordinates.isEmpty()){
                            yCoordinates = organizationTable.get(randomId).getCoordinates().getY();
                            break;
                        } else {
                            yCoordinates = Long.parseLong(ySCoordinates);
                            if (yCoordinates > 420) {
                                throw new NumberFormatException();
                            } else {
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели координаты");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling annualTurnover
            while (true) {
                try {
                    System.out.println("Задекларируйте новый ежегодный оборот компании: ");
                    try {
                        String annualTurnoverS = sc.nextLine();//Поле не может быть null, Значение поля должно быть больше 0
                        if (annualTurnoverS.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        }else if(annualTurnoverS.isEmpty()){
                            annualTurnover = organizationTable.get(randomId).getAnnualTurnover();
                            break;
                        } else {
                            annualTurnover = Double.parseDouble(annualTurnoverS);
                            if (annualTurnover <= 0) {
                                throw new NumberFormatException();
                            } else {
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели данные");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling fullName
            while (true) {
                try {
                    System.out.println("Введите полное новое название организации: ");
                    try {
                        fullName = sc.nextLine();//Поле может быть null
                        if (fullName.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        }else if(fullName.isEmpty()){
                            fullName = organizationTable.get(randomId).getFullName();
                            break;
                        } else {
                            break;
                        }
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling employeeCount
            while (true) {
                try {
                    System.out.println("Введите новое количество работников: ");
                    try {
                        String employeeCountS = sc.nextLine();//Поле может быть null, Значение поля должно быть больше 0
                        if (employeeCountS.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        } else if (employeeCountS.isEmpty()) {
                            employeeCount = organizationTable.get(randomId).getEmployeesCount();
                            break;
                        } else {
                            employeeCount = Integer.parseInt(employeeCountS);
                            if (employeeCount <= 0) {
                                throw new NumberFormatException();
                            } else {
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели данные");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling organisationType
            while (true) {
                try {
                    System.out.println("Выберите новый тип организации(COMMERCIAL, PUBLIC, GOVERNMENT, TRUST, PRIVATE_LIMITED_COMPANY): ");
                    try {
                        organizationType = sc.nextLine();//Поле может быть null
                        if (organizationType.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        }else if(organizationType.isEmpty()){
                            organizationType_type = organizationTable.get(randomId).getType();
                            break;
                        } else {

                            if (!(organizationTypes.contains(organizationType))) {
                                throw new WrongValue();
                            } else {
                                break;
                            }
                        }
                    } catch (WrongValue e) {
                        System.out.println("Вы неверно ввели тип организации");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling street
            while (true) {
                try {
                    System.out.println("Введите новую улицу, на которой находится организацию: ");
                    try {
                        street = sc.nextLine();//Длина строки не должна быть больше 34, Поле не может быть null
                        if ((street.length() > 34)) {
                            throw new WrongValue();
                        }else if(street.isEmpty()){
                            street = organizationTable.get(randomId).getPostalAddress().getStreet();
                            break;
                        } else if (street.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        } else {
                            break;
                        }

                    } catch (CancelMode e) {
                        throw e;
                    } catch (WrongValue e) {
                        System.out.println("Вы неверно ввели данные");
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling xLocation
            while (true) {
                try {
                    System.out.println("Введите новые координаты города по X: ");
                    try {
                        String xLocationS = sc.nextLine();//Поле не может быть null
                        if (xLocationS.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        } else if (xLocationS.isEmpty()) {
                            xLocation = organizationTable.get(randomId).getPostalAddress().getTown().getX();
                            break;
                        } else {
                            xLocation = Long.parseLong(xLocationS);
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели координаты");
                    } catch (CancelMode e) {
                        throw e;
                    } catch (EmptyLine e) {
                        System.out.println("Вы не ввели координаты города по X");
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling yLocation
            while (true) {
                try {
                    System.out.println("Введите новые координаты города по Y: ");
                    try {
                        String yLocationS = sc.nextLine();//Поле не может быть null
                        if (yLocationS.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        } else if (yLocationS.isEmpty()) {
                            yLocation = organizationTable.get(randomId).getPostalAddress().getTown().getY();
                            break;
                        } else {
                            yLocation = Float.parseFloat(yLocationS);
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели координаты");
                    } catch (CancelMode e) {
                        throw e;
                    } catch (EmptyLine e) {
                        System.out.println("Вы не ввели координаты города по Y");
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            //Handling zLocation
            while (true) {
                try {
                    System.out.println("Введите новые координаты города по Z: ");
                    try {
                        String zLocationS = sc.nextLine();//Поле может быть null, Значение поля должно быть больше 0
                        if (zLocationS.equals("/cancel")) {
                            mandatoryPoints = false;
                            throw new CancelMode();
                        } else if (zLocationS.isEmpty()) {
                            zLocation = organizationTable.get(randomId).getPostalAddress().getTown().getZ();
                            break;
                        } else {
                            zLocation = Float.parseFloat(zLocationS);
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели данные");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    System.out.println("Выход из режима регистрации организации");
                    return new Organization(nothing);
                }
            }

            return new Organization(id, name, new Coordinates(xCoordinates, yCoordinates), organizationTable.get(id).getCreationDate(), annualTurnover, fullName, employeeCount, organizationType_type, new Address(street, new Location(xLocation, yLocation, zLocation)));

        }
    }
}
