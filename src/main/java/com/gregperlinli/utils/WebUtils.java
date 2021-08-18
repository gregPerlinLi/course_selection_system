package com.gregperlinli.utils;

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

    /**
     * 将<code>String</code>类型的整数变量转换为<code>int</code>类型的变量
     *
     * @param strInt 需要转换的<code>String</code>类型整数变量
     * @param defaultValue 如果转换失败时返回的默认数值
     * @return 转换后的<code>int</code>类型的变量
     */
    public static int parseInt(String strInt, int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
