package com.example.jiang.sysc3110project;

import java.util.Set;

/**
 * Social Network Distance strategy 
 * (Only direct friends)
 * 
 * @author  Mohamed Dahrouj, Lava Tahir, Ali Farah, Zening Jiang
 * @version 3.0
 */
public class SocialNetworkDistanceStrategy extends Strategy {
	
	/* Overrides the ranking Algorithm strategy with the distance implementation (non-Javadoc)
	 * @see sysc3110_project.Strategy#rankAlgo(java.util.Set, int, java.util.Set)
	 */
	public Set<Document> rankAlgo(Set<Document> documentSet, int k, Set<Document> topDocuments) {
		
		Set<User> userFollowing = user.getFollowing();
		int i = 0;
		if(userFollowing.isEmpty()){
			return defaultStrategy(documentSet,k,topDocuments);
		}
		else{
			for(User user : userFollowing){
				Set<Document> likedDocuments = user.getLikedDocuments();
				for(Document doc : likedDocuments){
					if(i<k){
						if(doc.hasProducer())
						{
							Producer producer = (Producer) doc.getUploader();
							System.out.println("Producer " + producer.getName() + " Document:" + producer.getDocument());
							producer.increasePayoff();
						}
						topDocuments.add(doc);
						i++;
					}
				}
			}
			return topDocuments;
		}
	}
	
	/* Overrides toString (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "Social Network Distance";
	}
}
