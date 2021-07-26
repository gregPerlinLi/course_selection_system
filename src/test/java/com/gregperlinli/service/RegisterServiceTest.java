package com.gregperlinli.service;

import com.gregperlinli.pojo.Admin;
import com.gregperlinli.pojo.Student;
import com.gregperlinli.service.impl.RegistServerImpl;
import org.junit.jupiter.api.Test;

/**
 * 对<code>RegistServer</code>进行单元测试
 *
 * @author gregperlinli
 */
public class RegisterServiceTest {
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
        final RegistServer REGIST_SERVER = new RegistServerImpl();
        boolean isRegisted = REGIST_SERVER.studentRegist(STUDENT);
        System.out.println(isRegisted);
    }

    /**
     * 测试<code>adminRegist()</code>方法
     */
    @Test
    public void testAdminRegist() {
        final Admin ADMIN = new Admin("test1", "test123");
        final RegistServer REGIST_SERVER = new RegistServerImpl();
        boolean isRegisted = REGIST_SERVER.adminRegist(ADMIN);
        System.out.println(isRegisted);
    }
}
