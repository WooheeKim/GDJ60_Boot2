package com.iu.base.util;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class MailManager {
	
	@Value("${spring.mail.username}")
	private String sender;
	
	
	@Autowired
	private JavaMailSender javaMailSender;
	
//	public void send(String to, String sub, String con) throws Exception {
//		/* HTML 태그 그대로 전송하는 방법 */
//		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//		mimeMessage.setFrom(sender);
//		mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(to));
//		mimeMessage.setSubject(sub);
//		mimeMessage.setText(con);
//		javaMailSender.send(mimeMessage);
//		
//		/* HTML태그를 무시하고 그대로 Text 전송 */
////		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();		
////		simpleMailMessage.setFrom(sender);
////		simpleMailMessage.setTo(to);
////		simpleMailMessage.setSubject(to);
////		simpleMailMessage.setText(con);
////		
////		javaMailSender.send(simpleMailMessage);
//		
//		
//		
//	}
	
	public void sendEmail(Email email) throws Exception {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		try {
			mimeMessage.setSender(email.);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
