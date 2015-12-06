package com.example.jiang.sysc3110project;

import java.io.Serializable;
import java.util.*;

/**
 * This interface is part of the File Sharing Network Project.   
 * 
 * This Strategy interface creates instances of the documents for the Network
 * 
 * @author  Mohamed Dahrouj, Lava Tahir, Ali Farah, Zening Jiang
 * @version 3.0
 */

public abstract class Strategy implements Serializable{
	protected User user;
	
	/**
	 * Adds the user for whom the strategy will be implemented for
	 * @param user The user
	 */
	public void addUser(User user){
		this.user= user;
	}
	
    /**
     * Ranking algorithm strategy
     * @param documentSet Set of documents 
     * @param k The top documents of a search
     * @return Returns the set of ranked documents
     */
	public abstract Set<Document> rankAlgo(Set<Document> documentSet, int k, Set<Document> topDocuments);
	
	/**
     * Implementation of LFPopAlgo from Strategies interface
     * Likes or follows other users based on popularity
     * @param documentSet Set of documents 
     * @param me The consumer
     */
	public void LFPopAlgo(Set<Document> documentSet, User u){
		for(Document d : documentSet){
			if(d.getTag().equals(u.getTaste())){
				d.addUser(u);
				if(!u.getFollowing().contains(d.getUploader()) && d.getUploader() != null &&!d.getUploader().equals(u))
				{
					u.follow(d.getUploader());
					d.getUploader().increasePayoff();
				}
				u.addLikedDocuments(d);
				for(User user: d.getUsers()){
					
					
					if(!user.equals(u))
					{
						if(user instanceof Producer && !u.getFollowing().contains(user))
						{
							Producer producer = (Producer) user;
							System.out.println("Increase Payoff for producer "+ producer.getName());
							producer.increasePayoff();
							
						}
						u.follow(user);
						
					}
				}
			}
		}
	}
	
	/**
	 * The default Top K document searching strategy
	 * @param documentSet The document set
	 * @param k Top K number
	 * @param topDocuments The top K documents 
	 * @return The top K documents
	 */
	@SuppressWarnings("unchecked")
	public Set<Document> defaultStrategy(Set<Document> documentSet, int k,Set<Document> topDocuments) {
		ArrayList<Document> documentlist = new ArrayList<Document>(documentSet);	
		Collections.sort(documentlist);
		Collections.reverse(documentlist);
		topDocuments.clear();
		for(int i = 0; i < k; i++){
			Document document = documentlist.get(i);
			if(document.hasProducer())
			{
				Producer producer = (Producer) document.getUploader();
				System.out.println("Producer " + producer.getName() + " Document:" + producer.getDocument());
				producer.increasePayoff();
			}
			topDocuments.add(document);
			
		}	
		return topDocuments;
	}
	

}
