package cn.sdut.utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;


/**
 * 发送邮件的工具类
 * @author Administrator
 *
 */
public class MailUtils {

	/**
	 * 发送邮件的方法
	 * @param toMail 收件人
	 * @param code 激活码
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void sendMail(String toMail, String code) throws AddressException, MessagingException {
		
		//设置邮件服务器
		Properties props = new Properties();
		
		//与邮件服务器的链接
		Session session = Session.getInstance(props,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				//配置发件人的邮箱和密码
				return new PasswordAuthentication("test@book.com", "cc");
			}
		});
		//创建邮件
		Message message = new MimeMessage(session);
		//设置发件人
		message.setFrom(new InternetAddress("lzc@book.com"));
		//设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(toMail));
		//设置主题
		message.setSubject("来自图书商城的邮件");
		//设置邮件的内容
		String msg = "点击<a href='http://localhost:8080/bookshopping/activation?code="+code+"'>此处</a>激活账号";
		message.setContent(msg,"text/html;charset=utf-8");
		//发送邮件
		Transport.send(message);
	}
}
