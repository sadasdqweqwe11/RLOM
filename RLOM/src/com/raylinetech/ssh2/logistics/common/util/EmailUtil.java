package com.raylinetech.ssh2.logistics.common.util;

import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author sunzhanhu
 * 
 */
public class EmailUtil {

	/**
	 * 以文本格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件的信息
	 */
	public boolean sendTextMail() {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 */
	public boolean sendHtmlMail() {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param mailServerHost
	 *            邮件服务器域名
	 * @param mailServerPort
	 *            邮件服务器地址
	 * @param UserName
	 *            用户名
	 * @param password
	 *            密码
	 * @param toAddress
	 *            邮件发送地址
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 */
	public EmailUtil(String mailServerHost, String mailServerPort,
			String userName, String password, String toAddress, String subject,
			String content) {
		this.mailInfo.setMailServerHost(mailServerHost);
		this.mailInfo.setMailServerPort(mailServerPort);
		this.mailInfo.setValidate(true);
		this.mailInfo.setUserName(userName);
		this.mailInfo.setPassword(password);
		this.mailInfo.setFromAddress(userName);
		this.mailInfo.setToAddress(toAddress);
		this.mailInfo.setSubject(subject);
		this.mailInfo.setContent(content);
	}

	private MailSenderInfo mailInfo = new MailSenderInfo();

	public MailSenderInfo getMailInfo() {
		return mailInfo;
	}

	public void setMailInfo(MailSenderInfo mailInfo) {
		this.mailInfo = mailInfo;
	}

	public boolean isEmail(final String searchPhrase) {
		final String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		final Pattern regex = Pattern.compile(check);
		final Matcher matcher = regex.matcher(searchPhrase);
		return matcher.matches();
	}

	/**
	 * 
	 * @param userId
	 * @param email
	 * @param verifyCode
	 * @return
	 */
	public static String getFindPasswdMail(String userId, String email, String verifyCode) {

		String content = "<div class=\"main_cen\">\n\n"
				+ "<p class=\"title_all  zi_6\">\n"
				+ "您好,欢迎使用校区网!<br>\n"
				+ "您的登陆账号为：<span class=\"title_big2\"><a target=\"_blank\" href=\"mailto:"
				+ email
				+ "\">"
				+ email
				+ "</a> </span>\n"
				+ "</p>\n\n"
				+

				" <p class=\"title_all_top zi_6 link\">\n"
				+ "为了您的下一步操作，需要验证您的登录邮箱，请点击以下链接确认更改。 <br>\n\n"
				+

				"<a target=\"_blank\" href=\"http://www.uxiaoqu.com/signToFindPasswd.action?v="
				+ verifyCode+"&userID=" + userId
				+ "\">http://www.uxiaoqu.com/signToFindPasswd.action?v="
				+ verifyCode+"&userID=" + userId
				+ "</a><br>\n"
				+ "<span class=\"red\">(请在48小时内完成确认，48小时后邮件失效，您将需要重新填写注册信息) </span>\n"
				+ "</p>\n\n"
				+

				"<p class=\"title_all_top zi_6\">"
				+ "如果通过点击以上链接无法访问，请将该网址复制并粘贴至新的浏览器窗口中。<br> \n"
				+ "如果您错误地收到了此电子邮件，您无需执行任何操作来取消帐户！此帐户将不会启动。\n"
				+ "</p>\n\n"
//				+
//				"<p class=\"title_all_top zi_6 link\">\n\n"
//				+

//				"如果您对您的帐户有任何问题或疑问，请浏览新浪会员注册常见问题解答，地址如下：<br>\n"
//				+ "<a target=\"_blank\" href=\"http://iask.sina.com.cn/browse/get_class4.php?fatherid=1315&amp;nav=1\">http://iask.sina.com.cn/browse/get_class4.php?fatherid=1315&amp;nav=1 </a><br>"
				+ "这只是一封公告邮件。我们并不监控或回答对此邮件的回复。\n" + "</p>\n\n" +

				"</div>\n";

		return content;
	}

	public static String getVerifyMail(String userId, String email, String verifyCode) {

		String content = "<div class=\"main_cen\">\n\n"
				+ "<p class=\"title_all  zi_6\">\n"
				+ "感谢您注册会员!<br>\n"
				+ "您的登陆账号为：<span class=\"title_big2\"><a target=\"_blank\" href=\"mailto:"
				+ email
				+ "\">"
				+ email
				+ "</a> </span>\n"
				+ "</p>\n\n"
				+

				" <p class=\"title_all_top zi_6 link\">\n"
				+ "要启用帐户并确认电子邮件地址，请单击以下链接： <br>\n\n"
				+

				"<a target=\"_blank\" href=\"http://www.uxiaoqu.com/register/signup.action?v="
				+ verifyCode+"&userID=" + userId
				+ "\">http://www.uxiaoqu.com/register/signup.action?v="
				+ verifyCode+"&userID=" + userId
				+ "</a><br>\n"
				+ "<span class=\"red\">(请在48小时内完成确认，48小时后邮件失效，您将需要重新填写注册信息) </span>\n"
				+ "</p>\n\n"
				+

				"<p class=\"title_all_top zi_6\">"
				+ "如果通过点击以上链接无法访问，请将该网址复制并粘贴至新的浏览器窗口中。<br> \n"
				+ "如果您错误地收到了此电子邮件，您无需执行任何操作来取消帐户！此帐户将不会启动。\n"
				+ "</p>\n\n"
//				+
//				"<p class=\"title_all_top zi_6 link\">\n\n"
//				+

//				"如果您对您的帐户有任何问题或疑问，请浏览新浪会员注册常见问题解答，地址如下：<br>\n"
//				+ "<a target=\"_blank\" href=\"http://iask.sina.com.cn/browse/get_class4.php?fatherid=1315&amp;nav=1\">http://iask.sina.com.cn/browse/get_class4.php?fatherid=1315&amp;nav=1 </a><br>"
				+ "这只是一封公告邮件。我们并不监控或回答对此邮件的回复。\n" + "</p>\n\n" +

				"</div>\n";

		return content;
	}

	public static void main(String[] args) {

//		String mailServerHost = "smtp.sina.com.cn";
//		String mailServerPort = "25";
//		String userName = "meiyoubbs@sina.com";
//		String password = "c0c1c2";
//		String toAddress = "67202372@qq.com";
//		String subject = "难道在黑名单里？";

		String mailServerHost = "smtp.sina.com.cn";
		String mailServerPort = "25";
		String userName = "meiyoubbs@sina.com";
		String password = "c0c1c2";
		String toAddress = "67202372@qq.com";
		String subject = "难道在黑名单里？";
		
		// String content = "<a
		// href=\"localhost/meiyoubbs/regist/verified.action?code=123123\">sunzhanhu</a>";
		// String content = "<a href=\"www.baidu.com\">sunzhanhu</a>";
		String content = "<a href=\"http://www.raylinetech.com\">订单号不足500，该咋办？</a>";
		EmailUtil util = new EmailUtil(mailServerHost, mailServerPort,
				userName, password, toAddress, subject, content);
		util.sendHtmlMail();
//		System.out.println(EmailUtil.getVerifyMail("123", "1234"));
	}
}

class MailSenderInfo {
	// IP and port of server which send mail
	private String mailServerHost;
	private String mailServerPort = "";
	// address of server which send mail
	private String fromAddress;
	// address of server which receive mail
	private String toAddress;
	// user name and password of server which will login and send mail
	private String userName;
	private String password;
	// if need authentication
	private boolean validate = false;
	// theme of mail
	private String subject;
	// context of mail
	private String content;
	// file name of attachment in email
	private String[] attachFileNames;

	/**
	 * get properties of mail conversation
	 */
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] fileNames) {
		this.attachFileNames = fileNames;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String textContent) {
		this.content = textContent;
	}
}

class MyAuthenticator extends Authenticator {
	String userName = null;
	String password = null;

	public MyAuthenticator() {
	}

	public MyAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}