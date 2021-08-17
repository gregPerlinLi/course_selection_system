package com.gregperlinli.filter;

import com.gregperlinli.utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 用于进行事务管理，保证其线程安全
 *
 * @author gregPerlinLi
 * @see javax.servlet.Filter
 * @since 2021-08-17
 */
@WebFilter(filterName = "TransactionFilter", value = "/*")
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        try {
            chain.doFilter(request, response);
            // 提交事务
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            // 回滚事务
            JDBCUtils.rollBackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
