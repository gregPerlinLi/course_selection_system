package com.gregperlinli.dao;

import com.gregperlinli.utils.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * <h1><b><span style='color:red;'>DAO: Data(base) Access Object</span></b></h1>
 * <h2>Packaged generic operation of data</h2>
 *
 * @author gregperlinli
 * @since 2021-5-20
 * @version 2.1.0
 */
public abstract class BaseDAO<T> {

    private Class<T> clazz = null;

    /**
     * <h2><span style='color:red;'>Gets the generic type in the superclass inherited by the subclass of the current BaseDAO</span></h2>
     *
     * @author gregperlinli
     */
    public BaseDAO() {
        // TODO: Attention: "this" is mention to subclass object (such as User, Book) not BaseDAO object!!!
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;

        // get the generic parameters of the superclass
        Type[] typeArguments = paramType.getActualTypeArguments();

        // get the first parameter of the generic paradigm
        clazz = (Class<T>) typeArguments[0];
    }

    /**
     * <h2><span style='color:red;'>Generic add, delete and update operation (version2.0) (Consider database transaction)</span></h2>
     * <h3>
     * <b>How to output:</b><br/>
     * <code>BaseDAO.update(conn, sql, ...args);</code>
     * </h3>
     *
     * @author gregperlinli
     * @param conn connection of database
     * @param sql sql format
     * @param args fill the placeholder(variable)
     */
    public int update(Connection conn, String sql, Object ...args) throws Exception {  // the length of variable parameters should be equal to the number of placeholders

        PreparedStatement ps = null;
        try {
            // precompiled Statement and return ps instance
            ps = conn.prepareStatement(sql);

            // fill placeholder
            for ( int i = 0; i < args.length; i++ ) {
                // Be careful to the parameter declaration exception
                ps.setObject(i+1, args[i]);
            }

            // perform operation
            return ps.executeUpdate();

        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            // close the resource
            JDBCUtils.closeResource(null, ps);
        }

        return 0;
    }


    /**
     * <h2><span style='color:red;'>Generic query with one object</span><br/>
     * using PreparedStatement to realize the query operation by different form (version 2.0, consider to transaction)</h2>
     *
     * <h3>
     * How to output:<br/>
     * <code>ClassName className = getQuery(conn, ClassName.class, sql, ...args);</code>
     * </h3>
     *
     * @param conn connection of database
     * @param sql sql format
     * @param args fill the placeholder(variable)
     * @return Class or null
     * @author gregperlinli
     *
     */
    public T getQuery(Connection conn, String sql, Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();

            // get the metadata of the result set：ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // get the column of the result set through metadata
            int columnCount = rsmd.getColumnCount();

            if (rs.next()) {
                // T t = clazz.newInstance();
                T t = clazz.getDeclaredConstructor().newInstance();

                // handle each column in one row of the result set
                for (int i = 0; i < columnCount; i++) {
                    // get the value of the column
                    Object columnValue = rs.getObject(i + 1);

                    // get name of each row
                    String columnName = rsmd.getColumnLabel(i + 1);

                    // TODO: give one corresponding "columnName" of the Object "t" assignment to "columnValue" through reflex
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

    /**
     * <h2><span style='color:red;'>Generic query with multiple object</span><br/>
     * using PreparedStatement to realize multi query operation by different form (version 2.0, consider to transaction)</h2>
     *
     * <h3>
     * <b>How to output:</b><br/>
     * <code>List<ClassName> listName = getMultiQuery(Classname.class, sql, ...args);</code>
     *<br/><br/>
     * <b>How to print out:</b><br/>
     * <code>listName.forEach(System.out::println);</code>
     * </h3>
     *
     * @author gregperlinli
     * @param conn connection of database
     * @param sql sql format
     * @param args fill the placeholder(variable)
     * @return List or null
     *
     */
    public List<T> getMultiQuery(Connection conn, String sql, Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();

            // get the metadata of the result set：ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // get the column of the result set through metadata
            int columnCount = rsmd.getColumnCount();

            // create an aggregate object
            ArrayList<T> list = new ArrayList<T>();

            while (rs.next()) {
                // T t = clazz.newInstance();
                T t = clazz.getDeclaredConstructor().newInstance();

                // handle each column in one row of the result set and assignment the value to object "t"
                for (int i = 0; i < columnCount; i++) {
                    // get the value of the column
                    Object columnValue = rs.getObject(i + 1);

                    // get name of each row
                    String columnName = rsmd.getColumnLabel(i + 1);

                    // TODO: give one corresponding "columnName" of the Object "t" assignment to "columnValue" through reflex
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }

            return list;
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

    /**
     * <h2><span style='color:red;'>A generic way to query the special value</span></h2>
     *
     * <h3>
     * <b> How to use: </b><br/>
     * <code>Object objectName = getValue(conn, sql, ...args);</code>
     * </h3>
     *
     * @author gragperlinli
     * @param conn connection of database
     * @param sql sql format
     * @param args fill the placeholder
     * @param <E> Paradigms
     * @return Paradigms
     *
     */
    public <E> E getValue(Connection conn, String sql, Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++ ) {
                ps.setObject(i + 1, args[i]);

            }

            rs = ps.executeQuery();
            if ( rs.next() ) {
                return (E) rs.getObject(1);
            }


        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }
}
