package com.gregperlinli.dao;

import com.gregperlinli.pojo.Admin;

import java.sql.Connection;
import java.util.List;

/**
 * 用于对数据库中的admin表进行增删改查处理
 *
 * @author gregperlinli
 */
public interface AdminDao {

    /**
     * 插入新的管理员信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param admin 提供需要增加的管理员信息对象（不需要写id）
     * @throws Exception 抛出错误
     *
     */
    public void insert(Connection conn, Admin admin) throws Exception;

    /**
     * 根据id来删除管理员信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param id 提供要删除的管理员对象的id值
     * @throws Exception 抛出错误
     */
    public void deleteById(Connection conn, int id) throws Exception;

    /**
     * 根据id来更新管理员信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param admin 提供修改好的的管理员信息对象（不需要写id）
     * @throws Exception 抛出错误
     */
    public void updateById(Connection conn, Admin admin) throws Exception;

    /**
     * 根据管理员名字来获取管理员对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @param userName 提供所要获取管理员的名字
     * @return 查询到的管理员对象，若无结果则返回<code>null</code>
     */
    public Admin getAdmByUsername(Connection conn, String username);

    /**
     * 获取所有管理员对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @return 查询到的管理员对象集合，若无结果则返回<code>null</code>
     */
    public List<Admin> getAll(Connection conn);

    /**
     * 获取管理员个数
     *
     * @param conn 提供数据库连接池所给的连接
     * @return 返回管理员个数（以<code>long</code>为数据类型）
     */
    public Long getCount(Connection conn);
}
