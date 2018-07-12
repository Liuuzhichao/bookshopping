package cn.sdut.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.sdut.model.Users;
import cn.sdut.service.UsersService;
import cn.sdut.utils.Dateutils;
import cn.sdut.utils.Md5Utils;
import cn.sdut.utils.UUIDUtils;

@Controller
public class UesrController {
	
	@Autowired
	public UsersService userService;
	
	//注册界面
	@RequestMapping("showReg")//这是一个活动
	public String showReg(String type, HttpServletRequest request) {
		if ( type!=null && type.equals("1")) {
			request.setAttribute("msg", "两次密码不一致");
		} else if (type!=null && type.equals("2")){
			request.setAttribute("msg", "验证码输入错误");
		} else if (type!=null && type.equals("3")) {
			request.setAttribute("msg", "注册失败");
		}
		//返回reg.jsp页面
		return "reg";
	}
	
	//注册功能
	@RequestMapping("reg")
	//给表格添加action属性,值设置为reg,spring容器自动把表单数据绑定到user上,表单数据以键值对方式存储,键值要跟类中属性名字一样
	//表单中的checkcode自动与参数中的checkcode实现绑定
	public String Reg(Users user, String repassword, String checkcode, HttpServletRequest request) {
		user.setRole("user");
		user.setUpdatetime(Dateutils.FormatDate(new Date()));
		//设置用户状态,0表示没有激活,1激活
		user.setState(0);
		
		//将密码与确认密码进行比较,看是否相同
		String password = user.getPassword();
		if(!password.equals(repassword)) {
			//重定向是重定向到某一个controller,
			//请求转发是到一个jsp页面,但是会携带之前的信息(Controller方法里的内容)	
			//?type=1是带了一个参数
			return "redirect:showReg.action?type=1";
		}
		
		//使用MD5对密码进行加密
		//String password = user.getPassword();
		user.setPassword(Md5Utils.md5(password));
		
		//生成一个激活码,UUID生成一串32位的16进制的数字
		String uuid = UUIDUtils.getUUID();
		user.setActivecode(uuid);
		
		//验证验证码是否正确
		//从session当中获取系统生成的验证码
		String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
		//拿用户输入的验证码与生成的验证码进行比对
		if(!checkcode.equals(checkcode_session)) {
			return "redirect:showReg.action?type=2";
		}
		//调用service进行保存
		int num = userService.saveUsers(user);
		if(num==0) {
			return "redirect:showReg.action?type=3";
		}
		return "redirect:showLogin.action?type=1";
	}
	
	//登录界面
	@RequestMapping("showLogin")
	public String showLogin(String type,Model model) {
		if( type!=null && type.equals("1")) {
			model.addAttribute("msg","注册成功,请登录");
		} else if(type!=null && type.equals("2")) {
			model.addAttribute("msg","用户名或密码错误");
		} else if(type!=null && type.equals("3")) {
			model.addAttribute("msg","请登录");
		}
		return "login";
	}
	
	//激活账号的方法
	@RequestMapping("activation")
	public String activation(String code) {
		//根据激活码查询用户
		Users user = userService.findUserByCode(code);
		
		//将账户的状态改为1
		user.setState(1);
		userService.updateUsers(user);
		
		return "login";
	}
	
	//异步校验用户名
	@RequestMapping("checkName")
	@ResponseBody //该方法返回的是一个字符串,不是一个页面
	public String checkName(String username) {
		//System.out.println(username);
		//根据用户名查询是否存在该用户名
		Users user = userService.findUserByName(username);		
			
		//用户存在
		if(user!=null) {
			// 返回一个json类型的字符串  返回的是false的字符串 
			//\是转义字符  msg和false是一个键值对
			return "{\"msg\":\"false\"}";
		}
		return "{\"msg\":\"true\"}";
	}
	
	//登录方法
	@RequestMapping("login")
	public String login(Users user, String remember, String autologin, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
		
		user.setPassword(Md5Utils.md5(user.getPassword()));
		Users users = userService.login(user);
		
		if(users == null) {
			return "redirect:showLogin?type=2";
		}
		
		//记住用户
		//判断用户是否勾选记住用户
		if (remember != null && remember.equals("on")) {
			//将用户名以cookie的形式发送到客户端
			Cookie cookie = new Cookie("username", users.getUsername());
			//cookie默认是会话级别,会随着浏览器的关闭而消失
			//通过setMaxAge设置cookie的存活时间(会存放到硬盘中),单位是秒
			cookie.setMaxAge(60*60);//存储1小时
			response.addCookie(cookie);
			
			Cookie cookie1 = new Cookie("save", "on");
			cookie1.setMaxAge(60*60);//存储1小时
			response.addCookie(cookie1);
		} else {
			//将用户名以cookie的形式发送到客户端,cookie中默认不会识别中文,需要用URLEncoder.encode进行转换
			//转换的cookie在浏览器中也不会是中文,所以在提取cookie值的时候仍需要以相同的格式进行解码
			Cookie cookie = new Cookie("username", URLEncoder.encode(user.getUsername(), "utf-8"));
			//cookie默认是会话级别,会随着浏览器的关闭而消失
			//通过setMaxAge设置cookie的存活时间(会存放到硬盘中),单位是秒
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			
			Cookie cookie1 = new Cookie("save", "");
			cookie1.setMaxAge(0);
			response.addCookie(cookie1);
		}
		
		//设置自动登录
		if(autologin!=null && autologin.equals("on")) {
			Cookie cookie = new Cookie("autologin", URLEncoder.encode(users.getUsername(), "utf-8")+"-"+users.getPassword());
			cookie.setMaxAge(60*60);
			System.out.println(cookie);
			response.addCookie(cookie);
			
		}
		//将登录的用户存放到session当中
		request.getSession().setAttribute("user", users);
		//判断登录用户的角色,如果是admin,跳转到后台管理主页
		if(users.getRole().equals("admin")) {
			return "redirect:showAdminIndex";
		}
		return "redirect:showIndex";
	}
	
	//退出登录
	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		//移除session中存放的登陆对象
		//session.removeAttribute("user");
		
		//销毁session
		session.invalidate();
		//去除自动登录的功能
		//将自动登录cookie中的信息进行清空
		Cookie cookie = new Cookie("autologin", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		return "redirect:showIndex";
	}
	
}
