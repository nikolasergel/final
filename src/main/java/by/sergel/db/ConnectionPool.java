package by.sergel.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final int DEFAULT_POOL_SIZE = 3;
    public static final String URL_PROPERTY = "url";
    private BlockingQueue<Connection> availableConnections;
    private BlockingQueue<Connection> busyConnections;

    private ConnectionPool() {
        availableConnections = new ArrayBlockingQueue<Connection>(DEFAULT_POOL_SIZE);
        busyConnections = new ArrayBlockingQueue<Connection>(DEFAULT_POOL_SIZE);
        Properties properties = new Properties();
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            try {
                properties.load(new FileInputStream("src/main/resources/db.properties"));
                for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                    Connection connection = DriverManager.getConnection(properties.getProperty(URL_PROPERTY), properties);
                    availableConnections.put(new ProxyConnection(connection));
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Optional<Connection> getConnection() {
        if (availableConnections.size() > 0) {
            try {
                Connection connection = availableConnections.take();
                busyConnections.put(connection);
                return Optional.of(connection);
            } catch (InterruptedException e) {
                logger.error("Exception caught: ", e); //FIXME
            }
        }
        return Optional.empty();
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            busyConnections.remove(connection);
            try {
                availableConnections.put(connection);
            } catch (InterruptedException e) {
                logger.error("Exception caught: ", e); //FIXME
            }
        }
    }

    public void closePool() {
        try {
            for (Connection connection : availableConnections) {
                ((ProxyConnection) connection).closeConnection();
            }
            for (Connection connection : busyConnections) {
                ((ProxyConnection) connection).closeConnection();
            }
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.instance;
    }

    private static class ConnectionPoolHolder {
        private static final ConnectionPool instance = new ConnectionPool();
    }
}
