package com.predictions.itemRecommend;

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

import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;
import java.io.*;
import java.util.*;
 
class RecommenderWithoutPreference {
 
  private RecommenderWithoutPreference() {
  }
 
  public static void main(String[] args) throws Exception {
 
    DataModel model = new FileDataModel(new File("data//myData.csv"));  //load data from file needed for computation
    UserSimilarity similarity = new TanimotoCoefficientSimilarity(model); //log likelihood similarity will be used for making recommendation .
/*To use TanimotoCoefficientSimilarity replace “LogLikelihoodSimilarity” with TanimotoCoefficientSimilarity”.
UserSimilarity implementation provides how similar two two users are using LoglikehoodSimilarity */
    UserNeighborhood neighborhood =  new NearestNUserNeighborhood(2, similarity, model);  //Define a group of user most similar to a given user . 2 define a group of 2 user having most similar preference
 
    Recommender recommender = new GenericUserBasedRecommender(  model, neighborhood, similarity); // creates a recommendation engine
 
    List<RecommendedItem>recommendations =   recommender.recommend(4, 1);
/*one recommendation for user with ID 4 . In Mahout it always take Integer value i.e It will always take userId and number of item to be recommended */
 
    for (RecommendedItem recommendation : recommendations) {
      System.out.println(recommendation);
    }
 
  }
 
}

//- See more at: http://www.oodlestechnologies.com/blogs/Mahout-Recommendation-Engine-Without-Using-Preferences#sthash.hP9pk3Gn.dpuf
