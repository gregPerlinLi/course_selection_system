package com.gregperlinli.test.service;

import com.gregperlinli.pojo.Admin;
import com.gregperlinli.pojo.Student;
import com.gregperlinli.service.AccountManageService;
import com.gregperlinli.service.impl.AccountManageServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * 对<code>AccountManageService</code>进行单元测试
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
        final Student student = new Student("3120007214",
                                            "李翰霆",
                                            "123456",
                                            "物理与光电工程学院",
                                            "20级",
                                            "20电子科学与技术3班");
        final AccountManageService ACCOUNT_MANAGE_SERVER = new AccountManageServiceImpl();
        boolean isRegisted = ACCOUNT_MANAGE_SERVER.studentRegist(student);
        System.out.println(isRegisted);
    }

    /**
     * 测试<code>adminRegist()</code>方法
     */
    @Test
    public void testAdminRegist() {
        final Admin admin = new Admin("test", "test123");
        final AccountManageService ACCOUNT_MANAGE_SERVER = new AccountManageServiceImpl();
        boolean isRegisted = ACCOUNT_MANAGE_SERVER.adminRegist(admin);
        System.out.println(isRegisted);
    }

    /**
     * 测试<code>studentUpdate()</code>方法
     */
    @Test
    public void testStudentUpdate() {
        final Student student = new Student(3,
                "3121007213",
                "黎伊朗",
                "LargeKindergarten",
                "物理与光电工程学院",
                "21级",
                "21电子科学与技术3班");
        final AccountManageService ACCOUNT_MANAGE_SERVER = new AccountManageServiceImpl();
        boolean isUpdated = ACCOUNT_MANAGE_SERVER.studentUpdate(student);
        System.out.println(isUpdated);
    }


    /**
     * 测试<code>adminUpdate()</code>方法
     */
    @Test
    public void testAdminUpdate() {
        final Admin admin = new Admin(6, "anotherTest", "test_for_other");
        final AccountManageService ACCOUNT_MANAGE_SERVER = new AccountManageServiceImpl();
        boolean isUpdated = ACCOUNT_MANAGE_SERVER.adminUpdate(admin);
        System.out.println(isUpdated);
    }


    /**
     * 测试<code>studentDelete()</code>方法
     */
    @Test
    public void testStudentDelete() {
        final AccountManageService accountManageService = new AccountManageServiceImpl();
        boolean isDeleted = accountManageService.studentDelete(7);
        System.out.println(isDeleted);
    }


    /**
     * 测试<code>adminDelete()</code>方法
     */
    @Test
    public void testAdminDelete() {
        final AccountManageService accountManageService = new AccountManageServiceImpl();
        boolean isDeleted = accountManageService.adminDelete(7);
        System.out.println(isDeleted);
    }
}
