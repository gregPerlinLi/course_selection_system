package com.gregperlinli.dao;

import com.gregperlinli.pojo.Classes;

import java.sql.Connection;
import java.util.List;

/**
 * 用于对数据库中的classes表进行增删改查处理
 *
 * @author gregperlinli
 */
public interface ClassesDao {

    /**
     * 插入新的班级信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param classes 提供需要增加的班级信息对象（不需要写id）
     * @throws Exception 抛出错误
     *
     */
    void insert(Connection conn, Classes classes) throws Exception;

    /**
     * 根据id来删除班级信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param id 提供要删除的班级对象的id值
     * @throws Exception 抛出错误
     */
    void deleteById(Connection conn, int id) throws Exception;

    /**
     * 根据id来更新班级信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param classes 提供修改好的的班级信息对象（不需要写id）
     * @throws Exception 抛出错误
     */
    void updateById(Connection conn, Classes classes) throws Exception;
    
    /**
     * 根据班级名字来获取班级对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @param classesName 提供所要获取班级的名字
     * @return 查询到的班级对象，若无结果则返回<code>null</code>
     */
    Classes getClassByClassName(Connection conn, String classesName);

    /**
     * 根据学院来获取班级对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @param college 提供所要获取班级的所在学院
     * @return 查询到的班级对象集合，若无结果则返回<code>null</code>
     */
    List<Classes> getClassesByCollege(Connection conn, String college);

    /**
     * 根据年级来获取班级对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @param grade 提供所要获取班级的所在年级
     * @return 查询到的班级对象集合，若无结果则返回<code>null</code>
     */
    List<Classes> getClassesByGrade(Connection conn, String grade);

    /**
     * 获取所有班级对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @return 查询到的班级对象集合，若无结果则返回<code>null</code>
     */
    List<Classes> getAll(Connection conn);

    /**
     * 获取班级个数
     *
     * @param conn 提供数据库连接池所给的连接
     * @return 返回班级个数（以<code>long</code>为数据类型）
     */
    Long getCount(Connection conn);
}
