package by.sergel.library.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();

    private static final String PROPERTIES_PATH = "db.properties";

    private static final int DEFAULT_POOL_SIZE = 3;
    public static final String URL_PROPERTY = "url";
    private BlockingQueue<Connection> availableConnections;
    private BlockingQueue<Connection> busyConnections;

    private ConnectionPool() {
        availableConnections = new ArrayBlockingQueue<Connection>(DEFAULT_POOL_SIZE);
        busyConnections = new ArrayBlockingQueue<Connection>(DEFAULT_POOL_SIZE);
        Properties properties = new Properties();
        //FIXME configuring pool with properties
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_PATH)) {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            properties.load(inputStream);
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                String url = properties.getProperty(URL_PROPERTY);
                Connection connection = DriverManager.getConnection(url, properties);
                availableConnections.put(new ProxyConnection(connection));
            }
        } catch (SQLException e) {
            logger.error("Can't register driver: ", e);
        } catch (IOException e) {
            logger.error("Can't load or get property. Properties filepath: " + PROPERTIES_PATH, e);
        } catch (InterruptedException e) {
            logger.error("Exception was caught: ", e);
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = availableConnections.take();
            busyConnections.put(connection);
        } catch (InterruptedException e) {
            logger.error("Thread was interrupted: ", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            busyConnections.remove(connection);
            try {
                availableConnections.put(connection);
            } catch (InterruptedException e) {
                logger.error("Thread was interrupted: ", e);
                Thread.currentThread().interrupt();
            }
        } else {
            logger.warn("Trying to release connection that wasn't created in the connection pool!");
        }
    }

    public void destroyPool() {
        try {
            ProxyConnection proxyConnection;
            for (Connection connection : availableConnections) {
                proxyConnection = (ProxyConnection) connection;
                proxyConnection.closeConnection();
            }
            for (Connection connection : busyConnections) {
                proxyConnection = (ProxyConnection) connection;
                proxyConnection.closeConnection();
            }
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            logger.error("Can't close connection or deregister driver: ", e);
        }
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.instance;
    }

    private static class ConnectionPoolHolder {
        private static final ConnectionPool instance = new ConnectionPool();
    }
}
