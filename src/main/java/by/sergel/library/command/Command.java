package by.sergel.library.command;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    static final String ERROR_PATH = "/error404";
    String process(HttpServletRequest request);
    Command DEFAULT_COMMAND = new Command() {
        @Override
        public String process(HttpServletRequest request) {
            return ERROR_PATH;
        }
    };
}
