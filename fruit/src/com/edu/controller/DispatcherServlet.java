package com.edu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.beans.BuyerVo;
import com.edu.beans.SellerVo;
import com.edu.service.impl.FruitServiceImpl;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet(name = "action", urlPatterns = { "*.wj" })
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request,response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);

		HttpSession session=request.getSession();
		
		if(path.equals("/buyerLogin.wj")) {
			System.out.println("바이어 로그인");
			
			String id = request.getParameter("id");
			String password = request.getParameter("passwd");
			
			
			BuyerVo vo = new BuyerVo();
			vo.setId(id);
			vo.setPasswd(password);
			
			FruitServiceImpl svc = new FruitServiceImpl();
			BuyerVo buyer = svc.getBuyer(vo);
			
			if(buyer != null) { // 로그인 성공
				if((buyer.getPasswd()).equals(password)) {
					System.out.println("바비어 로그인 성공");
					session.setAttribute("buyerId", buyer.getId());
					response.sendRedirect("fruitBuyerMenu.jsp");
				}
				else {
					System.out.println("바비어 로그인 실패");
					request.setAttribute("error", "Password Error");
					response.sendRedirect("fruitBuyer.jsp");
				}
			}
			else {
				System.out.println("바이어 로그인 실패");
				request.setAttribute("error", "loginFail");
				response.sendRedirect("fruitBuyer.jsp");
			}
		} else if(path.equals("/buyerLogout.wj")) {
			System.out.println("바이어 로그아웃");
			session.removeAttribute("buyerId");
			response.sendRedirect("fruitBuyer.jsp");
		} else if(path.equals("/buyerInfo.wj")) {
			System.out.println("바이어 Info");
			
			String id = (String)session.getAttribute("buyerId");
			
			BuyerVo vo = new BuyerVo();
			vo.setId(id);
			
			FruitServiceImpl svc = new FruitServiceImpl();
			BuyerVo buyer = svc.getBuyer(vo);
			if(buyer != null) {
				System.out.println("buyer Information : " + vo.getId() + " : " + buyer.getId() 
					+ " : " + buyer.getMoney() + " : " + buyer.getApple_count());
				request.setAttribute("buyer", buyer);
//				response.sendRedirect("fruitBuyerInformation.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("fruitBuyerInformation.jsp");
				rd.forward(request, response);
			}
			else {
				System.out.println("못가져오는데? " + vo.getId());
				request.setAttribute("error", "No such seller");
				response.sendRedirect("fruitBuyerMenu.jsp");
			}
		} else if(path.equals("/registerMoney.wj")) {
			int money=Integer.parseInt(request.getParameter("money"));
			String id=(String)session.getAttribute("buyerId");
			
			BuyerVo vo = new BuyerVo();
			vo.setId(id);
			vo.setMoney(money);
			
			FruitServiceImpl svc=new FruitServiceImpl();
			svc.updateBuyer(vo);
			response.sendRedirect("fruitBuyerMenu.jsp");
			
			
		} else if(path.equals("/sellerLogin.wj")) {
			System.out.println("셀러 로그인");
			
			String id = request.getParameter("id");
			String password = request.getParameter("passwd");
			
			
			SellerVo vo = new SellerVo();
			vo.setId(id);
			vo.setPasswd(password);
			
			FruitServiceImpl svc = new FruitServiceImpl();
			SellerVo seller = svc.getSeller(vo);
			
			if(seller != null) { // 로그인 성공?
				if((seller.getPasswd()).equals(password)) {
					System.out.println("셀러 로그인 성공");
					session.setAttribute("sellerId", seller.getId());
					response.sendRedirect("fruitSellerMenu.jsp");
				}
				else {
					System.out.println("셀러 로그인 실패");
					request.setAttribute("error", "Password Error");
					response.sendRedirect("fruitSeller.jsp");
				}
			}
			else {
				System.out.println("셀러 로그인 실패");
				request.setAttribute("error", "noSuchUser");
				response.sendRedirect("fruitSeller.jsp");
			}
		} else if(path.equals("/sellerLogout.wj")) {
			System.out.println("셀러 로그아웃");
			session.removeAttribute("sellerId");
			response.sendRedirect("fruitSeller.jsp");
		}
		else if(path.equals("/sellerInfo.wj")) {
			System.out.println("셀러 Info");
			
			String id = (String)session.getAttribute("sellerId");
			
			SellerVo vo = new SellerVo();
			vo.setId(id);
			
			FruitServiceImpl svc = new FruitServiceImpl();
			SellerVo seller = svc.getSeller(vo);
			if(seller != null) {
				System.out.println("seller Information : " + vo.getId() + " : " + seller.getId());
				request.setAttribute("seller", seller);
				response.sendRedirect("fruitSellerInformation.jsp");
//				RequestDispatcher rd = request.getRequestDispatcher("fruitSellerInforamtion.jsp");
//				rd.forward(request, response);
			}
			else {
				System.out.println("못가져오는데? " + vo.getId());
				request.setAttribute("error", "No such seller");
				response.sendRedirect("fruitSellerMenu.jsp");
			}
		}
	}

}
