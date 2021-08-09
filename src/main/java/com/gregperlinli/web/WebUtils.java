package com.gregperlinli.web;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于Web的一系列工具
 *
 * @author gregPerlinLi
 * @since 2021-08-09
 */
public class WebUtils {

    /**
     * 将POST请求过来的表单自动转换为Bean对象
     *
     * @param request 请求，其中需要包含要转换的表单
     * @param bean 转换的目标Bean对象
     */
    public static void copyParamToBean(HttpServletRequest request, Object bean) {
        try {
            BeanUtils.populate(bean, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
