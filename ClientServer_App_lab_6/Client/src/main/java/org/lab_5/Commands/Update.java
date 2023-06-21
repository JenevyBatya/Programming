package org.lab_5.Commands;

import org.lab_5.*;
import org.lab_5.Models.*;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class Update implements BaseCommand, CommandWithDetails{
    private Hashtable<Integer, Organization> organizationTable;;
    public Update(Hashtable organizationTable){
        this.organizationTable = organizationTable;
    }
    private static String name = "update";

    private String description = name + ":  обновление значения элемента коллекции, id которого равен заданному\n";
    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    CommandExecute commandExecute;
    private String organisationData = "";


    public CommandExecute execute(Object... o){
        String nothing = null;
        Scanner sc = new Scanner(System.in);
        List<String> organizationTypes = Arrays.asList("COMMERCIAL", "PUBLIC", "GOVERNMENT", "TRUST", "PRIVATE_LIMITED_COMPANY");
        ConsoleLog consoleLog = new ConsoleLog();
//        int id = randomId;
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
        String organizationData = "";
        try {
            //TODO

            int id = Integer.parseInt(o[0].toString());
            consoleLog.consoleResp("Чтобы выйти из режима регистрации новой организации, введите /cancel");


            while (true) {
                try {
                    System.out.println("Введите новое название организации: ");
                    try {
                        name = sc.nextLine();//Поле не может быть null, Строка не может быть пустой
                        if (name.equals("/cancel")) {
                            //                        System.out.println("проверка выхода");
                            throw new CancelMode();
                        }else if(name.isEmpty()) {
                            organizationData += null + " ";
                            break;

                        } else {
                            organizationData+=name+" ";
                            break;
                        }

                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    return new CommandExecute("Выход из режима регистрации организации",false);
                }

            }


            //Handling xCoordinates
            while (true) {
                try {
                    System.out.println("Введите новые координаты организации по X: ");
                    try {
                        String xSCoordinates = sc.nextLine();//Значение поля должно быть больше -612
                        if (xSCoordinates.equals("/cancel")) {
                            throw new CancelMode();
                        }
                        else if(xSCoordinates.isEmpty()){
                            organizationData+=null+" ";
                            break;

                        }else {
                            xCoordinates = Integer.parseInt(xSCoordinates);
                            if (xCoordinates > (-612)) {
                                throw new NumberFormatException();
                            } else {
                                organizationData+=xSCoordinates+" ";
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели координаты");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    return new CommandExecute("Выход из режима регистрации организации",false);
                }
            }

            //Handling yCoordinates
            while (true) {
                try {
                    System.out.println("Введите новые координаты организации по Y: ");
                    try {
                        String ySCoordinates = sc.nextLine();//Максимальное значение поля: 420, Поле не может быть null
                        if (ySCoordinates.equals("/cancel")) {
                            throw new CancelMode();

                        }else if(ySCoordinates.isEmpty()){
                            organizationData+=null+" ";
                            break;
                        } else {
                            yCoordinates = Long.parseLong(ySCoordinates);
                            if (yCoordinates > 420) {
                                throw new NumberFormatException();
                            } else {
                                organizationData+=ySCoordinates+" ";
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели координаты");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    return new CommandExecute("Выход из режима регистрации организации",false);
                }
            }

            //Handling annualTurnover
            while (true) {
                try {
                    System.out.println("Задекларируйте новый ежегодный оборот компании: ");
                    try {
                        String annualTurnoverS = sc.nextLine();//Поле не может быть null, Значение поля должно быть больше 0
                        if (annualTurnoverS.equals("/cancel")) {
                            throw new CancelMode();
                        }else if(annualTurnoverS.isEmpty()){
                            organizationData+=null+" ";
                            break;
                        }else {
                            annualTurnover = Double.parseDouble(annualTurnoverS);
                            if (annualTurnover <= 0) {
                                throw new NumberFormatException();
                            } else {
                                organizationData+=annualTurnoverS+" ";
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели данные");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    return new CommandExecute("Выход из режима регистрации организации",false);
                }
            }

            //Handling fullName
            while (true) {
                try {
                    System.out.println("Введите полное новое название организации: ");
                    try {
                        fullName = sc.nextLine();//Поле может быть null
                        if (fullName.equals("/cancel")) {
                            throw new CancelMode();
                        }else if(fullName.isEmpty()){
                            organizationData+=null+" ";
                            break;
                        } else {
                            organizationData+=fullName+" ";
                            break;
                        }
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    return new CommandExecute("Выход из режима регистрации организации",false);
                }
            }

            //Handling employeeCount
            while (true) {
                try {
                    System.out.println("Введите новое количество работников: ");
                    try {
                        String employeeCountS = sc.nextLine();//Поле может быть null, Значение поля должно быть больше 0
                        if (employeeCountS.equals("/cancel")) {
                            throw new CancelMode();
                        }else if(employeeCountS.isEmpty()){
                            organizationData+=null+" ";
                            break;
                        } else {
                            employeeCount = Integer.parseInt(employeeCountS);
                            if (employeeCount <= 0) {
                                throw new NumberFormatException();
                            } else {
                                organizationData+=employeeCountS+" ";
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели данные");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    return new CommandExecute("Выход из режима регистрации организации",false);
                }
            }

            //Handling organisationType
            while (true) {
                try {
                    System.out.println("Выберите новый тип организации(COMMERCIAL, PUBLIC, GOVERNMENT, TRUST, PRIVATE_LIMITED_COMPANY): ");
                    try {
                        organizationType = sc.nextLine();//Поле может быть null
                        if (organizationType.equals("/cancel")) {
                            throw new CancelMode();
                        }else if(organizationType.isEmpty()){
                            organizationData+=null+" ";
                            break;
                        } else {

                            if (!(organizationTypes.contains(organizationType))) {
                                throw new WrongValue();
                            } else {
                                organizationData+=organizationType+" ";
                                break;
                            }
                        }
                    } catch (WrongValue e) {
                        System.out.println("Вы неверно ввели тип организации");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    return new CommandExecute("Выход из режима регистрации организации",false);
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
                            organizationData+=null+" ";
                            break;
                        } else if (street.equals("/cancel")) {
                            throw new CancelMode();
                        } else {
                            organizationData+=street+" ";
                            break;
                        }

                    } catch (CancelMode e) {
                        throw e;
                    } catch (WrongValue e) {
                        System.out.println("Вы неверно ввели данные");
                    }
                } catch (CancelMode e) {
                    return new CommandExecute("Выход из режима регистрации организации",false);
                }
            }

            //Handling xLocation
            while (true) {
                try {
                    System.out.println("Введите новые координаты города по X: ");
                    try {
                        String xLocationS = sc.nextLine();//Поле не может быть null
                        if (xLocationS.equals("/cancel")) {
                            throw new CancelMode();
                        } else if (xLocationS.isEmpty()) {
                            organizationData+=null+" ";
                            break;
                        } else {
                            xLocation = Long.parseLong(xLocationS);
                            organizationData+=xLocationS+" ";
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
                    return new CommandExecute("Выход из режима регистрации организации",false);
                }
            }

            //Handling yLocation
            while (true) {
                try {
                    System.out.println("Введите новые координаты города по Y: ");
                    try {
                        String yLocationS = sc.nextLine();//Поле не может быть null
                        if (yLocationS.equals("/cancel")) {
                            throw new CancelMode();
                        } else if (yLocationS.isEmpty()) {
                            organizationData+=null+" ";
                            break;
                        } else {
                            yLocation = Float.parseFloat(yLocationS);
                            organizationData+=yLocationS+" ";
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
                    return new CommandExecute("Выход из режима регистрации организации",false);
                }
            }

            //Handling zLocation
            while (true) {
                try {
                    System.out.println("Введите новые координаты города по Z: ");
                    try {
                        String zLocationS = sc.nextLine();//Поле может быть null, Значение поля должно быть больше 0
                        if (zLocationS.equals("/cancel")) {
                            throw new CancelMode();
                        } else if (zLocationS.isEmpty()) {
                            organizationData+=null+" ";
                            break;
                        } else {
                            zLocation = Float.parseFloat(zLocationS);
                            organizationData+=zLocationS+" ";
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Вы неверно ввели данные");
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {

                    return new CommandExecute("Выход из режима регистрации организации",false);
                }
            }return new CommandExecute(organizationData,true);

//            return new Organization(name, new Coordinates(xCoordinates, yCoordinates), null, annualTurnover, fullName, employeeCount, organizationType_type, new Address(street, new Location(xLocation, yLocation, zLocation)));








        }catch (ArrayIndexOutOfBoundsException e){

            return new CommandExecute("Неправильный синтаксис команды. Укажите id организации, данные которой вы хотите обновить",false);
        }
//        return new CommandExecute(organizationData,true);
    }

}
