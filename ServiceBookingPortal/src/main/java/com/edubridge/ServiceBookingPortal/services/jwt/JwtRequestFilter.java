/*package com.edubridge.ServiceBookingPortal.services.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.edubridge.ServiceBookingPortal.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
		
		String authentHeader =request.getHeader("Authorization");
		
		String token =null;
		String username=null;
		
		if(authentHeader !=null && authentHeader.startsWith("Bearer")) {
			
			token =authentHeader.substring(7);
			
			username =jwtUtil.extractUserName(token);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			if(jwtUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authentToken=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				authentToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentToken);
			}
			
		}
		
		filterChain.doFilter(request, response);
		
		
		
	}

}
*/