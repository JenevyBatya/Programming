package org.lab.Commands;

import org.lab.CommandExecute;

import java.io.IOException;

public interface CommandWithDetails {
    public CommandExecute execute(Object... o) throws IOException;
}
