package org.lab.Commands;


import org.lab.*;
import org.lab.Models.*;

import java.time.LocalDate;
import java.util.*;


public class Insert implements BaseCommand, CommandWithDetails {
    //    TODO
    private Hashtable<Integer, Organization> organizationTable;
    ;

    public Insert(Hashtable organizationTable) {
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

    public CommandExecute execute(Object... o) {
        String organizationData = "";
        String nothing = null;
        Scanner sc = new Scanner(System.in);
        List<String> organizationTypes = Arrays.asList("COMMERCIAL", "PUBLIC", "GOVERNMENT", "TRUST", "PRIVATE_LIMITED_COMPANY");
        System.out.println("Чтобы выйти из режима регистрации новой организации, введите /cancel");
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
        Organization organization = null;
        try {


            while (true) {
                try {
                    System.out.println("Введите название организации(обязательно): ");
                    try {
                        name = sc.nextLine();//Поле не может быть null, Строка не может быть пустой
                        if (name.isEmpty()) {
                            throw new EmptyLine();
                        } else if (name.equals("/cancel")) {
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
                    throw new CancelMode();
                }

            }


            //Handling xCoordinates
            while (true) {
                try {
                    System.out.println("Введите координаты организации по X(обязательно): ");
                    try {
                        String xSCoordinates = sc.nextLine();//Значение поля должно быть больше -612
                        if (xSCoordinates.equals("/cancel")) {
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
                    throw new CancelMode();
                }
            }

            //Handling yCoordinates
            while (true) {
                try {
                    System.out.println("Введите координаты организации по Y(обязательно): ");
                    try {
                        String ySCoordinates = sc.nextLine();//Максимальное значение поля: 420, Поле не может быть null
                        if (ySCoordinates.equals("/cancel")) {
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
                    throw new CancelMode();
                }
            }

            //Handling annualTurnover
            while (true) {
                try {
                    System.out.println("Задекларируйте ежегодный оборот компании(обязательно): ");
                    try {
                        String annualTurnoverS = sc.nextLine();//Поле не может быть null, Значение поля должно быть больше 0
                        if (annualTurnoverS.equals("/cancel")) {
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
                    throw new CancelMode();
                }
            }

            //Handling fullName
            while (true) {
                try {
                    System.out.println("Введите полное название организации(необязательно): ");
                    try {
                        fullName = sc.nextLine();//Поле может быть null
                        if (fullName.equals("/cancel")) {
                            throw new CancelMode();
                        } else {
                            break;
                        }
                    } catch (CancelMode e) {
                        throw e;
                    }
                } catch (CancelMode e) {
                    throw new CancelMode();
                }
            }

            //Handling employeeCount
            while (true) {
                try {
                    System.out.println("Введите количество работников(необязательно): ");
                    try {
                        String employeeCountS = sc.nextLine();//Поле может быть null, Значение поля должно быть больше 0
                        if (employeeCountS.equals("/cancel")) {
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
                    throw new CancelMode();
                }
            }

            //Handling organisationType
            while (true) {
                try {
                    System.out.println("Выберите тип организации(COMMERCIAL, PUBLIC, GOVERNMENT, TRUST, PRIVATE_LIMITED_COMPANY): ");
                    try {
                        organizationType = sc.nextLine();//Поле может быть null
                        if (organizationType.equals("/cancel")) {
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
                    throw new CancelMode();
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
                    throw new CancelMode();
                }
            }

            //Handling xLocation
            while (true) {
                try {
                    System.out.println("Введите координаты города по X(обязательно): ");
                    try {
                        String xLocationS = sc.nextLine();//Поле не может быть null
                        if (xLocationS.equals("/cancel")) {
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
                    throw new CancelMode();
                }
            }

            //Handling yLocation
            while (true) {
                try {
                    System.out.println("Введите координаты города по Y(обязательно): ");
                    try {
                        String yLocationS = sc.nextLine();//Поле не может быть null
                        if (yLocationS.equals("/cancel")) {
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
                    throw new CancelMode();
                }
            }

            //Handling zLocation
            while (true) {
                try {
                    System.out.println("Введите координаты города по Z(необязательно): ");
                    try {
                        String zLocationS = sc.nextLine();//Поле может быть null, Значение поля должно быть больше 0
                        if (zLocationS.equals("/cancel")) {
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
                    throw new CancelMode();
                }
            }

        } catch (CancelMode e) {

            System.out.println("Выход из режима регистрации организации");
            return new CommandExecute(null, false);
        }


        organizationData += name + " " + xCoordinates + " " + yCoordinates + " " +
                LocalDate.now() + " " + annualTurnover + " " + fullName + " " +
                employeeCount + " " + organizationType + " " + street + " " +
                xLocation + " " + yLocation + " " +
                zLocation;

        return new CommandExecute(organizationData, true);

    }


}
