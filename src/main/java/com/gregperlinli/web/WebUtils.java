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
     * @param bean 转换的目标Bean对象
     */
    public static void copyParamToBean(Map value, Object bean) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
