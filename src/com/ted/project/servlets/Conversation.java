package com.ted.project.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.sun.jmx.snmp.Timestamp;
import com.ted.project.dao.UserDao;
import com.ted.project.dao.UserDaoImpl;
import com.ted.project.entities.Message;
import com.ted.project.entities.User;
import com.ted.project.utilities.ObjectToJson;
import com.ted.project.entities.Conversations;
import com.ted.project.entities.Friend;
import com.ted.project.entities.LoadMessages;

/**
 * Servlet implementation class Conversation
 */
@WebServlet("/Conversation")
public class Conversation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Conversation() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UserDao userDao = new UserDaoImpl();
		HttpSession session=request.getSession();  
		User user = new User();
		user = (User) session.getAttribute("user");
		user=userDao.findUserbyId(user.getId());
		if(request.getParameter("refresh")!=null ) //otan o xrhsths pataei aristera stis sunomilies.tou epistefei thn katallhlh sunomilia me ta munhmata//
		{
			
			int friendId = Integer.parseInt(request.getParameter("refresh")); 
			System.out.print("Name= "+ friendId +"User= "+user.getId()+"\n\n");
			User friend = new User();
			friend=userDao.findUserbyId(friendId);
			List<Conversations> user_friend_conversation = new ArrayList<Conversations>();
			user_friend_conversation=userDao.findConversationsByUserFriendId(user, friend);
			List<Message> messages = new ArrayList<Message>();
			if(!user_friend_conversation.isEmpty())
			{
				messages=userDao.findMessagesByConversation(user_friend_conversation.get(0));
			}
			LoadMessages loadmsg=new LoadMessages();
			loadmsg.setMessages(messages);

			String json = ObjectToJson.objectToJsonMapper(loadmsg);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			
		}
		else if(request.getParameter("friend")!=null)		//edw erxetai apo to profile 'send message'//
		{
			int friendId = Integer.parseInt(request.getParameter("friend")); 
			System.out.println("friend="+friendId);
			User friend = userDao.findUserbyId(friendId);
			List<Conversations> user_friend_conversation = new ArrayList<Conversations>();
			user_friend_conversation=userDao.findConversationsByUserFriendId(user, friend);
			List<Message> messages = new ArrayList<Message>();
			if(!user_friend_conversation.isEmpty())
			{
				messages=userDao.findMessagesByConversation(user_friend_conversation.get(0));
			}
			else   //den exoun ksanamilhsei .ginetai prwth fora h sunomilia//
			{
				Conversations new_conv = new Conversations();
				new_conv.setUser1(user);
				new_conv.setUser2(friend);   
				userDao.createConversation(new_conv);
				user.getConversation2().add(new_conv);  
			}
			List<Conversations> conversation = new ArrayList<Conversations>();
			conversation=userDao.findConversationsById(user);  //sunomilies tou sundedemenou xrhsth//
			List<User> conversations_users = new ArrayList<User>();
			for(int i = 0 ; i < conversation.size(); i++)
			{
				if(conversation.get(i).getUser1().getId()==user.getId())
				{
					if(conversation.get(i).getUser2().getId()==friend.getId())
					{
						conversations_users.add(0, friend);
					}
					else
					{
						conversations_users.add(conversation.get(i).getUser2());
					}
				}
				else
				{
					if(conversation.get(i).getUser1().getId()==friend.getId())
					{
						conversations_users.add(0, friend);
					}
					else
					{
						conversations_users.add(conversation.get(i).getUser1());
					}
				}
			}
			LoadMessages loadmsg=new LoadMessages();
			loadmsg.setUser(conversations_users);
			loadmsg.setMessages(messages);
			String json = ObjectToJson.objectToJsonMapper(loadmsg); 
			System.out.print(json);
			response.getWriter().write(json);
		}
		else			//load page//
		{
			List<Conversations> single_conversation =new ArrayList<Conversations>();
			single_conversation=userDao.findLastConversationOfUser(user);			//vriskoume thn teleutaia sunomilia tou sundedemenou xrhsth//
			User friend = new User();
			if(!single_conversation.isEmpty())
				if(single_conversation.get(0).getUser1().getId()==user.getId()){			//vriskoume poios einai o sunomiliths tou user//
					friend=single_conversation.get(0).getUser2();
				}
				else {
					friend = single_conversation.get(0).getUser1();
				}
			List<Message> messages = new ArrayList<Message>();
			if(!single_conversation.isEmpty())
			{
				messages=userDao.findMessagesByConversation(single_conversation.get(0));
			}
			List<Conversations> conversation = new ArrayList<Conversations>();
			conversation=userDao.findConversationsById(user);  //sunomilies tou sundedemenou xrhsth//
			List<User> conversations_users = new ArrayList<User>();
			
			for(int i = 0 ; i < conversation.size(); i++)		//pame na ftiaksoume thn seira me thn opoia tha emfanistoun oi sunomilies aristera tou parathirou//
			{
				if(conversation.get(i).getUser1().getId()==user.getId())
				{
					if(conversation.get(i).getUser2().getId()==friend.getId())  
					{
						conversations_users.add(0, friend);
					}
					else
					{
						conversations_users.add(conversation.get(i).getUser2());
					}
				}
				else
				{
					if(conversation.get(i).getUser1().getId()==friend.getId())
					{
						conversations_users.add(0, friend);
					}
					else
					{
						conversations_users.add(conversation.get(i).getUser1());
					}
				}
			}
			LoadMessages loadmsg=new LoadMessages();
			loadmsg.setUser(conversations_users);
			loadmsg.setMessages(messages);
			String json = ObjectToJson.objectToJsonMapper(loadmsg); 
			response.getWriter().write(json);			
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserDao userDao = new UserDaoImpl();
		HttpSession session=request.getSession();  
		User user = new User();
		user = (User) session.getAttribute("user");
		user=userDao.findUserbyId(user.getId());
		
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		if(data!=null)																	//sumbit message
		{
			String msg = data.get("msg").getAsString();
			int friendId = Integer.parseInt(data.get("friend").getAsString()); 
			List<Conversations> conversations = new ArrayList<Conversations>();
			User friend = new User();
			friend=userDao.findUserbyId(friendId);
			conversations=userDao.findConversationsByUserFriendId(user, friend);
			Message message = new Message();
			Date date = new Date(System.currentTimeMillis());
			
			Time time =new Time(System.currentTimeMillis());
			message.setMessageTime(time);
			
			message.setMessageDate(date);
			message.setMessage(msg);
			message.setUser(user);
			message.setConversation(conversations.get(0));
			userDao.createMessage(message);
			List<Message> messages = new ArrayList<Message>();
			messages=userDao.findMessagesByConversation(conversations.get(0));
			LoadMessages loadmsg=new LoadMessages();
			loadmsg.setMessages(messages);
			String json = ObjectToJson.objectToJsonMapper(loadmsg); 
			response.getWriter().write(json);
		}
	}

}
