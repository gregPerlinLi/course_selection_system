package com.gregperlinli.test.utils;

import com.gregperlinli.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionTest {

    @Test
    public void testConnection() throws Exception {
        Connection connection = JDBCUtils.getConnectionWithPool();
        System.out.println(connection);

        JDBCUtils.closeResource(connection, null);
    }
}
