package com.ted.project.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ted.project.dao.UserDao;
import com.ted.project.dao.UserDaoImpl;
import com.ted.project.entities.NotificationData;
import com.ted.project.entities.User;
import com.ted.project.entities.UserHasLiked;
import com.ted.project.utilities.ObjectToJson;
import com.ted.project.entities.Article;
import com.ted.project.entities.Comment;

/**
 * Servlet implementation class Notification
 */
@WebServlet("/Notification")
public class Notification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Notification() {
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
		
		
		NotificationData data = new NotificationData();
		List<UserHasLiked> likes = new ArrayList<UserHasLiked>();
		List<Comment> comments = new ArrayList<Comment>();
		List<Article> articles = new ArrayList<Article>();
		
		articles = userDao.findArticlesOfUser(curr_user);
		if(!articles.isEmpty())
		{	
			likes = userDao.findLikesFromArticles(articles);
			comments = userDao.findCommentsFromArticles(articles);
		}
		
		
		
		data.setFriend_requests(curr_user.getFriendRequest());
		data.setComments(comments);
		data.setLikes(likes);
		data.setUser(curr_user);
		String json = ObjectToJson.objectToJsonMapper(data); 
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
		
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		if(data!=null)
		{
			if(data.get("choice")!=null)
			{
				String choice =  data.get("choice").getAsString();
				int friendId = Integer.parseInt( data.get("friend").getAsString()); 
				User friend = userDao.findUserbyId(friendId);
				if(choice.equals("accept"))
				{
					curr_user.addUserToFriend(friend);
					curr_user.getFriendRequest().remove(friend);
				}
				else
				{ 
					curr_user.getFriendRequest().remove(friend);
				}
			}
		}
	}

}
