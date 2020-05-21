package com.learning.filter;

import javax.servlet.*;
import java.io.IOException;

public class FilterA implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        throw new RuntimeException("If this exception is shown, filter works");
    }
}
