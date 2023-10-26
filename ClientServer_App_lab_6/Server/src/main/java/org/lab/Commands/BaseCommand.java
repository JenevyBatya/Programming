package org.lab.Commands;


import org.lab.CommandExecute;
import org.lab.Request;

import java.io.IOException;

public interface BaseCommand {

//    public static String getName(){
//     return null;
// }

    public String getDescription();
    public CommandExecute execute(Request o) throws IOException;


}
