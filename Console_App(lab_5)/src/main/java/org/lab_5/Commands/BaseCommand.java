package org.lab_5.Commands;


import java.io.IOException;

public interface BaseCommand {

//    public static String getName(){
//     return null;
// }

    public String getDescription();
    public void execute(Object... o) throws IOException;


}
