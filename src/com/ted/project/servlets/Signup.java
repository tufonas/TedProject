package com.ted.project.servlets;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ted.project.dao.UserDao;
import com.ted.project.dao.UserDaoImpl;
import com.ted.project.entities.User;
import com.ted.project.utilities.EmaiUtil;
import com.ted.project.utilities.Hash;
import com.ted.project.utilities.UploadUtil;


/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
@MultipartConfig
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		PrintWriter out = response.getWriter();
		UserDao userDao = new UserDaoImpl();
		List<User> user = new ArrayList<User>();
		user = userDao.findAll();
		int size=user.size();
		
		String onoma = (String) request.getParameter("name");
		String epitheto = (String) request.getParameter("surname");
		String thlephwno = (String) request.getParameter("telephone");
		String epaggelma = (String) request.getParameter("profession");
		String foreas = (String) request.getParameter("institution");
		Part photo=request.getPart("photo");
		
		
		
		String check_password = (String) request.getParameter("password_check");
		String email = (String) request.getParameter("e_mail");
		user=userDao.findUserByEmail(email); 
		if(!user.isEmpty())
		{
			request.setAttribute("message_fail", "Sign up Failed!Email is already in use!");
			request.getRequestDispatcher("/signup.jsp").forward(request, response);  
			return ;
		}
		String password=(String) request.getParameter("password");
		if(!password.equals(check_password))
		{
			out.println("Oi 2 kwdikoi den tairiazoun");
			return;
		}
		try {
			password = Hash.HashPassword(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		StringBuffer url=request.getRequestURL();

		User newUser = new User();
		newUser.setEmail(email);
		newUser.setName(onoma);
		newUser.setPassword(password);

		newUser.setForeas(foreas);
		newUser.setProfession(epaggelma);
		newUser.setSurname(epitheto);
		newUser.setTelephone(thlephwno);
		newUser.setTypeOfUser("epaggelmatias");

    	 String uuid ;
    	 uuid=EmaiUtil.sendEmail(newUser,url);
    	 newUser.setVerify(uuid);
    	 userDao.createUser(newUser);
    	 
    	 String id = userDao.findLastUserId(); 
    	 InputStream fileContent = photo.getInputStream();   
    	 System.out.println(fileContent);
    	 String outputPath = "C:\\Users\\É5-6500\\eclipse-workspace\\TedProject1\\WebContent\\resources\\Database\\"+id+"\\images\\"+id+".png";
    	 UploadUtil.fileUpload(fileContent, outputPath, id);
    	 String path = "resources/Database/"+id+"/images/"+id+".png";  
    	 newUser.setImage(path);
    	 userDao.UpdateUser(newUser);
   
		request.setAttribute("message_complete", "Sign up competed successfully!Go to your email inbox to confirm your email!");
		request.getRequestDispatcher("/signup.jsp").forward(request, response);  
		return ;
	}

}
