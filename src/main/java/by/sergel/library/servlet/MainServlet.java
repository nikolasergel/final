package by.sergel.library.servlet;

import by.sergel.library.command.Command;
import by.sergel.library.command.CommandType;
import by.sergel.library.command.Router;
import by.sergel.library.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(urlPatterns = {"/main/*"})
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
        String commandName = request.getParameter(COMMAND);
        Command command = CommandType.getCommand(commandName);
        Router router = command.process(request);
        dispatch(request, response, router);
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, Router router) throws ServletException, IOException {
        switch (router.getDispatcherType()){
            case FORWARD -> request.getRequestDispatcher(CONTEXT_PATH + router.getPagePath()).forward(request, response);
            case REDIRECT -> response.sendRedirect(CONTEXT_PATH + router.getPagePath());
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.destroyPool();
    }
}
