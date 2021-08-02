package com.gregperlinli.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * <h1><span style='color:red;'>MySQL数据库连接池</span></h1>
 *
 * @author gregperlinli
 * @since 2021-7-20
 * @version 1.0.2
 */
public class ConnectionPool {

    /**
     * 数据库驱动
     */
    private String jdbcDriver = "";
    /**
     * 数据 URL
     */
    private String dbUrl = "";
    /**
     * 数据库用户名
     */
    private String dbUsername = "";
    /**
     * 数据库用户密码
     */
    private String dbPassword = "";
    /**
     * 测试连接是否可用的测试表名，默认没有测试表
     */
    private String testTable = "";
    /**
     * 连接池的初始大小
     */
    private int initialSize = 10;
    /**
     * 连接池自动增加的大小
     */
    private int incrementalConnections = 5;
    /**
     * 连接池最大的大小
     */
    private int maxActive = 50;
    /**
     * 存放连接池中数据库连接的向量 , 初始时为 <code>null</code>
     */
    private Vector<PooledConnection> connections = null;

    /**
     * <h2><span style='color:red;'>设置数据库连接池参数（直接提供JDBC配置文件名）</span></h2>
     * <h2><span style='color:yellow;'>推荐使用该方法</h2>
     *
     * @param bundleName 提供一个jdbc的properties配置文件的名称（注意要放在<b>resources</b>目录下）
     */
    public ConnectionPool(String bundleName) {
        ResourceBundle jdbc = ResourceBundle.getBundle(bundleName);
        this.jdbcDriver = jdbc.getString("jdbc.driver");
        this.dbUrl = jdbc.getString("jdbc.url");
        this.dbUsername = jdbc.getString("jdbc.username");
        this.dbPassword = jdbc.getString("jdbc.password");
        if ( jdbc.containsKey("pool.initialSize") ) {
            this.initialSize = Integer.parseInt(jdbc.getString("pool.initialSize"));
        }
        if ( jdbc.containsKey("pool.incrementalConnections") ) {
            this.incrementalConnections = Integer.parseInt(jdbc.getString("pool.incrementalConnections"));
        }
        if ( jdbc.containsKey("pool.maxActive") ) {
            this.maxActive = Integer.parseInt(jdbc.getString("pool.maxActive"));
        }
    }

    /**
     * <h2><span style='color:red;'>设置数据库连接池参数（单独提供参数）</span></h2>
     *
     * @param jdbcDriver 提供数据库驱动
     * @param dbUrl 提供数据 URL
     * @param dbUsername 提供数据库用户名
     * @param dbPassword 提供数据库用户密码
     */
    public ConnectionPool(String jdbcDriver, String dbUrl, String dbUsername,String dbPassword) {
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public int getInitialSize() {
        return this.initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getIncrementalConnections() {
        return this.incrementalConnections;
    }

    public void setIncrementalConnections(int incrementalConnections) {
        this.incrementalConnections = incrementalConnections;
    }

    public int getMaxActive() {
        return this.maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public String getTestTable() {
        return this.testTable;
    }

    public void setTestTable(String testTable) {
        this.testTable = testTable;
    }

    /**
     * <h2><span style='color:red;'>创建一个数据库连接池</span></h2>
     * @throws Exception 抛出错误
     */
    public synchronized void createPool() throws Exception {
        // 确保连接池没有创建
        // 假如连接池已经创建了，保存连接的向量 connections 不会为空
        if (connections != null) {
            // 假如已经创建，则返回
            return;
        }
        // 实例化 JDBC Driver 中指定的驱动类实例
        Driver driver = (Driver) (Class.forName(this.jdbcDriver).newInstance());
        // 注册 JDBC 驱动程序
        DriverManager.registerDriver(driver);
        // 创建保存连接的向量 , 初始时有 0 个元素
        connections = new Vector<PooledConnection>();
        // 根据 initialSize 中设置的值，创建连接。
        createConnections(this.initialSize);
        System.out.println(" 数据库连接池创建成功！ ");
    }


    private void createConnections(int numConnections) throws SQLException {
        // 循环创建指定数目的数据库连接
        for (int x = 0; x < numConnections; x++) {
            // 是否连接池中的数据库连接的数量已经达到最大？最大值由类成员 maxActive
            // 指出，假如 maxActive 为 0 或负数，表示连接数量没有限制。
            // 假如连接数已经达到最大，即退出。
            if (this.maxActive > 0 && this.connections.size() >= this.maxActive) {
                break;
            }
            //add a new PooledConnection object to connections vector
            // 增加一个连接到连接池中（向量 connections 中）
            try {
                connections.addElement(new PooledConnection(newConnection()));
            } catch (SQLException e) {
                System.out.println(" 创建数据库连接失败！ " + e.getMessage());
                throw new SQLException();
            }
            System.out.println(" 数据库连接己创建 ......");
        }
    }

    private Connection newConnection() throws SQLException {
        // 创建一个数据库连接
        Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        // 假如这是第一次创建数据库连接，即检查数据库，获得此数据库答应支持的最大客户连接数目
        // connections.size() == 0 表示目前没有连接己被创建
        if (connections.size() == 0) {
            DatabaseMetaData metaData = conn.getMetaData();
            int driverMaxConnections = metaData.getMaxConnections();
            // 数据库返回的 driverMaxConnections 若为 0 ，
            // 表示此数据库没有最大连接限制，或数据库的最大连接限制不知道

            // driverMaxConnections 为返回的一个整数，表示此数据库答应客户连接的数目
            // 假如连接池中设置的最大连接数量大于数据库答应的连接数目 , 则置连接池的最大
            // 连接数目为数据库答应的最大数目
            if (driverMaxConnections > 0 && this.maxActive > driverMaxConnections) {
                this.maxActive = driverMaxConnections;
            }
        }
        // 返回创建的新的数据库连接
        return conn;
    }

    /**
     * <h2><span style='color:red;'>从数据库连接池中获取一个连接</span></h2>
     *
     * @return 数据库连接池中的一个连接
     * @throws SQLException 抛出SQL错误
     */
    public synchronized Connection getConnection() throws SQLException {
        // 确保连接池己被创建
        if (connections == null) {
            // 连接池还没创建，则返回 null
            return null;
        }
        // 获得一个可用的数据库连接
        Connection conn = getFreeConnection();
        // 假如目前没有可以使用的连接，即所有的连接都在使用中
        while (conn == null) {
            // 等一会再试
            wait(250);
            // 重新再试，直到获得可用的连接，假如
            conn = getFreeConnection();
            // getFreeConnection() 返回的为 null
            // 则表明创建一批连接后也不可获得可用连接
        }
        // 返回获得的可用的连接
        return conn;
    }

    private Connection getFreeConnection() throws SQLException {
        // 从连接池中获得一个可用的数据库连接
        Connection conn = findFreeConnection();
        if (conn == null) {
            // 假如目前连接池中没有可用的连接
            // 创建一些连接
            createConnections(incrementalConnections);
            // 重新从池中查找是否有可用连接
            conn = findFreeConnection();
            if (conn == null) {
                // 假如创建连接后仍获得不到可用的连接，则返回 null
                return null;
            }
        }
        return conn;
    }

    private Connection findFreeConnection() throws SQLException {
        Connection conn = null;
        PooledConnection pConn = null;
        // 获得连接池向量中所有的对象
        Enumeration<PooledConnection> enumerate = connections.elements();
        // 遍历所有的对象，看是否有可用的连接
        while (enumerate.hasMoreElements()) {
            pConn = (PooledConnection) enumerate.nextElement();
            if (!pConn.isBusy()) {
                // 假如此对象不忙，则获得它的数据库连接并把它设为忙
                conn = pConn.getConnection();
                pConn.setBusy(true);
                // 测试此连接是否可用
                if (!testConnection(conn)) {
                    // 假如此连接不可再用了，则创建一个新的连接，
                    // 并替换此不可用的连接对象，假如创建失败，返回 null
                    try {
                        conn = newConnection();
                    } catch (SQLException e) {
                        System.out.println(" 创建数据库连接失败！ " + e.getMessage());
                        return null;
                    }
                    pConn.setConnection(conn);
                }
                // 已经找到一个可用的连接，退出
                break;
            }
        }
        // 返回找到到的可用连接
        return conn;
    }

    private boolean testConnection(Connection conn) {
        try {
            // 判定测试表是否存在
            if ("".equals(testTable)) {
                // 假如测试表为空，试着使用此连接的 setAutoCommit() 方法
                // 来判定连接否可用（此方法只在部分数据库可用，假如不可用 ,
                // 抛出异常）。注重：使用测试表的方法更可靠
                conn.setAutoCommit(true);
            } else { // 有测试表的时候使用测试表测试
                //check if this connection is valid
                Statement stmt = conn.createStatement();
                stmt.execute("select count(*) from " + testTable);
            }
        } catch (SQLException e) {
            // 上面抛出异常，此连接己不可用，关闭它，并返回 false;
            closeConnection(conn);
            return false;
        }
        // 连接可用，返回 true
        return true;
    }

    /**
     * <h2><span style='color:red;'>将连接返还到数据库连接池中</span></h2>
     *
     * @param conn 需要返还的连接
     */
    public void returnConnection(Connection conn) {
        // 确保连接池存在，假如连接没有创建（不存在），直接返回
        if (connections == null) {
            System.out.println(" 连接池不存在，无法返回此连接到连接池中 !");
            return;
        }
        PooledConnection pConn = null;
        Enumeration<PooledConnection> enumerate = connections.elements();
        // 遍历连接池中的所有连接，找到这个要返回的连接对象
        while (enumerate.hasMoreElements()) {
            pConn = (PooledConnection) enumerate.nextElement();
            // 先找到连接池中的要返回的连接对象
            if (conn == pConn.getConnection()) {
                // 找到了 , 设置此连接为空闲状态
                pConn.setBusy(false);
                System.out.println(" 已成功归还连接");
                break;
            }
        }
    }

    /**
     * <h2><span style='color:red;'>刷新数据库连接池</span></h2>
     *
     * @throws SQLException 抛出SQL错误
     */
    public synchronized void refreshConnections() throws SQLException {
        // 确保连接池己创新存在
        if (connections == null) {
            System.out.println(" 连接池不存在，无法刷新 !");
            return;
        }
        PooledConnection pConn = null;
        Enumeration<PooledConnection> enumerate = connections.elements();
        while (enumerate.hasMoreElements()) {
            // 获得一个连接对象
            pConn = (PooledConnection) enumerate.nextElement();
            // 假如对象忙则等 5 秒 ,5 秒后直接刷新
            if (pConn.isBusy()) {
                // 等 5 秒
                wait(5000);
            }
            // 关闭此连接，用一个新的连接代替它。
            closeConnection(pConn.getConnection());
            pConn.setConnection(newConnection());
            pConn.setBusy(false);
        }
    }

    /**
     * <h2><span style='color:red;'>关闭数据库连接池</span></h2>
     *
     * @throws SQLException 抛出SQL错误
     */
    public synchronized void closeConnectionPool() throws SQLException {
        // 确保连接池存在，假如不存在，返回
        if (connections == null) {
            System.out.println(" 连接池不存在，无法关闭 !");
            return;
        }
        PooledConnection pConn = null;
        Enumeration<PooledConnection> enumerate = connections.elements();
        while (enumerate.hasMoreElements()) {
            pConn = (PooledConnection) enumerate.nextElement();
            // 假如忙，等 5 秒
            if (pConn.isBusy()) {
                // 等 5 秒
                wait(5000);
            }
            //5 秒后直接关闭它
            closeConnection(pConn.getConnection());
            // 从连接池向量中删除它
            connections.removeElement(pConn);
        }
        // 置连接池为空
        connections = null;
    }

    private void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(" 关闭数据库连接出错： " + e.getMessage());
        }
    }

    private void wait(int mSeconds) {
        try {
            Thread.sleep(mSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class PooledConnection {
        // 数据库连接
        Connection connection = null;
        // 此连接是否正在使用的标志，默认没有正在使用
        boolean busy = false;
        // 构造函数，根据一个 Connection 构告一个 PooledConnection 对象
        public PooledConnection(Connection connection) {
            this.connection = connection;
        }

        // 返回此对象中的连接
        public Connection getConnection() {
            return connection;
        }

        // 设置此对象的，连接
        public void setConnection(Connection connection) {
            this.connection = connection;
        }

        // 获得对象连接是否忙
        public boolean isBusy() {
            return busy;
        }

        // 设置对象的连接正在忙
        public void setBusy(boolean busy) {
            this.busy = busy;
        }
    }

    /**
     * <h1><span style='color:red;'>数据库连接池创建示例</span></h1>
     */
    public static void main(String[] args) {
        ResourceBundle jdbc = ResourceBundle.getBundle("jdbc");
        String driverClass = jdbc.getString("jdbc.driver");
        String driverUrl = jdbc.getString("jdbc.url");
        String username = jdbc.getString("jdbc.username");
        String password = jdbc.getString("jdbc.password");

        ConnectionPool connPool = new ConnectionPool(driverClass, driverUrl, username, password);

        try {
            connPool.createPool();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            Connection conn = connPool.getConnection();
            System.out.println(conn);
        } catch (SQLException ex1) {
            ex1.printStackTrace();
        }
    }
}
