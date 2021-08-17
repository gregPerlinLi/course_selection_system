package com.gregperlinli.test.utils;

import com.gregperlinli.pojo.SelectedCourse;
import com.gregperlinli.utils.FastSearchListUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gregPerlinLi
 * @since 2021-08-17
 */
class FastSearchListUtilTest {

    @Test
    void searchTasks() {
        List<SelectedCourse> selectedCourses = new ArrayList<>();
        selectedCourses.add(new SelectedCourse(1, "3000000000", "小明", "高等数学"));
        selectedCourses.add(new SelectedCourse(2, "3000000000", "小明", "大学英语"));
        selectedCourses.add(new SelectedCourse(3, "3000000000", "小明", "电路"));
        FastSearchListUtil fslu = null;
        try {
            fslu = new FastSearchListUtil(selectedCourses, "course");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Object> list = fslu.searchTasks("高等数学");
        System.out.println(list);
        if ( list.size() > 0 ) {
            System.out.println("已存在");
        } else {
            System.out.println("不存在");
        }
    }
}