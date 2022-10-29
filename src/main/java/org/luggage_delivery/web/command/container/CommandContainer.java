package org.luggage_delivery.web.command.container;
/*
  User: admin
  Cur_date: 21.10.2022
  Cur_time: 21:04
*/

import org.luggage_delivery.web.command.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private final Map<String, Command> commands = new HashMap<>();

    public void addCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    public boolean containerContains(String commandName) {
        return commands.containsKey(commandName);
    }

    @Override
    public String toString() {
        return "Container has: " + commands;
    }
}
