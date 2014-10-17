package com.kull.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.junit.Test;

import com.Console;

public class MahoutTest {

	//@Test
	public void recommender() throws Exception{
		
		DataModel model=new FileDataModel(new File("J:\\ws-sts\\kull-mvn\\kull-core\\src\\test\\resources\\datasource\\intro.csv"));
		UserSimilarity similarity=new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood=new NearestNUserNeighborhood(2, similarity, model);
		Recommender recommender=new GenericUserBasedRecommender(model, neighborhood, similarity);
		List<RecommendedItem> recommendedItems=recommender.recommend(3, 2);
		for(RecommendedItem recommendedItem :recommendedItems){
			System.out.print(recommendedItem);
		}
	}
}