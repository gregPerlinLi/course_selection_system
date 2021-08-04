package com.gregperlinli.dao;

import com.gregperlinli.pojo.Grade;

import java.sql.Connection;
import java.util.List;

/**
 * 用于对数据库中的grade表进行增删改查处理
 *
 * @author gregperlinli
 * @since 2021-7-21
 */
public interface GradeDao {

    /**
     * 插入新的年级信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param grade 提供需要增加的年级信息对象（不需要写id）
     * @throws Exception 抛出错误
     *
     */
    void insert(Connection conn, Grade grade) throws Exception;

    /**
     * 根据id来删除年级信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param id 提供要删除的年级对象的id值
     * @throws Exception 抛出错误
     */
    void deleteById(Connection conn, int id) throws Exception;

    /**
     * 根据id来更新年级信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param grade 提供修改好的的年级信息对象（不需要写id）
     * @throws Exception 抛出错误
     */
    void updateById(Connection conn, Grade grade) throws Exception;

    /**
     * 根据年级id来获取年级对象
     * @param conn 提供数据库连接池所给的连接
     * @param id 提供所要获取年级的id
     * @return 查询到的年级对象，若无结果则返回<code>null</code>
     */
    Grade getGradeById(Connection conn, int id);

    /**
     * 根据年级名字来获取年级对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @param gradeName 提供所要获取年级的名字
     * @return 查询到的年级对象，若无结果则返回<code>null</code>
     */
    Grade getGradeByGradeName(Connection conn, String gradeName);

    /**
     * 获取所有年级对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @return 查询到的年级对象集合，若无结果则返回<code>null</code>
     */
    List<Grade> getAll(Connection conn);

    /**
     * 获取年级个数
     *
     * @param conn 提供数据库连接池所给的连接
     * @return 返回年级个数（以<code>long</code>为数据类型）
     */
    Long getCount(Connection conn);
}
