package com.myspring.pro28.ex04;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private SimpleMailMessage preConfiguredMessage;
	
	
	@Async
	public void sendMail(String to, String subject, String body) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			
//			# MimeMessageHelper에 MimeMessage를 담아서 설정 (charset 등)
//			# 왜 굳이 MimeMessageHelper에 담아서 설정? >> charset을 자동으로 설정해줌
			
//			# Constructor
//			  >> MimeMessageHelper(MimeMessage mimeMessage, boolean multipart, String encoding)
//			  >> Create a new MimeMessageHelper for the given MimeMessage, in multipart mode (supporting alternative texts, inline elements and attachments) if requested.
			
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8");
//			#setCc : 참조 메일주소 설정
//			messageHelper.setCc("lkh14011424@gmail.com");
			messageHelper.setFrom("lkh14011424@gmail.com","이광훈");
			messageHelper.setSubject(subject);
			messageHelper.setTo(to);
			messageHelper.setText(body,true);
			mailSender.send(message);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Async
	public void sendPreConfiguredMail(String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
		mailMessage.setText(message);
		mailSender.send(mailMessage);
	}
}
