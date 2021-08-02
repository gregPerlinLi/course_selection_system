package com.gregperlinli.dao;


import com.gregperlinli.pojo.College;

import java.sql.Connection;
import java.util.List;

/**
 * 用于对数据库中的college表进行增删改查处理
 *
 * @author gregperlinli
 * @since 2021-7-21
 */
public interface CollegeDao {

    /**
     * 插入新的学院信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param college 提供需要增加的学院信息对象（不需要写id）
     * @throws Exception 抛出错误
     *
     */
    void insert(Connection conn, College college) throws Exception;

    /**
     * 根据id来删除学院信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param id 提供要删除的学院对象的id值
     * @throws Exception 抛出错误
     */
    void deleteById(Connection conn, int id) throws Exception;

    /**
     * 根据id来更新学院信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param college 提供修改好的的学院信息对象（不需要写id）
     * @throws Exception 抛出错误
     */
    void updateById(Connection conn, College college) throws Exception;

    /**
     * 根据学院名字来获取学院对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @param collegeName 提供所要获取学院的名字
     * @return 查询到的学院对象，若无结果则返回<code>null</code>
     */
    College getCollegeByCollegeName(Connection conn, String collegeName);

    /**
     * 获取所有学院对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @return 查询到的学院对象集合，若无结果则返回<code>null</code>
     */
    List<College> getAll(Connection conn);

    /**
     * 获取学院个数
     *
     * @param conn 提供数据库连接池所给的连接
     * @return 返回学院个数（以<code>long</code>为数据类型）
     */
    Long getCount(Connection conn);
}

