package by.sergel.library.command.impl;

import by.sergel.library.command.Command;
import by.sergel.library.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public Router process(HttpServletRequest request) {
        return null;
    }
}
