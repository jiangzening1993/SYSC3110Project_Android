package com.example.jiang.sysc3110project;

import java.util.*;

/**
 * This class is part of the File Sharing Network Project.   
 * 
 * This Consumer class extends User and takes an input of a name and taste.
 *
 * Once called, the consumer can act by acquiring the top K documents and either 
 * like or follow them depending on taste.
 * 
 * @author  Mohamed Dahrouj, Lava Tahir, Ali Farah, Zening Jiang
 * @version 3.0
 */

public class Consumer extends User{
	/**
     * Create a consumer using User super class given name and taste
     * @param name The consumers name
     * @param taste The consumers taste
     */
	public Consumer(String name, String taste){
		super(name, taste);

	}
	
    /**
     * Invokes strategies and updates payoff accordingly
     * @param documentSet Set of documents for users to act upon
     * @param k The top documents of a search
     */
	public void act(Set<Document> documentSet, int k) {
		int size = likedDocuments.size();
		HashSet<Document> documents = (HashSet<Document>) strategy.rankAlgo(documentSet, k, topDocuments);
		strategy.LFPopAlgo(documents, this);
		payoff+= (likedDocuments.size() - size);	//because likedDocuments is a set, and you cant add an existing document to the set
												//its fine to make the payoff equal to the size of the new set - old set
	}
	
    /**
     * Checks to see if the inputed object is an instance of Consumer
     * @param o Object passed to be compared
     * @return True if the inputed object is an instance of Consumer
     */
	public boolean equals(Object o){
		if(this == o) return true;
		if((o instanceof Consumer)) {
			User user = (User) o;
			return (name.equals(user.name) && taste.equals(user.taste));
		}
		return false;
	}
	

}