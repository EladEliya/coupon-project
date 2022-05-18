package app.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import app.core.jwt.util.JwtUtil;
import app.core.jwt.util.JwtUtil.ClientDetails;

public class LoginFilter implements Filter {

	private JwtUtil jwtUtil;

	public LoginFilter(JwtUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Headers", "*");
		resp.addHeader("Access-Control-Allow-Methods", "*");
		
		String token = req.getHeader("token");
//		String uri = req.getRequestURI();
		System.out.println("===== LOGIN FILTER TOKEN: " + token);

		if (token == null && req.getMethod().equals("OPTIONS")) {
			System.out.println("this is preflight request: " + req.getMethod());
			chain.doFilter(request, response); // go ahead with preflight
			return;
		}
		String uri = req.getRequestURI();
		String tokenType = jwtUtil.extractClient(token).getClientType().toString();
		try {
			if (uri.contains("/api/" + tokenType)) {

				ClientDetails clientDetails = jwtUtil.extractClient(token);
				System.out.println("===== LOGIN FILTER: " + clientDetails);
				chain.doFilter(request, response); // go to resource
				return;
			} else {
				resp.sendError(HttpStatus.UNAUTHORIZED.value(), "You are without proper permissions");
			}
		} catch (Exception ex) {
			// if we are here token is expired
			ex.printStackTrace();
			// send an error response to caller
			resp.setHeader("Access-Control-Allow-Origin", "*");
			resp.sendError(HttpStatus.UNAUTHORIZED.value(), "not logged in");
//			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "not logged in");
		}

	}

}
//resp.addHeader("Access-Control-Allow-Origin", "*");
//resp.addHeader("Access-Control-Allow-Headers", "*");
//resp.addHeader("Access-Control-Allow-Methods", "*");

//System.out.println(tokenType);
//System.out.println(uri.equals("/api/"+tokenType+"/*"));	
//System.out.println(uri.contains("/api/"+tokenType));	
//System.out.println(uri.contains(tokenType));	
//System.out.println("************************" + uri + "**********************");
