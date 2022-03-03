package com.danaherdigital.che_hx.asset_management;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;



@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {



    private static final Logger LOGGER = LogManager.getLogger(SimpleCorsFilter.class);



public SimpleCorsFilter() {
    LOGGER.info("SimpleCORSFilter init");
}



@Override
public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {



    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;


	  if(!Objects.isNull(request.getHeader("Origin"))) {
	  ESAPI.httpUtilities().setHeader(response,"Access-Control-Allow-Origin",
	  request.getHeader("Origin")); }

    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "AUTH-TOKEN, Authorization, Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");



    if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
        response.setStatus(HttpServletResponse.SC_OK);
    } else {
        chain.doFilter(req, res);
    }
}



@Override
public void init(FilterConfig filterConfig) {
	// Do nothing
}




@Override
public void destroy() {
	// Do nothing
}



}