package cc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**1.如果是JSP文件，则过滤掉异常登录
 * 2.可以指定特定的JSP文件，不用经过过滤（如Login.jsp,register.jsp）
 * 3.如果是login.jsp，则检查用户是否已经登录，是则直接传入用户页面，不必重新登录
 * servlet filter implementation class LoginFilter
 */
@WebFilter("/*")
public class A_LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public A_LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String[] excludeJSP = { "login.jsp","loginOut.jsp","register.jsp" ,"loginError.jsp","forgetPassword_Login.jsp",
				                "changePasswordError.jsp","forgetPassword_LoginError.jsp","loginError2.jsp","adminLogin.jsp"};
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String requestURI = httpServletRequest.getRequestURI();
		if(requestURI.endsWith(".jsp") || requestURI.endsWith("userAdmin") || requestURI.endsWith("chat")
				|| requestURI.endsWith("othersHome") || requestURI.endsWith("user_Adminchat")){
			boolean isExcluded = false;
			for (String url : excludeJSP) {
				if (requestURI.endsWith(url)) {
					isExcluded = true;
					break;
				}
			}
			if (!isExcluded) {
				String name =  (String) httpServletRequest.getSession().getAttribute("login");
				if (name == null) {
					((HttpServletResponse) response).sendRedirect("login.jsp");
					return;
				}
			}
			if (requestURI.endsWith("login.jsp")) {
				String name =  (String) httpServletRequest.getSession().getAttribute("login");
				if (name != null) {
					((HttpServletResponse) response).sendRedirect("home.jsp");
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
