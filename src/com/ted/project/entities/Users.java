package com.ted.project.entities;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlElementsJoinNodes;
import org.eclipse.persistence.oxm.annotations.XmlJoinNode;
import org.eclipse.persistence.oxm.annotations.XmlJoinNodes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public class Users {

	public Users() {
		users = new ArrayList<XmlClass>();
	}
	
    @XmlElement(name = "user", type = XmlClass.class)
    private List<XmlClass> users;
     
    public List<XmlClass> getUsers() {
        return users;
    }
 
    public void setUsers(List<XmlClass> users) {
        this.users = users;
    }  
}
