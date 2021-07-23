

package by.sergel.library.servlet;

import by.sergel.library.command.Command;
import by.sergel.library.command.impl.GetHomePageCommand;
import by.sergel.library.command.impl.GetLoginPageCommand;
import by.sergel.library.command.impl.GetRegistrationPageCommand;
import by.sergel.library.command.impl.TESTCommand;
import by.sergel.library.pool.ConnectionPool;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(urlPatterns = {"/" })
public class MainServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final String COMMAND = "command";
    private static final String CONTEXT_PATH = "/WEB-INF/view/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandStr = request.getParameter(COMMAND);
        Command command;
        logger.info(commandStr);
        command = switch (commandStr) {
            case "home" -> new GetHomePageCommand();
            case "login" -> new GetLoginPageCommand();
            case "registration" -> new GetRegistrationPageCommand();
            case "test" -> new TESTCommand();
            default -> {
                logger.error("Command not supported: " + commandStr);
                yield Command.DEFAULT_COMMAND;
            }
        };
        String path = command.process(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(CONTEXT_PATH + path);

        System.out.println(getServletContext().getContextPath() + "/" + request.getHttpServletMapping().getMatchValue() + "/" + path);
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.destroyPool();
    }
}
