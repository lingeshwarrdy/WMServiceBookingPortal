package com.edubridge.ServiceBookingPortal.configs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.FilterDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter{
	
	@Value("${app.client.url}")
	private String clientAppUrl="";
	
	public SimpleCorsFilter() {
		
	}
	
	public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain) throws java.io.IOException, ServletException{
		HttpServletResponse response =(HttpServletResponse) res;
		HttpServletRequest request =(HttpServletRequest) req;
		Map<String,String>map =new HashMap<>();
		String originHeader =request.getHeader("origin");
		response.setHeader("Access-Contril-Allow-Origin", originHeader);
		response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
	    response.setHeader("Access-Control-Max-Age","3600");
	    response.setHeader("Access-Control-Allow-Headers", "*");
	    
	    if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	    	response.setStatus(HttpServletResponse.SC_OK);
	    }else {
	    	chain.doFilter(req, res);
	    }
	
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilterDefinition getFilterDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filter setParameter(String name, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filter setParameterList(String name, Collection<?> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filter setParameterList(String name, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validate() throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAutoEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
