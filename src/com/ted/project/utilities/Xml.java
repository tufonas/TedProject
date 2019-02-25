package com.ted.project.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.ted.project.dao.UserDao;
import com.ted.project.dao.UserDaoImpl;
import com.ted.project.entities.Friend;
import com.ted.project.entities.User;
import com.ted.project.entities.Users;
import com.ted.project.entities.XmlClass;

public class Xml {

	public Xml() {
		// TODO Auto-generated constructor stub
	}
	static Users Users = new Users();
	public static void XmlCreate(String[] users)
	{
		Users.setUsers(new ArrayList<XmlClass>());
		List<String> usersList = Arrays.asList(users);
		UserDao userdao = new UserDaoImpl();
        List<User> user = new ArrayList<User>();
        
		user=userdao.findUserbyEmails(usersList); 

		for (int i = 0; i < user.size(); i++)   
		{
			XmlClass xmlclass= new XmlClass(); 
			xmlclass.setUserToXmlclass(user.get(i)); 
			List<Friend> friends = new ArrayList<Friend>(); 
			for(int j = 0; j < user.get(i).getFriend2().size(); j++) {
				
				Friend friend = new Friend();
				friend.setUserToFriend(user.get(i).getFriend2().get(j));
				friends.add(friend);
			}
			xmlclass.setFriends(friends);
			System.out.println(xmlclass.getId());
			Users.getUsers().add(xmlclass);
			System.out.println("---"+Users.getUsers().get(i).getId());
		}
		for (int i = 0; i < Users.getUsers().size(); i++) 
		{
			System.out.println(Users.getUsers().get(i).getId()); 
		}


        try {
            File file = new File("H:\\TedProject\\UserData.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(Users, file);
            jaxbMarshaller.marshal(Users, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
	}

}
