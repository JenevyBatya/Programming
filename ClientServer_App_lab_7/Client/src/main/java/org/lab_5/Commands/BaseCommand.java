package org.lab_5.Commands;


import org.lab_5.CommandExecute;

import java.io.IOException;

public interface BaseCommand {

//    public static String getName(){
//     return null;
// }

    public String getDescription();
    public CommandExecute execute(Object... o) throws IOException;


}
