package com.jlp.filter;

import com.jlp.service.PrisonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.jlp.utils.IPUtils.getRemoteIP;

@WebFilter(urlPatterns = "/*", filterName = "IPFilter")
public class IPFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(IPFilter.class);

    @Resource
    PrisonService prisonService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String remoteIP = getRemoteIP((HttpServletRequest) servletRequest);
        logger.info(remoteIP + "=======进入过滤器===========");
        if (prisonService.selectByIP(remoteIP) != null)
            servletRequest.getRequestDispatcher("/IPerror").forward(servletRequest, servletResponse);
        else
            filterChain.doFilter(servletRequest, servletResponse);
    }


}