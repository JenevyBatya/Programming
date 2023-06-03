package org.lab_5.Commands;


import org.lab_5.CommandExecute;
import org.lab_5.Request;

import java.io.IOException;
import java.sql.SQLException;

public interface BaseCommand {

    //    public static String getName(){
//     return null;
// }
    public void setUserId(Integer userId);

    public String getDescription();

    public CommandExecute execute(Request o) throws IOException, SQLException;


}
