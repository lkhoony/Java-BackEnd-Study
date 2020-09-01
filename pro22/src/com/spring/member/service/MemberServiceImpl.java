package com.spring.member.service;

import java.util.List;

import com.spring.member.dao.MemberDAO;

public class MemberServiceImpl implements MemberService{
	
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public List listMembers() {
	
		List membersList = null;
		membersList = memberDAO.selectAllMembers();
		return membersList;
	}
	
}
