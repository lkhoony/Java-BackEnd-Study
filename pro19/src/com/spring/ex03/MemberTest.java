package com.spring.ex03;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class MemberTest {

	public static void main(String[] args) {
		BeanFactory factoty = new XmlBeanFactory(new FileSystemResource("member.xml"));
		MemberService memberService = (MemberService)factoty.getBean("memberService");
		memberService.listMembers();
	}

}
