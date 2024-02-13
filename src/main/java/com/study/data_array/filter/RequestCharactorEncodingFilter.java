package com.study.data_array.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/*")
public class RequestCharactorEncodingFilter extends HttpFilter implements Filter {

    public RequestCharactorEncodingFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
				HttpServletResponse httpServletResponse = (HttpServletResponse) response;
				httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");
				httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");//응답을 하기 전에 인코딩해서 보내야한다. - 전처리 영역에서 처리
				//필터 전처리 영역
				chain.doFilter(request, response);//요청주소의 선언된 메서드와 연결 - 이 경우 post
				//필터 후처리 영역
				

	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
