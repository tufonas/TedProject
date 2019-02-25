package com.ted.project.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;
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
import com.ted.project.entities.ResultsData;
import com.ted.project.entities.User;
import com.ted.project.utilities.ObjectToJson;

/**
 * Servlet implementation class SearchResults
 */
@WebServlet("/SearchResults")
public class SearchResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResults() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String search = request.getParameter("search");
		List<User> usersByName = new ArrayList<User>();
		List<User> usersBySurname = new ArrayList<User>();
		List<User> usersByNameSurname = new ArrayList<User>();
		List<User> friends = new ArrayList<User>();
		List<User> friends_requests = new ArrayList<User>();	
		
		
		UserDao userDao = new UserDaoImpl();
		HttpSession session=request.getSession();  
		User curr_user = new User();
		curr_user = (User) session.getAttribute("user");
		curr_user=userDao.findUserbyId(curr_user.getId());
		ResultsData data = new ResultsData();
		friends = curr_user.getFriend2();
		friends_requests = userDao.findMyFriendRequest(curr_user);
		
		String[] parts = search.split(" ");
		String name="";
		String surname="";
		if(parts.length>1)    
		{
			 name = parts[0]; // 004
			 surname = parts[1];
		}
		if(parts.length==1)				//an valei mono mia leksh
		{
			usersByName = userDao.findUserbyName(search,curr_user.getId());
			usersBySurname = userDao.findUserbySurname(search,curr_user.getId());
			data.setUsersByName(usersByName);
			data.setUsersBySurname(usersBySurname);
		}
		else		// an valei panw apo 1 leksh
		{
			usersByNameSurname = userDao.findUserbyNameSurname(name,surname,curr_user.getId());
			data.setUsersByNameSurname(usersByNameSurname);
		}
		data.setFriend_requests(friends_requests);
		data.setFriends(friends);
		data.setUser(curr_user);
		String json = ObjectToJson.objectToJsonMapper(data); 
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		if(data!=null)
		{
			if(data.get("friend_id")!=null)
			{
				int friendId = Integer.parseInt(data.get("friend_id").getAsString());  

				UserDao userDao = new UserDaoImpl();
				User friend = userDao.findUserbyId(friendId);
				HttpSession session=request.getSession();  
				User user = new User();
				user = (User) session.getAttribute("user");
				user=userDao.findUserbyId(user.getId());  

				friend.getFriendRequest().add(user);
			}
			if(data.get("cancel_id")!=null)
			{
				int friendId = Integer.parseInt(data.get("cancel_id").getAsString());
				UserDao userDao = new UserDaoImpl();
				User friend = userDao.findUserbyId(friendId);
				HttpSession session=request.getSession();  
				User user = new User();
				user = (User) session.getAttribute("user");
				user=userDao.findUserbyId(user.getId());  
				friend.getFriendRequest().remove(user);
			}
		}
	}

}
