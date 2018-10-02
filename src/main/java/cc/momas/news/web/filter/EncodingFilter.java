package cc.momas.news.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.xml.ws.soap.AddressingFeature;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.charset.spi.CharsetProvider;

/**
 * 用来做字符编码的过滤器,保证所有请求的编码都正确为UTF-8
 */
@WebFilter(value = {"/*"})
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=utf-8");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
