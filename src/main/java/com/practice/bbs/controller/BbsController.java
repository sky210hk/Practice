package com.practice.bbs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.bbs.service.BbsService;
import com.practice.bbs.service.DeleteServiceImpl;
import com.practice.bbs.service.GetContentServiceImpl;
import com.practice.bbs.service.GetListServiceImpl;
import com.practice.bbs.service.RegistServiceImpl;
import com.practice.bbs.service.UpdateServiceImpl;


@WebServlet("*.bbs")
public class BbsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BbsController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doAction(request, response);
		
	}
				
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doAction(request, response);	
		
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		
		String command = uri.substring(path.length());
		
		System.out.println(command);
		
		BbsService service;
		
		if(command.equals("/board/board.bbs")) {
			
			service = new GetListServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("bbs.jsp").forward(request, response);
		} else if (command.equals("/board/write.bbs")) {
			
			request.getRequestDispatcher("bbs_write.jsp").forward(request, response);
			
		} else if(command.equals("/board/regist.bbs")) {
			
			service = new RegistServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("board.bbs").forward(request, response);
			
		} else if(command.equals("/board/content.bbs")) {
			
			service = new GetContentServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("bbs_content.jsp").forward(request, response);
			
		} else if(command.equals("/board/modify.bbs")) {
			
			service = new GetContentServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("bbs_modify.jsp").forward(request, response);
			
			
		} else if(command.equals("/board/update.bbs")) {
			
			service = new UpdateServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("content.bbs?bno=" + request.getParameter("bno") );
			
		} else if(command.equals("/board/delete.bbs")) {
			
			service = new DeleteServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("board.bbs");
			
		}
		
	}

}
