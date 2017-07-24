package cc.filter;

import cc.util.Code;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**将输入的文本中的非法字段过滤，并进行更改替换（html语句，反斜杠等）
 * servlet filter implementation class CodeFilter
 */
@WebFilter("/*")
public class CodeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CodeFilter() {
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
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String searchKey = req.getParameter("searchKey");
		String chatmsg = req.getParameter("chatmsg");
		if(searchKey!=null){
			searchKey = Code.htmlEncode(searchKey);
			req.setAttribute("searchKey", searchKey);
		}
		if(chatmsg!=null){
			chatmsg = Code.htmlEncode(chatmsg);
			req.setAttribute("chatmsg", chatmsg);
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
