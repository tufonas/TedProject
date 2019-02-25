package com.ted.project.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ted.project.entities.Article;
import com.ted.project.entities.Comment;
import com.ted.project.entities.User;
import com.ted.project.entities.UserHasLiked;

public class KnnData {

	private HashMap<Integer, Integer> array;
	
	private User user;
	
	public KnnData() {
		array = new HashMap<Integer, Integer>();
		user = new User();
	}

	public HashMap<Integer, Integer> getArray() {
		return array;
	}

	public void setArray(HashMap<Integer, Integer> array) {
		this.array = array;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Integer TakeValue(User user,Article article,List<Article> articles)	//user einai o user pou psaxnoume na doume ti energeies exei kanei,article einai to arthro pou theloume na doume ti timh evale o user,
	{	
																			//kai articles einai ta arthra pou emfanizontai sthn arxikh selida tou user.an to article den einai mesa sta articles tote shmainei
		for(int i=0;i<articles.size(); i++)										// oti o user einai ksenos me ton sundedemeno mas xrhsth.ara tha prepei na mpei h timh 10
		{
			if(article.getArticleId() == articles.get(i).getArticleId())
			{
				List<Comment> comments = new ArrayList<Comment>();
				List<UserHasLiked> likes = new ArrayList<UserHasLiked>();
				comments = user.getComments();    
				likes = article.getUsers_liked();
				int comment_flag = 0,like_flag = 0;
				for(int j=0;j<comments.size(); j++)
				{
					if(comments.get(j).getArticle()!=null)
						if(comments.get(j).getArticle().getArticleId() == article.getArticleId())
						{
							comment_flag = 1;
						}
				}
				for(int j=0;j<likes.size(); j++)
				{
					if(likes.get(j).getUser().getId() == user.getId())
					{
						like_flag = 1;
					}
				}
				if(comment_flag == 0 && like_flag == 0)			//no like , no comment
				{
					return 0;
				}
				else if(comment_flag == 1 && like_flag == 0)		// comment ,no like
				{
					return 1;
				}
				else if(comment_flag == 0 && like_flag == 1)			//no comment ,like
				{	
					return 2;
				}	
				else if(comment_flag == 1 && like_flag == 1)		//comment,like
				{
					return 3;
				}
			}
		}
		return 10;
	}
}
