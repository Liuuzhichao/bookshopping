package cn.sdut.filter;

import java.io.IOException;
import java.net.URLDecoder;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import cn.sdut.dao.UsersMapper;
import cn.sdut.model.Users;
import cn.sdut.service.UsersService;
import cn.sdut.service.impl.UsersServiceImpl;
import cn.sdut.utils.CookieUtils;

/**
 * filter:过滤器
 * @author Administrator
 *每一个过滤器类都应该去实现一个filter接口
 *可以使用过滤器拦截所有的请求,对请求进行验证,如果请求合法那就放行,若不合法则拦截
 *	权限的控制，登录认证，日志
 */
public class AutoLoginFilter implements Filter{
	
	private UsersMapper userMapper;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// 验证代码,用于测试
		//System.out.println("拦截到用户请求");
		//向下转型，获取session需要用request
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		//获取用户的请求路径
		String uri = request.getRequestURI();
		
		//获取项目的根路径
		//request.getContextPath();
		
		
		//对于登录的请求,注册的请求不需要自动登录
		if(uri.indexOf("login")==-1 && uri.indexOf("Login")==-1 
				&&uri.indexOf("reg")==-1 && uri.indexOf("Reg")==-1) {
			
			//获取session
			HttpSession session = request.getSession();
			//获取session中登录的用户对象
			 Users users = (Users) session.getAttribute("user");
			//判断用户是否登录
			//如果user为null,说明没有登录
			if(users==null) {
				//自动登录
				//获取所有的cookie
				Cookie[] cookies = request.getCookies();
				//获取需要使用的cookie:autologin
				Cookie cookie = CookieUtils.getCookie(cookies, "autologin");
				//如果cookie不等于null,说明存在用户信息
				if(cookie != null) {
					//获取cookie中存放的用户信息
					String autologin = cookie.getValue();
					//判断cookie当中是否有用户信息,如果有
					if(autologin!=null && !autologin.equals("")) {
						//写的cookie格式:用户名-密码,以-为分隔符来获取用户名和密码
						String [] names = autologin.split("-");
						//将登陆信息封装到对象当中
						Users user = new Users();
						//以utf-8d形式解码
						user.setUsername(URLDecoder.decode(names[0], "utf-8"));
						user.setPassword(names[1]);
						//调用UsersMapper进行登录
						Users user2 = userMapper.login(user);
						//将登陆的对象存放到session当中
						session.setAttribute("user", user2);
					}
				}else {
					//如果请求的是与订单相关的请求,要求用户必须登录
					if(uri.indexOf("order")!=-1 || uri.indexOf("Order")!=-1) {
						//跳转到登录页面,采用重定向方式,因为不需要携带参数
						response.sendRedirect("showLogin?type=3");
						
						//请求转发
						//request.getRequestDispatcher("").forward(request, response);
					}
				}
			}
		}
		
		
		//对请求进行放行
		chain.doFilter(request, response);
	}
	
	/**
	 * 创建对象时会自动调用init（）方法对对象进行初始化
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		//获取到spring容器
		//ServletContext代表整个web应用,在整个web应用中有且只有一个ServletContext对象
		ServletContext context = config.getServletContext();
		//从ServletContext对象中获得spring容器对象
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(context);
		System.out.println(ac);
		userMapper = ac.getBean(UsersMapper.class);
	}
	
	/**
	 * 在对象被销毁时调用
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

}
