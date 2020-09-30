package com.myspring.pro30.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.myspring.pro30.member.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<MemberVO> selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}
	
	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result=sqlSession.insert("mapper.member.insertMember",memberVO);
		return result;
	}
	
	@Override
	public int deleteMember(MemberVO memberVO) throws DataAccessException {
		int result=sqlSession.delete("mapper.member.deleteMember",memberVO);
		return result;
	}
	
	@Override
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException {
		MemberVO membvo = sqlSession.selectOne("mapper.member.loginById", memberVO);
		return membvo;
	}

}
