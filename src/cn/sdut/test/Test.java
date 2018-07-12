package cn.sdut.test;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import cn.sdut.utils.MailUtils;

public class Test {
	
//	public void save() {
//		System.out.println("save...");
//	}
//	
//	public void delete() {
//		save();
//	}
	public static void main(String[] args) throws AddressException, MessagingException {
		String code = "点击<a href='http://localhost:8080/bookshopping/showIndex.action'>此处</a>激活账户";
		MailUtils.sendMail("test@book.com", code);
	}
}
