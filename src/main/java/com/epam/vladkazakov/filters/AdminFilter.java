package com.epam.vladkazakov.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminFilter implements Filter {

    public AdminFilter() {

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String role = (String) session.getAttribute("role");
		System.out.println("in filter " + role);
		if (role == null || !role.equals("admin")) {
			System.out.println("redirect");
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendError(404);

		} else {
			chain.doFilter(request, response);
		}


	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
