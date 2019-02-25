package com.ted.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ted.project.dao.UserDao;
import com.ted.project.dao.UserDaoImpl;
import com.ted.project.entities.User;
import com.ted.project.utilities.Hash;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		try {
			password=Hash.HashPassword(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserDao userdao = new UserDaoImpl();
		
		List<User> user = new ArrayList<User>();
		user = userdao.findEmail_Password(email,password);
		
		if(user.isEmpty())
		{
			request.setAttribute("message", "Email or Password is wrong!");
			request.getRequestDispatcher("/index.jsp").forward(request, response);  
			return ;
		}
		
		
		if(user.get(0).getTypeOfUser().equals("admin"))
		{
			System.out.println("diaxeirisths");
			request.getRequestDispatcher("/diaxeirish.jsp").forward(request, response);
		}
		else
		{
			
			List<String> emails = new ArrayList<String>();
    		emails.add(email);
    		List<User> users = new ArrayList<User>();
    		UserDao userDao = new UserDaoImpl();
    		users=userDao.findUserbyEmails(emails);
    		
	        HttpSession session=request.getSession();  
	        session.setAttribute("user",users.get(0)); 
			Cookie userName = new Cookie("user", users.get(0).getEmail());
			userName.setMaxAge(30*60);
			response.addCookie(userName);
	        response.sendRedirect("home.jsp");

		}
	}

}
