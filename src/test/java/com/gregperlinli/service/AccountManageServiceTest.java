package com.gregperlinli.service;

import com.gregperlinli.pojo.Admin;
import com.gregperlinli.pojo.Student;
import com.gregperlinli.service.impl.AccountManageServerImpl;
import org.junit.jupiter.api.Test;

/**
 * 对<code>AccountManageServer</code>进行单元测试
 *
 * @author gregperlinli
 * @since 2021-7-26
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
        final AccountManageServer ACCOUNT_MANAGE_SERVER = new AccountManageServerImpl();
        boolean isRegisted = ACCOUNT_MANAGE_SERVER.studentRegist(STUDENT);
        System.out.println(isRegisted);
    }

    /**
     * 测试<code>adminRegist()</code>方法
     */
    @Test
    public void testAdminRegist() {
        final Admin ADMIN = new Admin("test", "test123");
        final AccountManageServer ACCOUNT_MANAGE_SERVER = new AccountManageServerImpl();
        boolean isRegisted = ACCOUNT_MANAGE_SERVER.adminRegist(ADMIN);
        System.out.println(isRegisted);
    }

    /**
     * 测试<code>studentUpdate()</code>方法
     */
    @Test
    public void testStudentUpdate() {
        final Student STUDENT = new Student(3,
                "3121007213",
                "黎伊朗",
                "LargeKindergarten",
                "物理与光电工程学院",
                "21级",
                "21电子科学与技术3班");
        final AccountManageServer ACCOUNT_MANAGE_SERVER = new AccountManageServerImpl();
        boolean isUpdated = ACCOUNT_MANAGE_SERVER.studentUpdate(STUDENT);
        System.out.println(isUpdated);
    }


    /**
     * 测试<code>adminUpdate()</code>方法
     */
    @Test
    public void testAdminUpdate() {
        final Admin ADMIN = new Admin(6, "anotherTest", "test_for_other");
        final AccountManageServer ACCOUNT_MANAGE_SERVER = new AccountManageServerImpl();
        boolean isUpdated = ACCOUNT_MANAGE_SERVER.adminUpdate(ADMIN);
        System.out.println(isUpdated);
    }


    /**
     * 测试<code>studentDelete()</code>方法
     */
    @Test
    public void testStudentDelete() {
        final AccountManageServer ACCOUNT_MANAGE_SERVER = new AccountManageServerImpl();
        boolean isDeleted = ACCOUNT_MANAGE_SERVER.studentDelete(7);
        System.out.println(isDeleted);
    }


    /**
     * 测试<code>adminDelete()</code>方法
     */
    @Test
    public void testAdminDelete() {
        final AccountManageServer ACCOUNT_MANAGE_SERVER = new AccountManageServerImpl();
        boolean isDeleted = ACCOUNT_MANAGE_SERVER.adminDelete(7);
        System.out.println(isDeleted);
    }
}
