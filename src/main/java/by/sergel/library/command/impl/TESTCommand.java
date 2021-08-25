package by.sergel.library.command.impl;

import by.sergel.library.command.Command;
import by.sergel.library.command.Router;
import by.sergel.library.pool.ConnectionPool;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.sergel.library.command.PagePath.TEST;

public class TESTCommand implements Command {
    @Override
    public Router process(HttpServletRequest request) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        List<String> roles = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT role FROM roles;");
            while(set.next()){
                roles.add(set.getString("role"));
            }
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        roles.forEach(System.out::println);
        request.setAttribute("roles", roles);
        Map<String, String> map = new HashMap();
        map.put("test", "TEST");
        request.setAttribute("lol", "lol");
        request.setAttribute("map", map);
        Router router = new Router(TEST);
        return router;
    }
}
