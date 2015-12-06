package com.example.jiang.sysc3110project;

import java.util.*;

/**
 * This class is part of the File Sharing Network Project.   
 * 
 * This Producer class extends User and takes an input of a name, taste and document.
 *
 * Once called, the producer can act by acquiring the top K documents and either 
 * like or follow them depending on taste and also create a new document.
 * 
 * @author  Mohamed Dahrouj, Lava Tahir, Ali Farah, Zening Jiang
 * @version 3.0
 */

public class Producer extends User {
	private Document doc;
	private String act;
    /**
     * Create a producer using User super class given name and taste
     * Initialize the producers newly created document
     * @param name The producers name
     * @param taste The producers taste
     * @param docName Document name
     */
	public Producer(String name, String taste, String docName){
		super(name, taste);
		doc = new Document(docName, this.taste, this);
		act = "Your Taste";
	}
	
	/**
	 * Increases the payoff of the producer
	 */
	public void increasePayoff(){
		payoff++;
	}
	
	/**Sets which act will be used (i.e. act based on producers taste OR random taste).
	 * @param act Act based on producers taste OR random taste
	 */
	public void setAct(String act){
		this.act = act;
	}
	
	/* Overide from the interface to select which act method to use (non-Javadoc)
	 * @see sysc3110_project.User#act(java.util.Set, int)
	 */
	public void act(Set<Document> documentSet, int k){
		if(act.equals("Your Taste"))
			act1(documentSet, k);
		else{
			act2(documentSet, k);
		}	
	}
	
    /**
     * Invokes strategies and updates payoff accordingly with original taste
     * @param documentSet Set of documents for users to act upon
     * @param k The top documents of a search
     */
	public void act1(Set<Document> documentSet, int k) {
		int size = likedDocuments.size();
		HashSet<Document> documents = (HashSet<Document>) strategy.rankAlgo(documentSet,k, topDocuments);
		strategy.LFPopAlgo(documents, this);
		payoff+= (likedDocuments.size() - size);

	}
	
	/**
     * Invokes strategies and updates payoff accordingly with random taste
     * Generates a random number and uses it to index and act based on a random taste
     * @param documentSet Set of documents for users to act upon
     * @param k The top documents of a search
	 */
	public void act2(Set<Document> documentSet, int k){
		Random randomGenerator = new Random();
		String origTaste = this.getTaste();
		ArrayList<String> otherTastes = new ArrayList<String>();
		for(Document d : documentSet){
			//Makes sure taste is not the same as original
			if(!d.getTag().equals(origTaste)){
				otherTastes.add(d.getTag());
			}
		}
		int index = randomGenerator.nextInt(otherTastes.size());
		String randTaste = otherTastes.get(index);
		this.setTaste(randTaste);
		this.act1(documentSet,k);
		this.setTaste(origTaste);
		
	}
	
    /**
     * Checks to see if the inputed object is an instance of Producer
     * @param o Object passed to be compared
     * @return True if the inputed object is an instance of Producer
     */
	public boolean equals(Object o){
		if(this == o) return true;
		if((o instanceof Producer)) {
			User u = (User) o;
			return (name.equals(u.name) && taste.equals(u.taste));
		}
		return false;
	} 
	
    /**
     * Gets the document uploaded
     * @return doc The uploaded document
     */
	public Document getDocument(){
		return doc;
	}
    /**
     * Prints the results when simulated
     * @return The string representation of the user results
     */
	public String printResults(){
		return super.printResults() + "\nAct: " + act;
	}
	

}