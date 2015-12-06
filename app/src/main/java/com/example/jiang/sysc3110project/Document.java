package com.example.jiang.sysc3110project;

import java.io.Serializable;
import java.util.*;

/**
 * This class is part of the File Sharing Network Project.   
 * 
 * This Document class creates instances of documents for the Network
 * 
 * @author  Mohamed Dahrouj, Lava Tahir, Ali Farah, Zening Jiang
 * @version 3.0
 */

@SuppressWarnings("rawtypes")
public class Document implements Comparable, Serializable{
	
	private String name;
	private String tag;
	private Set<User> userList;
	private int likes;
	private Producer uploader;
	// private static final long serialVersionUID = 6529685098267757690L;
	
    /**
     * Create a document given document name, taste and up-loader
     * @param name The Documents name
     * @param taste The Documents taste
     * @param uploader The Documents up-loader
     */
	public Document(String name, String taste, Producer uploader){
		this.name= name;
		this.tag = taste;
		this.uploader = uploader;
		userList = new HashSet<User>();
		likes = 0;
	}

	
	/**
	 * Constructor
	 * @param name The name
	 * @param taste The taste
	 */
	public Document(String name, String taste){
		this.name= name;
		this.tag = taste;
		userList = new HashSet<User>();
		likes = 0;
	}
    /**
     * Gets a list of Users
     * @return userList The User List
     */
	public HashSet<User> getUsers(){
		return (HashSet<User>) userList;
	}
	
    /**
     * Gets the up-loader
     * @return uploader The up-loader
     */
	public Producer getUploader(){
		return uploader;
	}
	
    /**
     * Adds a user to the list of users
     * @param newUser New user to be added
     */
	public void addUser(User newUser){
		if(!userList.contains(newUser))
		{
			userList.add(newUser);
			if (getUploader()!=null)
				this.getUploader().increasePayoff();
		}
	}
	
    /**
     * Gets the number of likes 
     * @return likes The amount of likes
     */
	public int getLikes(){
		likes = userList.size();
		return likes;
	}
	
	
	/**
	 * Gets the amount of followers from the userList
	 * @return userFollowerSize Amount of user followers
	 */
	public int getUserLikes()
	{
		int userFollowerSize =0;
		for(User user: userList)
		{
			userFollowerSize += user.getFollowers().size();
		}
		return userFollowerSize;
	}
	public String getName(){return name;}
    /**
     * Gets the Tag of the document
     * @return tag The tag
     */
	public String getTag(){
		return tag;
	}
	/**
	 * 
	 * @return True if there is a producer else return false
	 */
	public boolean hasProducer(){
		return uploader!= null;
	}
    /**
     * Converts name, tag, and likes to a string for simulation 
     * Returns string representation
     */
	public String toString(){
		return "Document Name: " + name + " | Tag: " + tag + " | Likes: " + getLikes();
	}
	
    /**
     * Implementation to compare and downcast documents
     * Returns -1 if objects likes are greater than the current documents
     */
	public int compareTo(Object o) {
		Document doc = (Document) o;
		if(this.getLikes() < doc.getLikes()){
			return -1;
		}
		
		return 1;
	}
}