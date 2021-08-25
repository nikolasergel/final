package by.sergel.library.command.impl;

import by.sergel.library.command.Command;
import by.sergel.library.command.Router;
import jakarta.servlet.http.HttpServletRequest;

import static by.sergel.library.command.PagePath.LOGIN;

public class GoToLoginPageCommand implements Command {
    @Override
    public Router process(HttpServletRequest request) {
        return new Router(LOGIN);
    }
}
