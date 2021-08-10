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
    final private AccountManageService accountManageService = new AccountManageServiceImpl();

    /**
     * 测试<code>studentRegist()</code>方法
     */
    @Test
    public void studentRegist() {
        final Student student = new Student("3120007214",
                                            "李翰霆",
                                            "123456",
                                            "物理与光电工程学院",
                                            "20级",
                                            "20电子科学与技术3班");
        boolean isRegisted = accountManageService.studentRegist(student);
        System.out.println(isRegisted);
    }

    /**
     * 测试<code>adminRegist()</code>方法
     */
    @Test
    public void adminRegist() {
        final Admin admin = new Admin("test", "test123");
        boolean isRegisted = accountManageService.adminRegist(admin);
        System.out.println(isRegisted);
    }

    /**
     * 测试<code>studentUpdate()</code>方法
     */
    @Test
    public void studentUpdate() {
        final Student student = new Student(3,
                "3121007213",
                "黎伊朗",
                "LargeKindergarten",
                "物理与光电工程学院",
                "21级",
                "21电子科学与技术3班");
        boolean isUpdated = accountManageService.studentUpdate(student);
        System.out.println(isUpdated);
    }


    /**
     * 测试<code>adminUpdate()</code>方法
     */
    @Test
    public void adminUpdate() {
        final Admin admin = new Admin(6, "anotherTest", "test_for_other");
        boolean isUpdated = accountManageService.adminUpdate(admin);
        System.out.println(isUpdated);
    }


    /**
     * 测试<code>studentDelete()</code>方法
     */
    @Test
    public void studentDelete() {
        boolean isDeleted = accountManageService.studentDelete(7);
        System.out.println(isDeleted);
    }


    /**
     * 测试<code>adminDelete()</code>方法
     */
    @Test
    public void adminDelete() {
        boolean isDeleted = accountManageService.adminDelete(7);
        System.out.println(isDeleted);
    }
}
