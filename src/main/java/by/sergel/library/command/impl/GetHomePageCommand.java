package by.sergel.library.command.impl;

import by.sergel.library.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class GetHomePageCommand implements Command {
    @Override
    public String process(HttpServletRequest request) {
        return "index.jsp";
    }
}
