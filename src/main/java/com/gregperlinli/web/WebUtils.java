package com.gregperlinli.web;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * 用于Web的一系列工具
 *
 * @author gregPerlinLi
 * @since 2021-08-09
 */
public class WebUtils {

    /**
     * 将Map键值对注入到对应的JavaBean对象属性中
     *
     * @param value 需要转换的Map键值对
     * @param bean 转换的目标JavaBean对象
     * @param <T> 泛型，可放入任意一个类型的JavaBean对象
     * @return 注入后的JavaBean对象
     */
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
