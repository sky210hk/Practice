package com.practice.bbs.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.bbs.model.BbsDAO;
import com.practice.bbs.model.BbsVO;

public class GetContentServiceImpl implements BbsService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bno = request.getParameter("bno");
		
		
		BbsDAO dao = BbsDAO.getInstance();
		BbsVO vo = dao.GetContent(bno);
		
		request.setAttribute("vo", vo);
		
		
	}

}
