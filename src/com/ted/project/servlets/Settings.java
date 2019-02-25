package com.ted.project.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ted.project.dao.UserDao;
import com.ted.project.dao.UserDaoImpl;
import com.ted.project.entities.User;
import com.ted.project.utilities.Hash;
import com.ted.project.utilities.ObjectToJson;

/**
 * Servlet implementation class Settings
 */
@WebServlet("/Settings")
public class Settings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Settings() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDaoImpl();
		HttpSession session=request.getSession(); 
		User user = new User();
		user = (User) session.getAttribute("user");
		user=userDao.findUserbyId(user.getId());
		String json = ObjectToJson.objectToJsonMapper(user);
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDaoImpl();
		HttpSession session=request.getSession();  
		User user = new User();
		user = (User) session.getAttribute("user");
		user=userDao.findUserbyId(user.getId());
		String json = ObjectToJson.objectToJsonMapper(user);
		
		if(request.getParameter("old_password")!=null)
		{  

			String oldPassword = request.getParameter("old_password");
			try {
				if(user.getPassword().equals(Hash.HashPassword(oldPassword)))		//old password swstos
				{
					if(request.getParameter("new_password")!=null && request.getParameter("confirm_password")!=null)
					{

						String newPass = request.getParameter("new_password");
						String confirmPass = request.getParameter("confirm_password");
						
						if(newPass.equals(confirmPass))		//epituxhs allagh kwdikou
						{
							user.setPassword(Hash.HashPassword(newPass));
						}
					}
					request.setAttribute("message_complete", "Update was successful!");
				}
				else
				{
					request.setAttribute("message_fail", "Old Password Is Wrong.Try Again!");
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("new_email")!=null)
		{
			String newEmail = request.getParameter("new_email");
			user.setEmail(newEmail);
		}
		request.getRequestDispatcher("settings.jsp").forward(request, response);

	}

}
