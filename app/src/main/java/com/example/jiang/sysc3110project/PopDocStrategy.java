package com.example.jiang.sysc3110project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

/**
 * Document Popularity Strategy 
 * @author  Mohamed Dahrouj, Lava Tahir, Ali Farah, Zening Jiang
 * @version 3.0
 *
 */
public class PopDocStrategy extends Strategy {
	
	/* Ranking algorithm implementation override for top k documents (non-Javadoc)
	 * @see sysc3110_project.Strategy#rankAlgo(java.util.Set, int, java.util.Set)
	 */
	@SuppressWarnings("unchecked")
	public Set<Document> rankAlgo(Set<Document> documentSet, int k, Set<Document> topDocuments) {
		ArrayList<Document> documentlist = new ArrayList<Document>(documentSet);	
		Collections.sort(documentlist);
		Collections.reverse(documentlist);
		topDocuments.clear();
		
		for(int i = 0; i < k; i++){
			Document document = documentlist.get(i);
			if(document.hasProducer()){
				Producer producer = (Producer) document.getUploader();
				
				producer.increasePayoff();
			}
			topDocuments.add(document);
		}	
		return topDocuments;
	}

	/* Override the toString method (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "Document Popularity";
	}
}
