package com.gregperlinli.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * <h1><span style='color:red;'>Tools for operating the database</span></h1>
 *
 * @author gregperlinli
 * @since 2021-5-20
 * @version 2.0.1
 */
public class JDBCUtils {

    /* Database connection method */

    /**
     * <h2><span style='color:red;'>Get the Connection of the database</span></h2>
     *
     * @return Connection
     * @throws Exception e
     */
    public static Connection getConnection() throws Exception {

        ResourceBundle jdbc = ResourceBundle.getBundle("jdbc");

        final String DRIVER_CLASS = jdbc.getString("jdbc.driver"),
                URL = jdbc.getString("jdbc.url"),
                USER = jdbc.getString("jdbc.username"),
                PASSWORD = jdbc.getString("jdbc.password");

        // load the Driver
        Class.forName(DRIVER_CLASS);

        // get connection
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        // System.out.println("Connection status: " + conn);

        return conn;
    }

    /**
     * <h2><span style='color:red;'>Close the resource of the database in update mode</span></h2>
     *
     * @param conn the connection of the database
     * @param ps the PreparedStatement of the database
     *
     */
    public static void closeResource(Connection conn, Statement ps) {
        try {
            if ( ps != null ) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if ( conn != null) {
            connectionPool.returnConnection(conn);
        }
    }


    /**
     * <h1><b><span style='color:yellow;background:背景颜色;font-size:文字大小;'>Attention! the following connection method is using with database connection pools</span></b></h1>
     * <br/>
     *
     * <h2><span style='color:red;'>Using database connection pool with ThreadLocal to ensure thread safety<br/>
     * The database connection pool just one can be provided</span></h2>
     */
    private static ConnectionPool connectionPool;
    private static final ThreadLocal<Connection> CONN_THREAD = new ThreadLocal<>();
    static {
        try {
            // ResourceBundle jdbc = ResourceBundle.getBundle("jdbc");
            connectionPool  = new ConnectionPool("jdbc");
            connectionPool.createPool();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <h2><span style='color:red;'>Using database connection pool to get connection</span></h2>
     *
     * @return connection with database connection pool
     * @throws Exception e
     *
     */
    public static Connection getConnectionWithPool() throws Exception {
        // return CPDS.getConnection();
        Connection conn = CONN_THREAD.get();
        if ( conn == null ) {
            // get connection from database connection pool
            conn = connectionPool.getConnection();
            // save to ThreadLocal object, for later jdbc operations
            CONN_THREAD.set(conn);
            // set connection to manual management transaction
            conn.setAutoCommit(false);
        }
        return conn;
    }


    /* Close resource method */

    /**
     * <h2><span style='color:red;'>Close the resource of the database in query mode</span></h2>
     *
     * @param conn the connection of the database
     * @param ps the statement of the database
     * @param rs the result set of the database
     *
     */
    public static void closeResource(Connection conn, Statement ps, ResultSet rs) {
        try {
            if ( ps != null ) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if ( conn != null) {
            connectionPool.returnConnection(conn);
        }
        try {
            if ( rs != null ) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <h2><span style='color:red;'>Commit the transaction and close the connection</span></h2>
     */
    public static void commitAndClose() {
        Connection conn = CONN_THREAD.get();
        if ( conn != null ) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connectionPool.returnConnection(conn);
            }
        }
        // Be sure to implement the remove operation, otherwise, an error will occur (Because the bottom layer of Tomcat server is used for thread pool technology)
        CONN_THREAD.remove();
    }

    /**
     * <h2><span style='color:red;'>RollBack the transaction and close the connection</span></h2>
     */
    public static void rollBackAndClose() {
        Connection conn = CONN_THREAD.get();
        if ( conn != null ) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connectionPool.returnConnection(conn);
            }
        }
        // Be sure to implement the remove operation, otherwise, an error will occur (Because the bottom layer of Tomcat server is used for thread pool technology)
        CONN_THREAD.remove();
    }
}
