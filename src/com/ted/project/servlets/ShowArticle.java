package com.ted.project.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.ted.project.entities.Article;
import com.ted.project.entities.Comment;
import com.ted.project.entities.HomeArticle;
import com.ted.project.entities.User;
import com.ted.project.entities.UserHasLiked;
import com.ted.project.utilities.ObjectToJson;

/**
 * Servlet implementation class ShowArticle
 */
@WebServlet("/ShowArticle")
public class ShowArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowArticle() {
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
		
		if(request.getParameter("articleId")!=null)
		{
			int articledId = Integer.parseInt(request.getParameter("articleId")); 
			int userId = Integer.parseInt(request.getParameter("userId")); 
			int commId = 0;
			System.out.println("comm="+request.getParameter("commId"));
			if(request.getParameter("commId")!="")
					commId=Integer.parseInt(request.getParameter("commId")); 
			Article article = new Article();
			article = userDao.findArticleById(articledId);

			List<User> friends = new ArrayList<User>();
			List<Article> articles = new ArrayList<Article>();
			List<UserHasLiked> likes = new ArrayList<UserHasLiked>();
			List<Comment> comments = new ArrayList<Comment>();
			
			friends = user.getFriend2();
			articles.add(article);
			likes = userDao.findLikesFromArticles(articles);
			comments = userDao.findCommentsFromArticles(articles);
			comments.sort(Comparator.comparing(Comment::getPublishTime));
			Collections.reverse(comments);
			HomeArticle object = new HomeArticle();
			object.setArticles(articles);
			object.setUser(user);
			object.setActive_comm_id(commId);
			object.setLikes(likes);
			object.setComments(comments);
			String json = ObjectToJson.objectToJsonMapper(object);	
			response.getWriter().write(json);
		}
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
		

	}

}
