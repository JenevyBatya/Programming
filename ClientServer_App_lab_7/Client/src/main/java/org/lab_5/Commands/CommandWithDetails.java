package org.lab_5.Commands;

import org.lab_5.CommandExecute;

import java.io.IOException;

public interface CommandWithDetails {
    public CommandExecute execute(Object... o) throws IOException;
}
