package by.sergel.library.command;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    String ERROR_PATH = "/error404";
    Router process(HttpServletRequest request);
    Command DEFAULT_COMMAND = request -> new Router(ERROR_PATH);
}
