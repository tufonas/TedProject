package com.ted.project.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ted.project.entities.Aggelia;
import com.ted.project.entities.Application;
import com.ted.project.entities.Article;
import com.ted.project.entities.Comment;
import com.ted.project.entities.Conversations;
import com.ted.project.entities.Experience;
import com.ted.project.entities.Friend;
import com.ted.project.entities.Message;
import com.ted.project.entities.Profession;
import com.ted.project.entities.User;
import com.ted.project.entities.UserHasLiked;
import com.ted.project.jpautils.EntityManagerHelper;

public class UserDaoImpl implements UserDao {

	private EntityManager em = EntityManagerHelper.getEntityManager();

	public EntityManager getEm() {
		return em;
	}

	@Override
	public User find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		Query query = em.createNamedQuery("User.findAll");
		return (List<User>) query.getResultList();
	}

	@Override
	public void createArticle(Article person) {
		// TODO Auto-generated method stub
		em.persist(person);
	}

	@Override
	public void createComment(Comment person) {
		// TODO Auto-generated method stub
		em.persist(person);
		
	}

	@Override
	public void createUser(User person) {
		// TODO Auto-generated method stub
		em.persist(person);
		
	}


	@Override
	public List<User> findEmail_Password(String email, String password) {
		Query query = em.createNamedQuery("User.findEmail_Password").setParameter("requested_email",email ).setParameter("requested_password", password);
		return (List<User>) query.getResultList();
	}

	@Override
	public List<User> findProfessionals() {
		Query query = em.createNamedQuery("User.findProfessionals");
		return (List<User>) query.getResultList();
	}

	@Override
	public List<User> findUserbyEmails(List<String> id) {
		Query query = em.createNamedQuery("User.findUserbyEmails").setParameter("requested_id", id );
		return (List<User>) query.getResultList();
	}

	@Override
	public List<User> findUserByEmail(String email) {
		Query query = em.createNamedQuery("User.findUserByEmail").setParameter("requested_email", email);
		return (List<User>) query.getResultList();
	}

	@Override
	public void createAggelia(Aggelia person) {
		em.persist(person);
		
	}

	@Override
	public void createProfession(Profession person) {
		em.persist(person);
		
	}

	@Override
	public void createExperience(Experience person) {
		em.persist(person);   
		
	}
	
	@Override
	public String FindLastId() {
		Query query = em.createNamedQuery("Article.findlastid");
		int id =(int) query.getSingleResult();
		String strid = Integer.toString(id);
		return strid;
	}

	@Override
	public boolean checkVerify(String code,String email) {
		Query query = em.createNamedQuery("User.checkVerify").setParameter("requested_email", email);
		String query_answer = (String) query.getSingleResult();
		if (query_answer.equals(code))
		{

			updateVerify("true", email);
			return true;			
		}
		else if(query_answer.equals("true"))
		{

			return true;
		}
		else
		{

			return false;
		}
	}

	@Override
	public void updateVerify(String verify, String email) {
		int query = em.createNamedQuery("User.updateVerify").setParameter("requested_email", email).setParameter("requested_verify", verify).executeUpdate();	
	}

	@Override
	public User findUserbyId(int id) {

		Query query = em.createNamedQuery("User.FindUserbyId").setParameter("requested_id", id);
		return (User)query.getSingleResult();
	}

	@Override
	public List<User> findUserbyName(String name,int  id) {
		Query query = em.createNamedQuery("User.FindUserbyName").setParameter("requested_name", name).setParameter("current_user", id);
		return query.getResultList();
	}

	@Override
	public List<User> findUserbySurname(String surname,int id) {
		Query query = em.createNamedQuery("User.FindUserbySurname").setParameter("requested_surname", surname).setParameter("current_user", id);
		return query.getResultList();
	}

	@Override
	public List<User> findUserbyNameSurname(String name, String surname,int id) {
		Query query = em.createNamedQuery("User.FindUserbyNameSurname").setParameter("requested_surname", surname).setParameter("requested_name", name).setParameter("current_user",id);
		return query.getResultList();
	}

	@Override
	public void createMessage(Message message) {
		// TODO Auto-generated method stub
		em.persist(message);
		
	}

	@Override
	public void createConversation(Conversations convarsation) {
		em.persist(convarsation);
		
	}

	@Override
	public List<Conversations> findConversationsById(User id) {
		Query query = em.createNamedQuery("Conversations.findConversationsById").setParameter("requested_id", id);
		return query.getResultList();
	}

	@Override
	public List<Message> findMessagesByConversation(Conversations conversation) {
		Query query = em.createNamedQuery("Message.findMessagesByConversation").setParameter("requested_conversation", conversation);  
		return query.getResultList();
	}

	@Override
	public List<Conversations> findConversationsByUserFriendId(User user, User friend) {
		Query query = em.createNamedQuery("Conversations.findConversationsByUserFriendId").setParameter("requested_user", user).setParameter("requested_friend", friend);
		return query.getResultList();
	}

	@Override
	public List<Conversations> findLastConversationOfUser(User user) {
		Query query = em.createNamedQuery("Conversations.findLastConversationOfUser").setParameter("requested_user", user);
		return  query.getResultList();
	}

	@Override
	public void deleteFriendRequest(User curr_user, User friend) {
		Query query = em.createNamedQuery("User.deleteFriendRequest").setParameter("requested_user", curr_user).setParameter("requested_friend", friend);
		
	}

	@Override
	public void updatePersonalData(User user, Experience exp,Profession prof) {
		
		em.merge(user);
		//Query query = em.createNamedQuery("Experience.updatePersonalData").setParameter("requested_skills", exp.getSkills()).setParameter("requested_education", exp.getEducation()).setParameter("requested_user", user);  
		//Query query1= em.createNamedQuery("Profession.updatePersonalData").setParameter("requested_position", prof.getPosition()).setParameter("requested_achievements", prof.getAchievements()).setParameter("requested_business_name", prof.getBusiness_name()).setParameter("requested_duties", prof.getDuties()).setParameter("requested_tomeas", prof.getTomeas()).setParameter("requested_user", user);  
		
	}

	@Override
	public Profession findProfessionById(int id) {  
		Query query = em.createNamedQuery("Profession.FindProfessionbyId").setParameter("requested_id", id);
		return (Profession)query.getSingleResult();
	}

	@Override
	public List<Article> findArticlesForHome(User curr_user, List<User> friends) {
		if(friends.isEmpty())
		{
			Query query = em.createNamedQuery("Article.findArticlesOfUser").setParameter("requested_user", curr_user);
			List<Article> articles = new ArrayList<Article>();
			articles = query.getResultList();
			articles.sort(Comparator.comparing(Article::getPublishTime));
			Collections.reverse(articles);
			return articles;
		}
		else
		{
			Query query = em.createNamedQuery("Article.findArticlesForHome").setParameter("requested_friends", friends);
			Query query1 = em.createNamedQuery("Article.findArticlesForHome1").setParameter("requested_friends", friends).setParameter("requested_user", curr_user);
			List<Article> articles = new ArrayList<Article>();
			List<Article> articles1 = new ArrayList<Article>();
			List<Article> articles2 = new ArrayList<Article>();
			articles = query.getResultList();
			articles1 = query1.getResultList();
			articles2.addAll(articles);

			
			articles2.addAll(articles1);
			
			articles2.sort(Comparator.comparing(Article::getPublishTime));
			Collections.reverse(articles2);
			return articles2;
		}

	}

	@Override
	public Article findArticleById(int article_id) {
		Query query = em.createNamedQuery("Article.findArticleById").setParameter("requested_id", article_id);
		return (Article)query.getSingleResult();
	}

	@Override
	public List<UserHasLiked> findLikesFromArticles(List<Article> articles) {
		Query query = em.createNamedQuery("UserHasLiked.findLikesFromArticles").setParameter("requested_articles", articles);
		return query.getResultList();
	}

	@Override
	public UserHasLiked findLikeFromUserAndArticle(User user, Article article) {
		Query query = em.createNamedQuery("UserHasLiked.findLikeFromUserAndArticle").setParameter("requested_article", article).setParameter("requested_user", user);
		return (UserHasLiked) query.getSingleResult();
	}

	@Override
	public void removeLikeFromArticle(Article article, UserHasLiked like, User user) {
		
		//user.removeLikeFromArticle(article, like);
		em.remove(like);
		
	}

	@Override
	public List<UserHasLiked> findAllLikes() {
		Query query = em.createNamedQuery("UserHasLiked.findAllLikes");
		return query.getResultList();
	}

	@Override
	public List<Article> findArticlesOfUser(User user) {
		Query query = em.createNamedQuery("Article.findArticlesOfUser").setParameter("requested_user", user);
		return query.getResultList();
	}

	@Override
	public List<Comment> findCommentsFromArticles(List<Article> articles) {
		Query query = em.createNamedQuery("Comment.findCommentsFromArticles").setParameter("requested_articles", articles);
		return query.getResultList();
	}

	@Override
	public void UpdateUser(User user) {
		em.merge(user);
		
	}

	@Override
	public void UpdateArticle(Article article) {
		em.merge(article);
		
	}

	@Override
	public List<Aggelia> findAggeliesToPrint(User user, List<User> friends) {
		Query query = em.createNamedQuery("Aggelia.findAggeliesToPrint").setParameter("requested_user", user).setParameter("requested_friends", friends);
		return query.getResultList();
	}

	@Override
	public Aggelia findAggeliaById(int advertismentId) {
		Query query = em.createNamedQuery("Aggelia.findAggeliaById").setParameter("requested_id", advertismentId);
		return (Aggelia)query.getSingleResult();
	}

	@Override
	public void createApplication(Application app) {
		em.persist(app);
		
	}

	@Override
	public void deleteApplication(Application app) {
		em.remove(app);
		
	}

	@Override
	public Application findApplicationByAdAndUser(Aggelia ad, User user) {
		Query query = em.createNamedQuery("Application.findApplicationByAdAndUser").setParameter("requested_user", user).setParameter("requested_ad", ad);
		return (Application)query.getSingleResult();
		
	}

	@Override
	public List<Aggelia> findAdvertismentsByApplications(User user) {
		Query query = em.createNamedQuery("Aggelia.findAdvertismentsByApplications").setParameter("requested_user", user);
		return query.getResultList();
	}

	@Override
	public List<User> findMyFriendRequest(User user) {
		Query query = em.createNamedQuery("User.findMyFriendRequest").setParameter("requested_user", user);
		return query.getResultList();
	}

	@Override
	public String findLastUserId() {
		Query query = em.createNamedQuery("User.findLastUserId");
		int id =(int) query.getSingleResult();
		String strid = Integer.toString(id);
		return strid;
	}

	@Override
	public void UpdateApplication(Application app) {
		
		em.merge(app);
		
	}

	@Override
	public List<Aggelia> findAllAds() {
		Query query = em.createNamedQuery("Aggelia.findAll");
		return query.getResultList();
	}

	@Override
	public void DeleteUser(User user) {
		
		UserDao userDao = new UserDaoImpl();
		List<Aggelia> aggelies = new ArrayList<Aggelia>();
		aggelies = user.getAggelies_published();
		List <Application> apps = new ArrayList<Application>();
		apps = user.getApplications();
		List<Article> articles = new ArrayList<Article>();
		articles = user.getArticles_published();
		List<Comment> comments = new ArrayList<Comment>();
		comments = user.getComments();
		
		
		List<Message> messages = new ArrayList<Message>();
		messages = user.getMessages();
		for(int i=0;i<messages.size();i++)
		{
			em.remove(messages.get(i));
		}
		Experience exp = new Experience();
		exp = user.getExperience();
		List<User> friends = new ArrayList<User>();

		for(int i=0;i<user.getFriend2().size();i++)
		{
			user.getFriend2().remove(user.getFriend2().get(i));
		}
		for(int i=0;i<user.getFriend1().size();i++)
		{
			user.getFriend1().remove(user.getFriend1().get(i));
		}
		List<User> friendRequests = new ArrayList<User>();   
		friendRequests = user.getFriendRequest();
		List<Profession> professions = new ArrayList<Profession>();
		professions = user.getJobs();
		
		for(int i=0;i<aggelies.size();i++)
		{ 
			em.remove(aggelies.get(i));
		}
		for(int i=0;i<apps.size();i++)
		{
			em.remove(apps.get(i));
		}
		for(int i=0;i<articles.size();i++)
		{
			em.remove(articles.get(i));
		}
		for(int i=0;i<comments.size();i++)
		{
			em.remove(comments.get(i));  
		}   
		if(exp!=null) 
			em.remove(exp);
		
		List<User> friend_requesters = new ArrayList<User>();
		friend_requesters = userDao.findMyFriendRequest(user);
		for(int i=0;i<friend_requesters.size();i++)
		{
			friend_requesters.get(i).getFriendRequest().remove(user); 
		} 
		for(int i=0;i<friendRequests.size();i++) 
		{
			friendRequests.remove(friendRequests.get(i)); 
		}
		for(int i=0;i<professions.size();i++)
		{
			em.remove(professions.get(i));
		}
		List<Conversations> conversations1 = new ArrayList<Conversations>();
		List<Conversations> conversations2 = new ArrayList<Conversations>();
		conversations1 = user.getConversation1();
		conversations2 = user.getConversation2();
		for(int i=0;i<conversations1.size();i++)
		{
			List<Message> msgs = new ArrayList<Message>();
			msgs = conversations1.get(i).getMessages(); 
			for(int j=0;j<msgs.size();j++)
			{
				em.remove(msgs.get(j));
			}
			em.remove(conversations1.get(i));
		}
		for(int i=0;i<conversations2.size();i++)  
		{
			List<Message> msgs = new ArrayList<Message>();
			msgs = conversations2.get(i).getMessages();
			for(int j=0;j<msgs.size();j++)
			{
				em.remove(msgs.get(j));
			}
			em.remove(conversations2.get(i));
		}
		em.remove(user);
		
	}



}
