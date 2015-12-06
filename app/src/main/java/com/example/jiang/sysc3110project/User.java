package com.example.jiang.sysc3110project;

import java.io.Serializable;
import java.util.*;

/**
 * This class is part of the File Sharing Network Project.   
 * 
 * This User abstract class contains information pertaining to a user.
 * 
 * Super class for producers and consurmers.
 * 
 * @author  Mohamed Dahrouj, Lava Tahir, Ali Farah, Zening Jiang
 * @version 3.0
 */

public abstract class User implements Serializable {
	
	protected String name;
	protected String taste;
	protected Set<Document> likedDocuments;
	protected Set<Document> topDocuments;
	protected Set<User> following;
	protected Set<User> followers;
	protected int payoff;
	protected ArrayList<Integer> payoffs;
	protected Strategy strategy;
	
    /**
     * Initialize and create a new User given name and taste
     * @param name The consumers name
     * @param taste The consumers taste
     */
	public User(String name, String taste){
		this.name = name;
		this.taste = taste;
		likedDocuments = new HashSet<Document>();
		topDocuments = new HashSet<Document>();
		following = new HashSet<User>();
		followers = new HashSet<User>();
		payoff = 0;
		payoffs = new ArrayList<>();
	}
	
	/**
	 * @return The list of payoffs
	 */
	public ArrayList<Integer> getPayoffs(){
		return payoffs;
	}
	
	
	/**
	 * @return The single payoff of a user
	 */
	public int getPayoff(){
		return payoff;
	}
	
	/**
	 * Adds to the users payoff list
	 */
	public void addPayoff(){
		payoffs.add(payoff);	
	}
	
	/**
	 * Sets the users name
	 * @param name The users name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Sets the users taste
	 * @param taste The users taste
	 */
	public void setTaste(String taste){
		this.taste = taste;
	}
	
    /**
     * Adds a follower to a set of followers
     * @param newFollower The follwer to be added
     */
	public void addFollower(User newFollower){
		followers.add(newFollower);    
	}
	
    /**
     * Follow a user
     * @param newFollow The user to be follwed
     */
	public void follow(User newFollow){
		following.add(newFollow);
		newFollow.addFollower(this);
	}
	
    /**
     * Adds a document to the set of liked documents
     * @param newDoc Newly liked document
     */
	public void addLikedDocuments(Document newDoc ){
		likedDocuments.add(newDoc);
	}
	
    /**
     * Adds a document to the set of top documents
     * @param newDoc New top document
     */
	public void addTopDocuments(Document newDoc ){
		topDocuments.add(newDoc);
	}
	
    /**
     * Gets the set of followers
     * @return followers The set of followers of the User
     */
	public HashSet<User> getFollowers(){
		return (HashSet<User>) followers;
	}
	
	/**
     * Gets the set of following users
     * @return followers The set of followers of the User
     */
	public HashSet<User> getFollowing(){
		return (HashSet<User>) following;
	}
	
	/**
	 * Sets the strategy of the user
	 * @param s The strategy
	 */
	public void setStrategy(Strategy s){
		this.strategy = s;
	}
	
	/**
	 * @return The users strategy
	 */
	public Strategy getStrategy(){
		return strategy;
	}
	
    /**
     * Prints the set of followers
     * @return Returns set of followers as a string representation
     */
	public String printFollowers(){
		String s = "";
		for(User u: followers){
			s+=u.getName() + " ";
		}
		
		return s + "\n";
	}
	
    /**
     * Prints the set of users followed
     * @return Returns set of followed users as a string representation
     */
	public String printFollows(){
		String s = "";
		for(User u: following){
			s+=u.getName() + " ";
		}
		return s + "\n";
	}
	/**
     * Returns the set of liked documents
     * @return Returns set of liked documents as a Set representation
     */
	public Set<Document> getLikedDocuments(){
		return likedDocuments;
	}
	
    /**
     * Prints the set of liked documents
     * @return Returns set of liked documents as a string representation
     */
	public String printLikedDocuments(){
		String s = "";
		for(Document d: likedDocuments){
			s+=d.toString() + "\n";
		}
		return s;
	}
	
	/**
     * Returns the set of top documents
     * @return Returns set of top documents as a Set representation
     */
	public Set<Document> getTopKDocuments(){
		return topDocuments;
	}
	
    /**
     * Prints the set of "Top k" documents
     * @return Returns set of top documents as a string representation
     */
	public String printTopDocuments(){
		String s = "";
		for(Document d: topDocuments){
			s+= ">" + d.toString() + "\n";
		}
		return s;
	}
	
    /**
     * Gets the name of the user
     * @return name Name of the user
     */
	public String getName(){
		return name;
	}
	
    /**
     * Gets the taste of the user
     * @return taste Taste of the user
     */
	public String getTaste(){
		return taste;
	}
	
    /**
     * Prints the results when simulated
     * @return The string representation of the user results
     */
	public String printResults(){
		String str = "";
		str += this.getName() +"\nUser Status: " + printUserStatus() + "\nTaste: " + getTaste() + "\nLiked Documents:\n" + this.printLikedDocuments()
				+ "Payoff: " + payoff + "\nPayoffs: " + payoffs + "\nFollowing: "+"\nFollowing: " + this.printFollows()
				+ "Followers: " + this.printFollowers()  + "Strategy: " + strategy.toString();
		
		return str;
	}
	
    /**
     * Prints the status of a user i.e. Producer or consumer
     * @return The users status
     */	
	public String printUserStatus(){
		
		if (this instanceof Producer){
			return "Producer";
		}
		return "Consumer";
	}
	
    /**
     * Prints the name of user
     * @return The users name
     */
	public String toString(){
		if (this instanceof Producer){
			return "Producer: "+ name;
		}
		return "Consumer: "+ name;
	}
	
    /**
     * Each user implements invoking their search strategies in their classes
     * @param documentSet Set of documents for users to act upon
     * @param k The top documents of a search
     */
	public abstract void act(Set<Document> documentSet, int k);
}