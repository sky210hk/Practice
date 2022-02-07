package com.practice.bbs.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.bbs.model.BbsDAO;

public class DeleteServiceImpl implements BbsService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bno = request.getParameter("bno");
		
		BbsDAO dao = BbsDAO.getInstance();
		dao.delete(bno);
		
	}

}
