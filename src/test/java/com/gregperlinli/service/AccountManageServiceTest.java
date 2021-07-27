package com.gregperlinli.service;

import com.gregperlinli.pojo.Admin;
import com.gregperlinli.pojo.Student;
import com.gregperlinli.service.impl.AccountManageServerImpl;
import org.junit.jupiter.api.Test;

/**
 * 对<code>AccountManageServer</code>进行单元测试
 *
 * @author gregperlinli
 */
public class AccountManageServiceTest {
    /**
     * 测试<code>studentRegist()</code>方法
     */
    @Test
    public void testStudentRegist() {
        final Student STUDENT = new Student("3120007214",
                                            "李翰霆",
                                            "123456",
                                            "物理与光电工程学院",
                                            "20级",
                                            "20电子科学与技术3班");
        final AccountManageServer REGIST_SERVER = new AccountManageServerImpl();
        boolean isRegisted = REGIST_SERVER.studentRegist(STUDENT);
        System.out.println(isRegisted);
    }

    /**
     * 测试<code>adminRegist()</code>方法
     */
    @Test
    public void testAdminRegist() {
        final Admin ADMIN = new Admin("test", "test123");
        final AccountManageServer REGIST_SERVER = new AccountManageServerImpl();
        boolean isRegisted = REGIST_SERVER.adminRegist(ADMIN);
        System.out.println(isRegisted);
    }
}
