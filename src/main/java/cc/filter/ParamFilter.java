package cc.filter;

import cc.model.Vip;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 防止聊天页面用户通过修改URL内参数  以进行跳转
 * servlet filter implementation class ParamFilter
 */
@WebFilter("/chat")
public class ParamFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ParamFilter() {
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
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		int userId = (int) httpServletRequest.getSession().getAttribute("userId");
		Vip hisVip = (Vip) httpServletRequest.getSession().getAttribute("hisVip");
		if(hisVip!=null){
			String hisusername = hisVip.getUsername();
			int hisuserId = hisVip.getUserId();
			int inmyUserId = Integer.valueOf(httpServletRequest.getParameter("myuserId"));
			int inhisUserId = Integer.valueOf(httpServletRequest.getParameter("hisuserId"));
			String inusername = httpServletRequest.getParameter("hisusername");
			if(userId!=inmyUserId || hisuserId!=inhisUserId || !hisusername.equals(inusername)){
				httpServletResponse.sendError(404,"请勿随意修改URL地址！");
				return;
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
