package com.ted.project.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

//import org.apache.velocity.runtime.directive.Parse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mysql.jdbc.ProfilerEventHandlerFactory;
import com.ted.project.dao.UserDao;
import com.ted.project.dao.UserDaoImpl;
import com.ted.project.entities.Experience;
import com.ted.project.entities.Profession;
import com.ted.project.entities.ProfileData;
import com.ted.project.entities.User;
import com.ted.project.utilities.ObjectToJson;
import com.ted.project.utilities.UploadUtil;

/**
 * Servlet implementation class PersonalData
 */
@WebServlet("/PersonalData")
public class PersonalData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDaoImpl();
		HttpSession session=request.getSession();  
		User friend_user = new User();
		User curr_user = new User();
		curr_user = (User) session.getAttribute("user");				//load page//
		ProfileData data = new ProfileData();
		
		
		if(request.getParameter("friend")!=null && request.getParameter("admin")!=null)		//admin//
		{
			System.out.println("friend_profile1");
			int userId =  Integer.parseInt(request.getParameter("friend"));
			friend_user = userDao.findUserbyId(userId);
			data.setProfile_user(friend_user);
			data.setFriends(friend_user.getFriend2());
			data.setType_of_user("admin");
		}
		else if(request.getParameter("friend")!=null)		//other user//
		{
			System.out.println("friend_profile");
			int userId =  Integer.parseInt(request.getParameter("friend"));
			friend_user = userDao.findUserbyId(userId);
			data.setFriends(friend_user.getFriend2());
			data.setProfile_user(friend_user);
			data.setLogged_user(curr_user);
			data.setType_of_user("user");
		}
		else		//current user//
		{
			curr_user=userDao.findUserbyId(curr_user.getId());
			data.setFriends(curr_user.getFriend2());
			data.setProfile_user(curr_user);
			data.setLogged_user(curr_user);
			data.setType_of_user("user");
		}
		
		String json = ObjectToJson.objectToJsonMapper(data);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
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
		System.out.println(request.getParameter("edit_profession")+request.getParameter("edit_education"));
		Part photo;
		if(request.getParameter("edit_profession")!=null)   //save job//
		{
			System.out.println("edittt");
			String position = request.getParameter("edit_profession"); 
			String tomeas = request.getParameter("edit_tomeas");
			String duties = request.getParameter("edit_duties");
			String business_name = request.getParameter("edit_business_name");  
			String achievements = request.getParameter("edit_achievements");
			String education = request.getParameter("edit_education");
			String education_visibility = request.getParameter("edit_choice");
			String skills = request.getParameter("edit_skills");
			String skills_visibility = request.getParameter("edit_choice1");
			
			String position_choice = request.getParameter("choice7");
			String tomeas_choice = request.getParameter("choice8");
			String duties_choice = request.getParameter("choice10");
			String business_name_choice = request.getParameter("choice9");
			String achievements_choice = request.getParameter("choice11");
			
			Profession prof = new Profession();
			if(request.getParameter("profid")!="")
			{
				int prof_id = Integer.parseInt(request.getParameter("profid")); 
				prof= userDao.findProfessionById(prof_id);
			}
			  
			Experience experience = new Experience();
			experience = user.getExperience();
			experience.setEducation(education);
			experience.setSkills(skills);
			experience.setEducation_visibility(education_visibility);
			experience.setSkills_visibility(skills_visibility);
			
			
			prof.setAchievements(achievements);
			prof.setBusiness_name(business_name);
			prof.setDuties(duties);  
			prof.setTomeas(tomeas);
			prof.setPosition(position);  
			prof.setUser(user);
			prof.setAchievements_visibility(achievements_choice);
			prof.setBusiness_name_visibility(business_name_choice);
			prof.setDuties_visibility(duties_choice);
			prof.setTomeas_visibility(tomeas_choice);
			prof.setPosition_visibility(position_choice);			
			
			userDao.updatePersonalData(user,experience ,prof);
			request.getRequestDispatcher("personalData.jsp").forward(request, response);
		}
		else if(request.getParameter("home_edit_skills")!=null || request.getParameter("home_edit_education")!=null)
		{
			
			String education = request.getParameter("home_edit_education");
			String education_visibility = request.getParameter("edit_choice");
			String skills = request.getParameter("home_edit_skills");
			String skills_visibility = request.getParameter("edit_choice1");
			
			if(user.getExperience()==null)
			{
				System.out.print("if");
				Experience exp = new Experience();
				exp.setEducation(education);
				exp.setSkills(skills);
				exp.setSkills_visibility(skills_visibility);
				exp.setUser(user);
				exp.setEducation_visibility(education_visibility);
				user.setExperience(exp);
				userDao.createExperience(exp);
			}
			else
			{
				System.out.print("else");
				user.getExperience().setEducation(education);
				user.getExperience().setEducation_visibility(education_visibility);
				user.getExperience().setSkills(skills);
				user.getExperience().setSkills_visibility(skills_visibility);
				userDao.updatePersonalData(user,user.getExperience() ,null);
			}
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		else if(request.getParameter("profession")!=null)   //save job//
		{
			String position = request.getParameter("profession");
			String tomeas = request.getParameter("tomeas");
			String duties = request.getParameter("duties");
			String business_name = request.getParameter("business_name");
			String achievements = request.getParameter("achievements");
			
			String position_choice = request.getParameter("choice");
			String tomeas_choice = request.getParameter("choice1");
			String duties_choice = request.getParameter("choice3");
			String business_name_choice = request.getParameter("choice2");
			String achievements_choice = request.getParameter("choice4");
			 
			Experience exp = new Experience();
			Profession prof = new Profession();
			
			prof.setAchievements(achievements);
			prof.setBusiness_name(business_name);
			prof.setDuties(duties);
			prof.setPosition(position);
			prof.setTomeas(tomeas);
			prof.setUser(user);
			prof.setAchievements_visibility(achievements_choice);
			prof.setBusiness_name_visibility(business_name_choice);
			prof.setDuties_visibility(duties_choice);
			prof.setTomeas_visibility(tomeas_choice);
			prof.setPosition_visibility(position_choice);
			
			if(user.getExperience()==null)
			{
				exp.setUser(user);
				exp.getExperience_jobs().add(prof);
				userDao.createExperience(exp);
				
				user.getJobs().add(prof);
				user.setExperience(exp);
				prof.setExperience(exp);
			}
			else  
			{
				user.getExperience().getExperience_jobs().add(prof);
				prof.setExperience(user.getExperience());
				user.getJobs().add(prof);  
			}
			userDao.createProfession(prof);
			request.getRequestDispatcher("personalData.jsp").forward(request, response);
			
			
		}
		else if(request.getParameter("education")!=null)
		{
			String education = request.getParameter("education");
			String education_choice = request.getParameter("choice5");
			if(user.getExperience()==null)
			{
				Experience exp = new Experience();
				exp.setEducation(education);
				exp.setUser(user);
				exp.setEducation_visibility(education_choice);
				user.setExperience(exp);
				userDao.createExperience(exp);
			}
			else
			{
				user.getExperience().setEducation(education);
				user.getExperience().setEducation_visibility(education_choice);
			}
			request.getRequestDispatcher("personalData.jsp").forward(request, response);
		}
		else if(request.getParameter("skills")!=null)
		{
			String skills = request.getParameter("skills");
			String skills_choice = request.getParameter("choice6");			
			if(user.getExperience()==null)
			{
				Experience exp = new Experience();
				exp.setSkills(skills);
				exp.setUser(user);
				exp.setSkills_visibility(skills_choice);
				user.setExperience(exp);
				userDao.createExperience(exp);
			}
			else
			{
				user.getExperience().setSkills(skills);
				user.getExperience().setSkills_visibility(skills_choice);
			}
			request.getRequestDispatcher("personalData.jsp").forward(request, response);
		}
	}

}
