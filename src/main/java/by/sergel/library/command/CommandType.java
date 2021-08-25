package by.sergel.library.command;

import by.sergel.library.command.impl.*;

import java.util.Arrays;
import java.util.Optional;

public enum CommandType {
    GO_TO_LOGIN(new GoToLoginPageCommand()),
    GO_TO_REGISTRATION(new GoToRegistrationPageCommand()),
    GO_TO_HOME(new GoToHomePageCommand()),
    LOGIN(new LoginCommand()),
    TEST(new TESTCommand());

    CommandType(Command command){
        this.command = command;
    }

    private Command command;

    public static Command getCommand(String commandName){
        Command command;
        Optional<Command> optionalCommand = Arrays.stream(values())
                .filter(c -> c.name().equalsIgnoreCase(commandName))
                .map(c -> c.command)
                .findFirst();
        command = optionalCommand.orElse(Command.DEFAULT_COMMAND);
        return command;
    }
}
