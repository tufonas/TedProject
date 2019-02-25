package com.ted.project.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ted.project.dao.UserDao;
import com.ted.project.dao.UserDaoImpl;
import com.ted.project.entities.User;
import com.ted.project.utilities.ObjectToJson;

/**
 * Servlet implementation class Net
 */
@WebServlet("/Net")
public class Net extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Net() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDao userDao = new UserDaoImpl();
		HttpSession session=request.getSession();  
		User curr_user = new User();
		curr_user = (User) session.getAttribute("user");
		curr_user=userDao.findUserbyId(curr_user.getId());
		
		List<User> friends=curr_user.getFriend2();
		String json = ObjectToJson.objectToJsonMapper(friends); 
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDao userDao = new UserDaoImpl();
		HttpSession session=request.getSession();  
		User curr_user = new User();
		curr_user = (User) session.getAttribute("user");
		curr_user=userDao.findUserbyId(curr_user.getId());
		User friend = new User();
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		if(data!=null)
		{
			if(data.get("removed_id")!=null)
			{
				int friendId = Integer.parseInt( data.get("removed_id").getAsString()); 
				friend = userDao.findUserbyId(friendId);
				friend.getFriend2().remove(curr_user);
				curr_user.getFriend2().remove(friend);
			}
		}
	}

}
