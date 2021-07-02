package by.sergel.repository.impl;

import by.sergel.db.ConnectionPool;
import by.sergel.entity.User;
import by.sergel.entity.UserRole;
import by.sergel.entity.UserStatus;
import by.sergel.repository.Repository;
import by.sergel.repository.Specification;
import com.mysql.cj.jdbc.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class UserRepository implements Repository<Integer, User> {
    private static final Logger logger = LogManager.getLogger();
    private static final String SELECT_BY_ID
            = "SELECT id, login, password, email, first_name, last_name," +
              "patronymic, phone, picture, confirmation_code, status, role " +
              "FROM users " +
              "INNER JOIN roles ON id_role_roles = id_role " +
              "INNER JOIN statuses ON id_status_statuses = id_status;";

    private static final String INSERT_USER
            = "INSERT INTO users (login, password, email, first_name, last_name," +
            "patronymic, phone, picture, confirmation_code, id_status_statuses, id_role_roles) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    @Override
    public User get(Integer id) {
        User user = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(Statement statement = connection.createStatement()){
            ResultSet set = statement.executeQuery(SELECT_BY_ID);
            if(set.next()){
                user = new User();
                user.setId(set.getInt("id"));
                user.setLogin(set.getString("login"));
                user.setPassword(set.getString("password"));
                user.setEmail(set.getString("email"));
                user.setFirstName(set.getString("first_name"));
                user.setLastName(set.getString("last_name"));
                user.setPatronymic(set.getString("patronymic"));
                user.setPhone(set.getString("phone"));
                user.setPicture(set.getBinaryStream("picture"));
                user.setStatus(UserStatus.values()[set.getInt("status")]);//FIXME
                user.setRole(UserRole.values()[set.getInt("role")]);//FIXME
                user.setConfirmationCode(set.getString("confirmation_code"));
            }
        } catch (SQLException e) {
            logger.error("Can't create statement!", e);
        }
        return null;
    }

    @Override
    public boolean add(User user) {
        boolean flag = false;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement statement = connection.prepareStatement(INSERT_USER)){
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getPatronymic());
            statement.setString(7, user.getPhone());
            statement.setBlob(8, user.getPicture());
            statement.setString(9, user.getConfirmationCode());
            statement.setInt(10, user.getStatus().ordinal());
            statement.setInt(11, user.getRole().ordinal());
            flag = true;
        } catch (SQLException e) {
            logger.error("Can't create statement!", e);//FIXME
        }
        return flag;
    }
    //TODO 07.02.2021 16:19
    @Override
    public boolean update(User element) {
        return false;
    }

    @Override
    public boolean remove(User element) {
        return false;
    }

    @Override
    public List<User> query(Specification specification) {
        return null;
    }
}
