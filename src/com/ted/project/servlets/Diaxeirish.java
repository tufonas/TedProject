package com.ted.project.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import com.ted.project.utilities.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.persistence.oxm.mappings.converters.XMLConverterAdapter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ted.project.dao.UserDao;
import com.ted.project.dao.UserDaoImpl;
import com.ted.project.entities.User;

/**
 * Servlet implementation class Diaxeirish
 */
@WebServlet("/Diaxeirish")
public class Diaxeirish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Diaxeirish() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		UserDao userDao = new UserDaoImpl();
		List<User> users = new ArrayList<User>();
		users = userDao.findProfessionals(); 
		String json = ObjectToJson.objectToJsonMapper(users); 
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

    	String[] users = request.getParameterValues("user");
		System.out.println("hjasdh1");
    	if(users!=null)
    	{
    		System.out.println("hjasdh");
    		Xml.XmlCreate(users);
    		request.getRequestDispatcher("diaxeirish.jsp").forward(request, response);	
    		return;
    	}
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		if(data!=null)
		{
			if(data.get("user_id")!=null)
			{
				int user_id = Integer.parseInt(data.get("user_id").getAsString());
				User user = new User();
				UserDao userDao = new UserDaoImpl();
				user = userDao.findUserbyId(user_id);
				userDao.DeleteUser(user);
				File parent = new File("C:\\Users\\É5-6500\\eclipse-workspace\\TedProject1\\WebContent\\resources\\Database\\"+user_id);
				File articles_folder = new File("C:\\Users\\É5-6500\\eclipse-workspace\\TedProject1\\WebContent\\resources\\Database\\"+user_id+"\\articles");
				File images_folder = new File("C:\\Users\\É5-6500\\eclipse-workspace\\TedProject1\\WebContent\\resources\\Database\\"+user_id+"\\images");
				String[] articles = articles_folder.list();
				String[] images = images_folder.list();
				
				if(images!=null)
				{
					for(String s: images)
					{
						File currentFile = new File(images_folder.getPath(),s);
						currentFile.delete();
					}
					images_folder.delete();
				}
				if(articles!=null)
				{

					for(String s: articles)
					{
						File prnt = new File("C:\\Users\\É5-6500\\eclipse-workspace\\TedProject1\\WebContent\\resources\\Database\\"+user_id+"\\articles\\"+s);
						System.out.println("files1: "+s);
						File videos_folder = new File("C:\\Users\\É5-6500\\eclipse-workspace\\TedProject1\\WebContent\\resources\\Database\\"+user_id+"\\articles\\"+s+"\\videos");
						File audios_folder = new File("C:\\Users\\É5-6500\\eclipse-workspace\\TedProject1\\WebContent\\resources\\Database\\"+user_id+"\\articles\\"+s+"\\audios");
						File photos_folder = new File("C:\\Users\\É5-6500\\eclipse-workspace\\TedProject1\\WebContent\\resources\\Database\\"+user_id+"\\articles\\"+s+"\\photos");

						String[] videos = videos_folder.list();
						String[] audios = audios_folder.list();
						String[] photos = photos_folder.list();
						
						if(videos!=null)
						{
							for(String str: videos)
							{
								File currentFile = new File(videos_folder.getPath(),str);
								currentFile.delete();
							}
							videos_folder.delete();
						}
						if(audios!=null)
						{
							for(String str: audios)
							{
								File currentFile = new File(audios_folder.getPath(),str);
								currentFile.delete();
							}
							audios_folder.delete();
						}
						if(photos!=null)
						{
							for(String str: photos)
							{
								File currentFile = new File(photos_folder.getPath(),str);
								currentFile.delete();
							}
							photos_folder.delete();
						}
						prnt.delete();
					}
					articles_folder.delete();
				}
				parent.delete();
			}
		}
		request.getRequestDispatcher("diaxeirish.jsp").forward(request, response);	
		
	}

}
