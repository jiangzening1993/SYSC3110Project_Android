package com.example.jiang.sysc3110project;

import java.util.Set;

/**
 * Shared likes Similarity Strategy 
 * @author  Mohamed Dahrouj, Lava Tahir, Ali Farah, Zening Jiang
 * @version 3.0
 *
 */
public class LikeSimilarityStrategy extends Strategy{
	private Simulation simulate;
	public LikeSimilarityStrategy(Simulation s){
		this.simulate = s;
	}
	
	/* Ranking algorithm implementation override for top k documents (non-Javadoc)
	 * @see sysc3110_project.Strategy#rankAlgo(java.util.Set, int, java.util.Set)
	 */
	public Set<Document> rankAlgo(Set<Document> documentSet, int k, Set<Document> topDocuments) {
		if(user.getLikedDocuments().isEmpty())
			return defaultStrategy(documentSet, k, topDocuments);
		Set<Document> likedDocs = user.getLikedDocuments();
		int i = 0;
		for(User u: simulate.getUsers()){
			for(Document d: likedDocs){
				if(u.getLikedDocuments().contains(d)){
					for(Document doc:u.getLikedDocuments())
					{
						if(i<k && !doc.equals(d)){
							topDocuments.add(doc);
							i++;
						}
					}
				}
			}
		}
		return topDocuments;
	}

	/* toString override(non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "Like Similarity";
	}
}
