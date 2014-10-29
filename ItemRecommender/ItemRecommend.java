package com.predictions.itemRecommend;

//import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
//import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
//import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
//import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class ItemRecommend 
{

	public static void main(String[] args) throws IOException
	{
		//BufferedWriter bw = new BufferedWriter(new FileWriter("data/recosTC.txt"));
		
		try 
		{
			DataModel dm = new FileDataModel(new File("data/conv.csv"));
			
			ItemSimilarity sim = new LogLikelihoodSimilarity(dm);	
	//		UserSimilarity sim = new PearsonCorrelationSimilarity(dm);
		//	TanimotoCoefficientSimilarity sim = new TanimotoCoefficientSimilarity(dm);
			
			//GenericItemBasedRecommender reccommender = new GenericItemBasedRecommender(dm, sim);
			GenericItemBasedRecommender reccommender = new GenericItemBasedRecommender(dm, sim);
			
			
			for(LongPrimitiveIterator items = dm.getItemIDs(); items.hasNext();)
			{
				long itemId = items.nextLong();
				List<RecommendedItem>reccomendations = reccommender.mostSimilarItems(itemId, 5);
			
			for(RecommendedItem recco : reccomendations)
			{
				System.out.println(itemId + "," + recco.getItemID() + "," + recco.getValue());
				//bw.write(itemId + "," + recco.getItemID() + "," + recco.getValue() + "\n");
			}
			
		}
			//bw.close();
		}
		catch (IOException e) 
		{
			System.out.println("There was an error !");
			e.printStackTrace();
		} catch (TasteException e) {
			System.out.println("There was a Taste error !");
			e.printStackTrace();
		}

	}

}
