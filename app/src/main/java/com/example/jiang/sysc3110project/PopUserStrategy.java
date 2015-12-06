package com.example.jiang.sysc3110project;

import java.util.ArrayList;
import java.util.Set;

/**
 * User Popularity Strategy 
 * @author  Mohamed Dahrouj, Lava Tahir, Ali Farah, Zening Jiang
 * @version 3.0
 *
 */
public class PopUserStrategy extends Strategy{

	/* Ranking algorithm implementation override for top k documents (non-Javadoc)
	 * @see sysc3110_project.Strategy#rankAlgo(java.util.Set, int, java.util.Set)
	 */
	public Set<Document> rankAlgo(Set<Document> documentSet, int k,Set<Document> topDocuments) {
		ArrayList<Document> documentlist = sortDocuments(documentSet);
		
		topDocuments.clear();
		for(int i = 0; i < k; i++){
			Document document = documentlist.get(i);
			if(document.hasProducer())
			{
				Producer producer = (Producer) document.getUploader();
				
				producer.increasePayoff();
			}
			topDocuments.add(document);
			
		}	
		return topDocuments;
	}
		

	/**Sorts a document list using bubble sort algorithm
	 * @param documents The documents to be sorted
	 * @return documentlist The sorted document list
	 */
	private ArrayList<Document> sortDocuments(Set<Document> documents){
		ArrayList<Document> documentlist = new ArrayList<Document>(documents);
		for(int i =0 ; i< documentlist.size()-1; i++)
		{
			for(int j =0; j< documentlist.size()-i-1; j++)
			{
				if(documentlist.get(j).getUserLikes() < documentlist.get(j+1).getUserLikes() )
				{
					Document temp = documentlist.get(j);
					documentlist.set(j,  documentlist.get(j+1));
					documentlist.set(j+1, temp);
				}
			}
		}
		return documentlist;
		
	}
	
	
	/* toString override (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "User Popularity";
	}

}
