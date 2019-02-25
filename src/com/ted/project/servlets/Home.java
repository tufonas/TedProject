package com.ted.project.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.sql.Timestamp;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ted.project.dao.UserDao;
import com.ted.project.dao.UserDaoImpl;
import com.ted.project.entities.Article;
import com.ted.project.entities.Comment;
import com.ted.project.entities.HomeArticle;
import com.ted.project.entities.User;
import com.ted.project.entities.UserHasLiked;
import com.ted.project.utilities.KnnData;
import com.ted.project.utilities.ObjectToJson;
import com.ted.project.utilities.UploadUtil;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
@MultipartConfig
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDao userDao = new UserDaoImpl();
		HttpSession session=request.getSession();  
		int flag=0;
		String email="";
		if(request.getParameter("id")!=null && request.getParameter("email")!=null)
		{
			email = request.getParameter("email");
			if(!userDao.checkVerify(request.getParameter("id"), email))
			{
				Boolean message = false;
				String json = ObjectToJson.objectToJsonMapper(message);
				response.getWriter().write(json);
				session.invalidate();
				return;
			}	
			flag=1;
			response.sendRedirect("/TedProject1/index.jsp");
			return;
		}
		
		User user = new User();
		List<User> users = new ArrayList<User>();
		if(session.getAttribute("user")==null)
		{
			response.sendRedirect("index.jsp");
		}else user = (User) session.getAttribute("user");
		String userEmail = null;
		String sessionID = null;
		if(flag==0)
		{
			Cookie[] cookies = request.getCookies();
			
			if(cookies !=null)
			{
				for(Cookie cookie : cookies)
				{
					if(cookie.getName().equals("user")) userEmail = cookie.getValue();
					if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
				}
			}
			user=userDao.findUserByEmail(userEmail).get(0);
		}
		else
		{
			user = (User) userDao.findUserByEmail(email).get(0);
			session.setAttribute("user",user);  
		}

		users = userDao.findAll();
	
		if(!userDao.checkVerify("true", user.getEmail()))
		{
			Boolean message = false;
			String json = ObjectToJson.objectToJsonMapper(message);
			response.getWriter().write(json);
			session.invalidate();
			return;
		}
		List<KnnData> data = new ArrayList<KnnData>();

		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		List<User> friends = new ArrayList<User>();
		List<Article> articles = new ArrayList<Article>();
		List<UserHasLiked> likes = new ArrayList<UserHasLiked>();
		List<Comment> comments = new ArrayList<Comment>();
		
		friends = user.getFriend2();
		articles = userDao.findArticlesForHome(user, friends);
		for(int i = 0; i <users.size(); i++)
		{
			KnnData knn = new KnnData();
			for(int j = 0; j <articles.size(); j++)
			{
				int value;
				List<User> friends2 = new ArrayList<User>();
				List<Article> articles2 = new ArrayList<Article>();
				friends2 = users.get(i).getFriend2();
				articles2 =  userDao.findArticlesForHome(users.get(i), friends2);
				value = knn.TakeValue(users.get(i), articles.get(j), articles2);
				knn.getArray().put(articles.get(j).getArticleId(), value);
			}
			knn.setUser(users.get(i));
			data.add(knn);
		}
		HashMap <Integer,Integer> myMap = new HashMap<Integer,Integer>();
		for(int i = 0; i <data.size(); i++)
		{
			if(data.get(i).getUser().getId() == user.getId())			//an einai o current user kanei continue
			{
				myMap = data.get(i).getArray();
				break;
			}
		}
		float my_mean_value=0;
		for(int key : myMap.keySet())
		{
			my_mean_value+=myMap.get(key);
		}
		my_mean_value/=myMap.size();
		HashMap<Integer,Float> eucleidian_dist = new HashMap<Integer,Float>();
		for(int i = 0; i <data.size(); i++)
		{
			float dist;
			int sum=0 ;
			if(data.get(i).getUser().getId() == user.getId())			//an einai o current user kanei continue
			{
				continue;
			}
			for ( int key : data.get(i).getArray().keySet() ) 			//gia ola ta arthra		
			{
				 int value = data.get(i).getArray().get(key);			//foreign user value for article with key the var key
				 int myvalue = myMap.get(key);							//current user value for article with key the var key
				 dist = Math.abs(value - myvalue);
				 sum += dist*dist;
			}
			eucleidian_dist.put(data.get(i).getUser().getId(), (float) Math.sqrt(sum));
		}
		int k = 2;
		float sum_dist=(float) 0.0;

		HashMap<Integer, Float> minMap = new HashMap<Integer,Float>();
		while(k!=0)
		{
			double min =Double.POSITIVE_INFINITY;
			int minKey = 0;
			for ( int key : eucleidian_dist.keySet() )
			{
				if(eucleidian_dist.get(key)<min)
				{
					minKey = key;
					min = eucleidian_dist.get(key);
				}
			}
			if(eucleidian_dist.isEmpty())break;
			sum_dist+=eucleidian_dist.get(minKey);
			minMap.put(minKey, eucleidian_dist.get(minKey));
			eucleidian_dist.remove(minKey);
			k--;
		}
		
		float z = 1/sum_dist;
		if(sum_dist==0.0)
		{
			z=0;
		}
		HashMap<Integer,Float> ratings = new HashMap<Integer,Float>();
		float rating=(float) 0.0;
		float mean_value = (float) 0.0;
		for(int key1 = 0 ;key1<articles.size();key1++)      //kathe arthro
		{
			rating = my_mean_value;
			for(int i=0;i<data.size();i++)					//olous tous users
			{
				for(int key : minMap.keySet())						//kathe user
				{
					if(key == data.get(i).getUser().getId())		//an einai apo tous similars users//kathe xrhsths apo tous similar
					{
						mean_value = (float) 0.0;
						for(int key2 : data.get(i).getArray().keySet())
						{
							mean_value+= data.get(i).getArray().get(key2);				//mean_value = score(mesh timh) tou kathe arthrou gia kathe xrhsth 
						}
						mean_value/=data.get(i).getArray().size();
						rating=rating + minMap.get(key)*(data.get(i).getArray().get(articles.get(key1).getArticleId())-mean_value)*z;
					}
				}
			}
			ratings.put(articles.get(key1).getArticleId(), rating);
		}
		
		HashMap<Integer, Float> bestArticles = new HashMap<Integer,Float>();
		k=3;             //k o arithmos twn recommended arthrwn
		while(k!=0)
		{
			double max =Double.NEGATIVE_INFINITY;
			int maxKey = 0;
			for ( int key : ratings.keySet() )
			{
				if(ratings.get(key)>max)
				{
					maxKey = key;
					max = ratings.get(key);
				}
			}
			bestArticles.put(maxKey, ratings.get(maxKey));
			ratings.remove(maxKey);
			k--;
		}
		List<Article> recommended = new ArrayList<Article>();
		if(!ratings.isEmpty())
		{
			

			for(int key : bestArticles.keySet())
			{
				Article article = new Article();
				article = userDao.findArticleById(key);
				if(!recommended.isEmpty())
				{
					if(bestArticles.get(key)>bestArticles.get(recommended.get(0).getArticleId()))
					{
						recommended.add(0, article);
					}
					else
					{
						recommended.add(article);
					}
				}
				else
				{
					recommended.add(article);
				}
				articles.remove(article);
				
			}
			
			recommended.addAll(articles);
	
			
			likes = userDao.findLikesFromArticles(recommended);
			comments = userDao.findCommentsFromArticles(recommended);
			comments.sort(Comparator.comparing(Comment::getPublishTime));
			Collections.reverse(comments);
		}
		else
		{
			recommended.addAll(articles);
			if(!recommended.isEmpty())
			{
				likes = userDao.findLikesFromArticles(recommended);
				comments = userDao.findCommentsFromArticles(recommended);
				comments.sort(Comparator.comparing(Comment::getPublishTime));
				Collections.reverse(comments);	
			}
		}
		for(int i=0;i<recommended.size();i++)
		{
			
			recommended.get(i).getArticle_comments().sort(Comparator.comparing(Comment::getPublishTime));
			Collections.reverse(recommended.get(i).getArticle_comments());

		}  

		HomeArticle object = new HomeArticle();
		object.setArticles(recommended);
		object.setUser(user);
		object.setLikes(likes);
		object.setFriends(friends);
		object.setComments(comments);
		String json = ObjectToJson.objectToJsonMapper(object);	
		response.getWriter().write(json);
		request.getRequestDispatcher("/home.jsp");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();  
		HttpSession session=request.getSession(false);
		String email;
		User user =  new User();
		UserDao userDao = new UserDaoImpl();
        user=(User)session.getAttribute("user"); 
		user=userDao.findUserbyId(user.getId());
		email=user.getEmail();
		List<User> users = new ArrayList<User>();
		
	
	    if(session!=null)                           //mesa sto logariasmo
	    {

	    	if(request.getParameter("title")!=null)			//dhmiourgia article
	    	{  
	    		File dir = new File("C:\\Users\\É5-6500\\eclipse-workspace\\TedProject1\\WebContent\\resources\\Database\\"+user.getId()+"\\articles\\");
	    		dir.mkdir();
	    		Timestamp time = new Timestamp(System.currentTimeMillis());
	    		String title = request.getParameter("title");
	    		String text = request.getParameter("text");
	    		Part photo;
	    		Part video;
	    		Part audio;
				Article article = new Article();
				article.setText(text);
				article.setTitle(title);				
				article.setUser(user);
				article.setPublishTime(time);
				userDao.createArticle(article);
				user.getArticles_published().add(article);
				
				dir = new File("C:\\Users\\É5-6500\\eclipse-workspace\\TedProject1\\WebContent\\resources\\Database\\"+user.getId()+"\\articles\\"+userDao.FindLastId());
	    		dir.mkdir();
	    		
	    		if(request.getPart("photo").getSize()>0)
	    		{
	    			photo = request.getPart("photo");
					dir = new File("C:\\Users\\É5-6500\\eclipse-workspace\\TedProject1\\WebContent\\resources\\Database\\"+user.getId()+"\\articles\\"+userDao.FindLastId()+"\\photos");
		    		dir.mkdir();
		    		InputStream fileContent = photo.getInputStream();
					String outputPath = "C:\\Users\\É5-6500\\eclipse-workspace\\TedProject1\\WebContent\\resources\\Database\\"+user.getId()+"\\articles\\"+userDao.FindLastId()+"\\photos\\"+user.getId()+".png";
					UploadUtil.fileUpload(fileContent, outputPath, email);
					String path = "resources/Database/"+user.getId()+"/articles/"+userDao.FindLastId()+"/photos/"+user.getId()+".png";  
					article.setImages(path);
	    		}
	    		if(request.getPart("video").getSize()>0)
	    		{
	    			video = request.getPart("video"); // Retrieves <input type="file" name="file">
					dir = new File("C:\\Users\\É5-6500\\eclipse-workspace\\TedProject1\\WebContent\\resources\\Database\\"+user.getId()+"\\articles\\"+userDao.FindLastId()+"\\videos");
		    		dir.mkdir();
		    		InputStream fileContent = video.getInputStream();
					String outputPath = "C:\\Users\\É5-6500\\eclipse-workspace\\TedProject1\\WebContent\\resources\\Database\\"+user.getId()+"\\articles\\"+userDao.FindLastId()+"\\videos\\"+user.getId()+".mp4";
					String path = "resources/Database/"+user.getId()+"/articles/"+userDao.FindLastId()+"/videos/"+user.getId()+".mp4";  
					UploadUtil.fileUpload(fileContent, outputPath, email);
					article.setVideos(path);
	    		}
	    		if(request.getPart("audio").getSize()>0)
	    		{
	    			audio = request.getPart("audio"); // Retrieves <input type="file" name="file">
					dir = new File("C:\\Users\\É5-6500\\eclipse-workspace\\TedProject1\\WebContent\\resources\\Database\\"+user.getId()+"\\articles\\"+userDao.FindLastId()+"\\audios");
		    		dir.mkdir();
		    		InputStream fileContent = audio.getInputStream();
					String outputPath = "C:\\Users\\É5-6500\\eclipse-workspace\\TedProject1\\WebContent\\resources\\Database\\"+user.getId()+"\\articles\\"+userDao.FindLastId()+"\\audios\\"+user.getId()+".mp3";
					String path = "resources/Database/"+user.getId()+"/articles/"+userDao.FindLastId()+"/audios/"+user.getId()+".mp3";  
					UploadUtil.fileUpload(fileContent, outputPath, email);
					article.setSounds(path);
	    		}
				userDao.UpdateArticle(article);
				request.getRequestDispatcher("/home.jsp").forward(request, response);
				return;
	    	}
			JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
	    	if(data!=null)
	    	{
	    		if(data.get("comment")!=null)
	    		{
		    		String comment_text = data.get("comment").getAsString();
		    		int articledId = Integer.parseInt(data.get("article_id").getAsString()); 
		    		
		    		Article article = new Article();
		    		article = userDao.findArticleById(articledId);
		    		
		    		Timestamp time = new Timestamp(System.currentTimeMillis());
		    		Comment comment = new Comment();
		    		comment.setText(comment_text);
		    		comment.setUser(user);
		    		user.getComments().add(comment);
		    		comment.setPublishTime(time);
		    		comment.setArticle(article);
		    		article.getArticle_comments().add(comment);
		    		userDao.createComment(comment);
	    		}
		    		
	    		else if(data.get("like")!=null)
	    		{
	    			UserHasLiked like = new UserHasLiked();
	        		Timestamp time = new Timestamp(System.currentTimeMillis());
	    			int article_id = Integer.parseInt(data.get("like").getAsString());
	    			Article article = new Article();
	    			article = userDao.findArticleById(article_id);
	    			user.addLikeToArticle(article, like, time);
	    		}
	    		else if(data.get("dislike")!=null)
	    		{
	    			UserHasLiked like = new UserHasLiked();
	    			int article_id = Integer.parseInt(data.get("dislike").getAsString());
	    			Article article = new Article();
	    			article = userDao.findArticleById(article_id);
	    			like = userDao.findLikeFromUserAndArticle(user, article);
	    			
	    			userDao.removeLikeFromArticle(article, like, user);
	    		}
	    	}
	    }  
	}

}
