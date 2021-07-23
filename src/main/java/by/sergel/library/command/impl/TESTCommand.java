package by.sergel.library.command.impl;

import by.sergel.library.command.Command;
import by.sergel.library.pool.ConnectionPool;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TESTCommand implements Command {
    @Override
    public String process(HttpServletRequest request) {
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
        request.setAttribute("lol", "lol");
        return "test.jsp";
    }
}
