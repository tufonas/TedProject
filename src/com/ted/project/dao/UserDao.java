package com.ted.project.dao;

import java.util.List;

import com.ted.project.entities.Aggelia;
import com.ted.project.entities.Application;
import com.ted.project.entities.Article;
import com.ted.project.entities.Comment;
import com.ted.project.entities.Conversations;
import com.ted.project.entities.Experience;
import com.ted.project.entities.Message;
import com.ted.project.entities.Profession;
import com.ted.project.entities.User;
import com.ted.project.entities.UserHasLiked;

public interface UserDao {
	
	public User find(int id);
	
	public List<User> findAll();
	
	public List<User> findProfessionals();
	
	public void createArticle(Article person);
	
	public void createUser(User person);
	
	public void createComment(Comment person);
	
	public void createProfession(Profession person);
	
	public void createExperience(Experience person);
	
	public boolean checkVerify(String code,String email);
	
	public void updateVerify(String verify,String email);
	
	public void createAggelia(Aggelia person);
	
	public List<User> findEmail_Password(String email,String password); 
	
	public List<User> findUserbyEmails(List<String> id); 
	
	public List<User> findUserByEmail(String email);
	
	public String FindLastId();
	
	public User findUserbyId(int id);
	
	public List<User> findUserbyName(String name,int id); 
	
	public List<User> findUserbySurname(String surname,int id);
	
	public List<User> findUserbyNameSurname(String name,String surname,int id);
	
	public void createMessage(Message message);
	
	public void createConversation(Conversations convarsation);
	
	public List<Conversations> findConversationsById(User id);
	
	public List<Conversations> findConversationsByUserFriendId(User user,User friend);
	
	public List<Message> findMessagesByConversation(Conversations convarsation);
	
	public List<Conversations> findLastConversationOfUser(User user);
	
	public void deleteFriendRequest(User curr_user, User friend);
	
	public void updatePersonalData(User user,Experience exp ,Profession prof);
	
	public Profession findProfessionById(int id);
	
	public List<Article> findArticlesForHome(User curr_user,List<User> friends);
	
	public Article findArticleById(int article_id);
	
	public List<UserHasLiked> findLikesFromArticles(List<Article> articles);
	
	public UserHasLiked findLikeFromUserAndArticle(User user,Article article);
	
	public void removeLikeFromArticle(Article article,UserHasLiked like,User user);
	
	public List<UserHasLiked> findAllLikes();
	
	public List<Article> findArticlesOfUser(User user);
	
	public List<Comment> findCommentsFromArticles(List<Article> articles);
	
	public void UpdateUser(User user);
	
	public void UpdateArticle(Article article);
	
	public List<Aggelia> findAggeliesToPrint(User user,List<User> friends);
	
	public Aggelia findAggeliaById(int advertismentId);
	
	public void createApplication(Application app);
	
	public void deleteApplication(Application app);
	
	public Application findApplicationByAdAndUser(Aggelia ad,User user);
	
	public List<Aggelia> findAdvertismentsByApplications(User user);
	
	public List<User> findMyFriendRequest(User user);
	
	public void UpdateApplication(Application app);
	
	public List<Aggelia> findAllAds();
	
	public String findLastUserId();
	
	public void DeleteUser(User user);
}
