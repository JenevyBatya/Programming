package org.lab.Commands;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.lab.CommandExecute;
import org.lab.Models.Organization;
import org.lab.Request;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Hashtable;

public class Save implements BaseCommand{

    private Hashtable<Integer, Organization> organizationTable;;
    public Save(Hashtable organizationTable, Connection connection){
        this.organizationTable = organizationTable;
    }
    private static String name = "save";
    private String response;

    private String description = name + " path: сохранение коллекции в файл\n";
    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public CommandExecute execute(Request o) {
        try {
            String file = o.getCommand();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
            try {
                writer.writeValue(new File(file), organizationTable);
                response="Коллекция была успешно сохранена в " + file;
            }catch (FileNotFoundException e){
                return new CommandExecute("Файл не найден",false);
            }
        }catch (IOException e){
            e.printStackTrace();
            return new CommandExecute(null,false);
        }catch (ArrayIndexOutOfBoundsException e){
            return new CommandExecute("Неправильный синтаксис команды. Укажите полный путь до файла после команды",false);
        }
        return new CommandExecute(response,true);
    }
    public void setUserId(Integer userId) {

    }

}
