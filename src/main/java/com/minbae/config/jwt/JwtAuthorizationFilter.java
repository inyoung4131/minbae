package com.minbae.config.jwt;

import com.minbae.config.auth.PrincipalDetails;
import com.minbae.sso.jwt.JwtTokenProvider;
import com.minbae.user.entity.User;
import com.minbae.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
		String userEmail="";
		if (result) {
			jwtTokenProvider.getUserId(token);
		}

		if(userEmail!=""&&userEmail!=null) {
			User user = userRepository.findByUserEmail(userEmail);
			
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
