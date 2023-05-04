package org.lab_5.Commands;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.lab_5.Models.Organization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

public class Save implements BaseCommand{

    private Hashtable<Integer, Organization> organizationTable;;
    public Save(Hashtable organizationTable){
        this.organizationTable = organizationTable;
    }
    private static String name = "save";

    private String description = name + " path: сохранение коллекции в файл\n";
    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void execute(Object... o) {
        try {
            String file = o[0].toString();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
            try {
                writer.writeValue(new File(file), organizationTable);
                System.out.println("Коллекция была успешно сохранена в " + file);
            }catch (FileNotFoundException e){
                System.out.println("Файл не найден");
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Неправильный синтаксис команды. Укажите полный путь до файла после команды");
        }
    }

}
