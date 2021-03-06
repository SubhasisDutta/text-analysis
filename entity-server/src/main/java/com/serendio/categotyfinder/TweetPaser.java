package com.serendio.categotyfinder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.diskoverorta.entities.BaseEntity;
import com.diskoverorta.entities.OrganizationEntity;
import com.diskoverorta.entities.PersonEntity;

public class TweetPaser {
	
	public static final String NO_KEY_FOUND_IN_TWEET = "No Keys present in Tweet. Cannot be classified.";
	/*public Map<String,Map<String,List<String>>> tweetsCategoriser(List<String> tweets){
		Map<String,Map<String,List<String>>> result = new HashMap<String, Map<String,List<String>>>();
		for(String tweet: tweets){
			result.put(tweet, tweetCategories(tweet));
		}
		return result;
	}*/
	
	public TweetCategoryModel tweetClassifier(String tweet){
		Set<String> categories = new HashSet<String>();
		Map<String,List<String>> dbCategories = tweetCategories(tweet);
		MapDBPediaKeys mapDB= new MapDBPediaKeys();
		if(dbCategories.isEmpty()){
			categories.add(NO_KEY_FOUND_IN_TWEET);			
		}else{
			for (Map.Entry<String, List<String>> entry : dbCategories.entrySet()) {				
				categories.addAll(mapDB.getMapedCategory(entry.getKey(), entry.getValue()));
			}
		}
		TweetCategoryModel tm = new TweetCategoryModel();
		tm.setCategories(categories);
		tm.setDbPediaCategory(dbCategories);
		return tm;
	}
	
	public Map<String,List<String>> tweetCategories(String tweet){
		BaseEntity ex = new PersonEntity();
		List<String> keyWords = ex.getEntities(tweet);
		ex = new OrganizationEntity();
		List<String> organisations = ex.getEntities(tweet);
		keyWords.addAll(organisations);
		Map<String,List<String>> dbCategories= new HashMap<String, List<String>>();
		for(String keyWord:keyWords){
			dbCategories.put(keyWord, getExtractedCategory(keyWord));
		}
		return dbCategories;
	}
	
	public TweetCategoryModel tweetClassifier(String tweet,String packageChoice){
		Set<String> categories = new HashSet<String>();
		Map<String,List<String>> dbCategories = tweetCategories(tweet,packageChoice);
		MapDBPediaKeys mapDB= new MapDBPediaKeys();
		if(dbCategories.isEmpty()){
			categories.add(NO_KEY_FOUND_IN_TWEET);			
		}else{
			for (Map.Entry<String, List<String>> entry : dbCategories.entrySet()) {				
				categories.addAll(mapDB.getMapedCategory(entry.getKey(), entry.getValue()));
			}
		}
		TweetCategoryModel tm = new TweetCategoryModel();
		tm.setCategories(categories);
		tm.setDbPediaCategory(dbCategories);
		return tm;
	}
	
	public Map<String,List<String>> tweetCategories(String tweet,String packageChoice){
		PersonEntity ex = new PersonEntity();
		List<String> keyWords = ex.getEntities(tweet,packageChoice);
		OrganizationEntity ex1 = new OrganizationEntity();
		List<String> organisations = ex1.getEntities(tweet,packageChoice);
		keyWords.addAll(organisations);
		Map<String,List<String>> dbCategories= new HashMap<String, List<String>>();
		for(String keyWord:keyWords){
			dbCategories.put(keyWord, getExtractedCategory(keyWord));
		}
		return dbCategories;
	}
	
	private List<String> getExtractedCategory(String keyword){
		List<String> categories = null;
		//TODO: first search in a database if keyword alrady present no need of getting from external source
		
		//Get data from external source
		DBPediaParser dbPediaParser=new DBPediaParser();
		categories=dbPediaParser.getCategoriesFromDBPedia(keyword);
		
		//TODO:save data from external source for future reference
		
		
		return categories;
	}
	
	
}
