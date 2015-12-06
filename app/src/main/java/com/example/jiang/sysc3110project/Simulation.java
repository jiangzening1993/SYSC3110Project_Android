package com.example.jiang.sysc3110project;

import java.io.*;
import java.util.*;

/**
 * This class is part of the File Sharing Network Project.   
 * 
 * This main Simulation class creates a simulation of the Network
 * and populates it with users and documents
 * 
 * @author  Mohamed Dahrouj, Lava Tahir, Ali Farah, Zening Jiang
 * @version 1.2
 */

public class Simulation implements Serializable {
	
	//private int rounds;
	private ArrayList<User> users;
	private Set<Document> documents;
	//Arbitrary k value
	private int k;
	private Stack<byte[]> prev;
	private int simRound;

	
    /**
     * Initializes the simulation
     * @param k Number of top K documents
     */
	public Simulation(int k){
		this.k = k;
		simRound =0;
		users = new ArrayList<>();
		documents= new HashSet<>();
		prev = new Stack<>();
	
	}

   
	
	/**
     * Adds a document to the list of documents
     * @param doc The document to be added to the document list
     */
	public void addDocument(Document doc){
		documents.add(doc);
	}
	
    /**
     * Gets the list of documents
     * @return documents The list of documents
     */
	public Set<Document> getDocuments(){
		return documents;
	}
		
	
    /**
     * Gets the list of users
     * @return users The list of users
     */
	public ArrayList<User> getUsers(){
		return users;
	}
	
    /**
     * Removes a user from the list of users
     * @param user The user to be removed from the user list
     */
	public void removeUser(User user){
		users.remove(user);
	}
	
    /**
     * Removes a document from the list of documents
     * @param doc The document to be removed from the document list
     */
	public void removeDocument(Document doc){
		documents.remove(doc);
	}
	
    /**
     * Adds a user to the list of users
     * @param user The user to be added
     */
	public void addUser(User user){
		if(user instanceof Producer){
			documents.add(((Producer) user).getDocument());	
		}
		users.add(user);
	}
	
    /**
     * The simulator which randomly acts with a set of users and documents
     * @return hmap Returns a hash map
     */
	public void simulate(){
	
		
		add(this);
		int userNum = users.size();
		while(userNum>0){
			int rn = (int)(Math.random()*(users.size()));
			User user = users.get(rn);
			user.act(documents, k);
			System.out.println(user.getName());
			//user.printResults();
			userNum--;
			
			for(Document d: documents)
			{
				//System.out.print(d+ "\n");
			}
			
		}
		
		for(User u : users){
			u.addPayoff();
		
			//System.out.print(u.printResults());
		}
		simRound++;
		
	}
	public int getSimRounds(){
		return simRound;
	}
	public String toString()
	{
		String s= "";
		for(User u : users){
			
		
			s+= u.printResults();
		}
		return s;
	}
	public void add(Simulation s)
	{
		try {
		     ByteArrayOutputStream bo = new ByteArrayOutputStream();
		     ObjectOutputStream out = new ObjectOutputStream(bo);
		     out.writeObject(s);
		      prev.push(bo.toByteArray());
		      bo.close();
		      out.flush();
		      out.close();
		 } catch (Exception e) {
		     System.out.println(e + "error1");
		 }

		
	}
	public Simulation undo(){
		if(!prev.isEmpty())
		{
			try {
			  
			     ByteArrayInputStream bi = new ByteArrayInputStream( prev.pop());
			     ObjectInputStream in = new ObjectInputStream(bi);
			     Simulation sim = (Simulation) in.readObject();
			     
			     return sim;
			 } catch (Exception e) {
			
			     e.printStackTrace();
			 }
			
		}
		return null;
	}
	public void export(String s){
		try {
			FileOutputStream outstream = new FileOutputStream(s);
			ObjectOutputStream o = new ObjectOutputStream(outstream);
			o.writeObject(this);
			o.flush();
			o.close();
			outstream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Simulation readSimulation(String s){
		
		try {
			FileInputStream instream = new FileInputStream(s);
			ObjectInputStream i = new ObjectInputStream(instream);
			Simulation sim = (Simulation) i.readObject();
			//System.out.println(a.printbook());
			i.close();
			instream.close();
			return sim;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}