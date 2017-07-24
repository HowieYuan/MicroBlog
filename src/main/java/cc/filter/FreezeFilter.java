package cc.filter;

import cc.dao.UserDao;
import cc.service.LoginService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet filter implementation class FreezeFilter
 */
@WebFilter(urlPatterns={"/homePage.jsp","/home.jsp"})
public class FreezeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public FreezeFilter() {
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
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		LoginService logSer = new LoginService();
		int userId = (int) ((HttpServletRequest) request).getSession().getAttribute("userId");
		if(logSer.checkFreezeAndFilter(req,res)){
			new UserDao().beingOutLogin(userId);
			((HttpServletResponse) response).sendRedirect("loginFreeze.jsp");
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
