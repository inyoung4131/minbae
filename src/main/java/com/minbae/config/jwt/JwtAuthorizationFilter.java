package com.minbae.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minbae.config.auth.PrincipalDetails;
import com.minbae.sso.jwt.JwtTokenProvider;
import com.minbae.user.entity.User;
import com.minbae.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter{
	
	private UserRepository userRepository;


	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(request);
		String token = jwtTokenProvider.getToken();
		response.setHeader("user-token",token);
		boolean result = jwtTokenProvider.validateExpToken(token);
		boolean result2 = false;
		if (result) {
			result2 = jwtTokenProvider.validateExpTokenForRole(token, "owner");
		}
		if (!result2) {
			response.sendRedirect("/error403");
			return;
		}

		if(result2) {
			User user = userRepository.findByUsername(username);
			
			PrincipalDetails principalDetails = new PrincipalDetails(user);
			Authentication authentication =
					new UsernamePasswordAuthenticationToken(
							principalDetails,
							null,
							principalDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	
		chain.doFilter(request, response);
	}

	//어떤 사용자인지 검증하는 메소드 필요

	//소셜로그인인지 검증하는 메소드 필요

}
