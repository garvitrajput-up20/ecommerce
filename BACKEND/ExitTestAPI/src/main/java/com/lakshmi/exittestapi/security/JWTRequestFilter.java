package com.lakshmi.exittestapi.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.lakshmi.exittestapi.dao.UsersDAO;
import com.lakshmi.exittestapi.entities.UsersEntity;
import com.lakshmi.exittestapi.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Filter for decoding a JWT in the Authorization header and loading the user
 * object into the authentication context.
 */
@Component
public class JWTRequestFilter extends OncePerRequestFilter {

	/** The JWT Service. */
	private JwtService jwtService;
	/** The Local User DAO. */
	private UsersDAO localUserDAO;

	public JWTRequestFilter(JwtService jwtService, UsersDAO localUserDAO) {
		this.jwtService = jwtService;
		this.localUserDAO = localUserDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String tokenHeader = request.getHeader("Authorization");
		if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			String token = tokenHeader.substring(7);
			System.out.println("Testtttttttttttt");
			try {
				String username = jwtService.getUsername(token);
				Optional<UsersEntity> opUser = localUserDAO.findByUsernameIgnoreCase(username);
				if (opUser.isPresent()) {
					UsersEntity user = opUser.get();
					@SuppressWarnings("rawtypes")
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
							null, new ArrayList());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} catch (JWTDecodeException ex) {
			}
		}
		filterChain.doFilter(request, response);
	}
}