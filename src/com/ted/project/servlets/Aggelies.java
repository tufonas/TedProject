package com.ted.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
import com.ted.project.entities.User;
import com.ted.project.utilities.AdsKnnData;
import com.ted.project.utilities.ObjectToJson;
import com.ted.project.entities.AdvertismentData;
import com.ted.project.entities.Aggelia;
import com.ted.project.entities.Application;
import com.ted.project.entities.Comment;

/**
 * Servlet implementation class Aggelia
 */
@WebServlet("/Aggelies")
public class Aggelies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Aggelies() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		User user =  new User();
		UserDao userDao = new UserDaoImpl();
        user=(User)session.getAttribute("user"); 
		user=userDao.findUserbyId(user.getId());
		List<User> friends = new ArrayList<User>();
		List<Aggelia> ads = new ArrayList<Aggelia>();
		List<Aggelia> application_ads = new ArrayList<Aggelia>();
		List<User> no_friends = new ArrayList<User>();
		no_friends = userDao.findAll();

		
		
		friends = user.getFriend2();
		if(!friends.isEmpty())
		{
			ads = userDao.findAggeliesToPrint(user, friends);		//ads pou emfanizontai sthn mesh//
			for(int i=0;i<ads.size();i++)
			{
				if(!ads.get(i).getApplications().isEmpty())
				{
					for(int j=0;j<ads.get(i).getApplications().size();j++)
					{
						if(ads.get(i).getApplications().get(j).getPending().equals("FALSE"))
						{
							ads.remove(ads.get(i));
							i--;
							break;
						}
					}
				}
			}
		}
		for(int i=0;i<no_friends.size();i++)		//vriskoume autous pou den einai filoi mas
		{
			for(int j=0;j<friends.size();j++)
			{
				if(no_friends.get(i).getId()==friends.get(j).getId())
				{
					no_friends.remove(friends.get(j));
					i--;
					break;
				}
			}
		}
		no_friends.remove(user);
		List<Aggelia> common_skills_ads = new ArrayList<Aggelia>();
		List<Aggelia> ads_where_user_applied = new ArrayList<Aggelia>();
		List<Application> my_applications = new ArrayList<Application>();
		List<Aggelia> all_ads = new ArrayList<Aggelia>();
		all_ads = userDao.findAllAds();
		my_applications = user.getApplications();
		for(int i=0;i<my_applications.size();i++)
		{
			ads_where_user_applied.add(my_applications.get(i).getAd());
		}
		List<AdsKnnData> adsData = new ArrayList<AdsKnnData>();
		for(int i = 0 ;i<all_ads.size();i++)			//for gia oles tis ads
		{
			AdsKnnData temp = new AdsKnnData();
			temp.setId(all_ads.get(i).getAdvertismentId());
			for(int j=0;j<ads_where_user_applied.size();j++)		//for gia tis ads pou exei kanei apply
			{
				if(ads_where_user_applied.get(j).getAdvertismentId()==all_ads.get(i).getAdvertismentId())
				{
					temp.setScore(1);
					break;
				}
				else
				{
					temp.setScore(0);
				}
			}
			String[] ads_skills = all_ads.get(i).getNecessarySkills().split(",");   //kanoume split ta necessary skills ths aggelias
			String[] curr_user_skills = null;
			if(user.getExperience()!=null)
				if(user.getExperience().getSkills()!=null)
					curr_user_skills = user.getExperience().getSkills().split(",");  		//kanoume split ta skills tou xrhsth
			int common_skills=0;
			for(int k=0;k<ads_skills.length;k++)
			{
				if(curr_user_skills==null)break;
				for(int l=0;l<curr_user_skills.length;l++)
				{
					if(ads_skills[k].equals(curr_user_skills[l]))
					{
						common_skills++;
					}
				}
			}
			temp.setScore(temp.getScore()+2*common_skills);
			adsData.add(temp);
		}
		int k = 5;	
		for(int i = 0 ;i<adsData.size();i++)
		{
			if(adsData.get(i).getScore()==0)
			{
				adsData.remove(adsData.get(i));
				i--;
			}
		}
		adsData.sort(Comparator.comparing(AdsKnnData::getScore));
		if(k<adsData.size())
		{
			for(int i = 0 ;i<adsData.size()-k;i++)
			{
				adsData.remove(adsData.get(i));
				i--;
			}	
		}
		List<String> skills_from_similar_ads = new ArrayList<String>();
		String[] tmp = null;
		for(int i = 0 ;i<adsData.size();i++)			//pairnoume ola ta necessary skills apo tis similar ads kai tis vazoume se ena pinaka// 
		{
			tmp = userDao.findAggeliaById(adsData.get(i).getId()).getNecessarySkills().split(",");
			for(int j=0;j<tmp.length;j++)
			{
				skills_from_similar_ads.add(tmp[j]);
			}
		}	
		List<Aggelia> recommended_ads = new ArrayList<Aggelia>();
		for(int i=0;i<no_friends.size();i++)		//oloi oi mh filoi mas
		{
			List<Aggelia> no_friends_ads = new ArrayList<Aggelia>();
			no_friends_ads = no_friends.get(i).getAggelies_published();		//ads twn mh filwn mas
			for(int j=0;j<no_friends_ads.size();j++)		//kathe aggelia tou mh filou mas
			{	
				String[] ads_skills = no_friends_ads.get(j).getNecessarySkills().split(",");   //kanoume split ta necessary skills ths aggelias
				int flag = 0;
				for(int p=0;p<ads_skills.length;p++)
				{
					for(int l=0;l<skills_from_similar_ads.size();l++)
					{
						if(ads_skills[p].equals(skills_from_similar_ads.get(l)))
						{
							recommended_ads.add(no_friends_ads.get(j));
							flag=1;
							break;
						}
					}
					if(flag==1)break;
				}
			}
		}
		application_ads = userDao.findAdvertismentsByApplications(user);
		AdvertismentData data = new AdvertismentData();
		ads.addAll(recommended_ads);
		data.setAds(ads);
		data.setUser(user);
		data.setApplAds(application_ads);
		
		String json = ObjectToJson.objectToJsonMapper(data);	
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		User user =  new User();
		UserDao userDao = new UserDaoImpl();
        user=(User)session.getAttribute("user"); 
		user=userDao.findUserbyId(user.getId());
		if(request.getParameter("title")!=null)			//add aggelia
		{
			Timestamp time = new Timestamp(System.currentTimeMillis());
			String title = request.getParameter("title");
			String text = request.getParameter("text");
			String skills = request.getParameter("skills");
			Aggelia ad = new Aggelia();
			ad.setNecessarySkills(skills);
			ad.setText(text);
			ad.setTitle(title);
			ad.setUser(user);
			ad.setPublishTime(time);
			userDao.createAggelia(ad);
			request.getRequestDispatcher("/aggelies.jsp").forward(request, response);
			return;
		}
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		if(data!=null)
		{
			if(data.get("advertisment_id")!=null)
			{
				int advertismentId = Integer.parseInt(data.get("advertisment_id").getAsString());
				Aggelia ad = new Aggelia();
	    		Timestamp time = new Timestamp(System.currentTimeMillis());
				ad = userDao.findAggeliaById(advertismentId);

				Application app = new Application();
				app.setAd(ad);
				app.setUser(user);
				app.setPending("TRUE");
				app.setPublishTime(time);
				user.getApplications().add(app);
				ad.getApplications().add(app);
				userDao.createApplication(app);
			}
			if(data.get("cancel_id")!=null)
			{
				int advertismentId = Integer.parseInt(data.get("cancel_id").getAsString());
				Aggelia ad = new Aggelia();
				ad = userDao.findAggeliaById(advertismentId);
				Application app = new Application();
				app = userDao.findApplicationByAdAndUser(ad, user);
				userDao.deleteApplication(app);
				user.getApplications().remove(app);
				ad.getApplications().remove(app);
				
			}
			if(data.get("delete_id")!=null)
			{
				int advertismentId = Integer.parseInt(data.get("delete_id").getAsString());
				int chosenUserId =  Integer.parseInt(data.get("chosen_user").getAsString());
				Aggelia ad = new Aggelia();
				User chosenUser = new User();
				
				chosenUser =userDao.findUserbyId(chosenUserId);
				ad = userDao.findAggeliaById(advertismentId);
				Application app = new Application();
				app = userDao.findApplicationByAdAndUser(ad, chosenUser);
				app.setPending("FALSE");
			}			
		}
	}

}
