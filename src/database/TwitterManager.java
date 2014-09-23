package database;

import java.io.IOException;
import java.util.ArrayList;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class TwitterManager {
	 
	SentimentClassifier sentClassifier;
	int LIMIT= 1000; //the number of retrieved tweets
	ConfigurationBuilder cb;
	Twitter twitter;


	public TwitterManager() {
		cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey("xxx");
		cb.setOAuthConsumerSecret("xx");
		cb.setOAuthAccessToken("xxx");
		cb.setOAuthAccessTokenSecret("xxx");
		twitter = new TwitterFactory(cb.build()).getInstance();
		sentClassifier = new SentimentClassifier();
	}

	public Tweet[] performQuery(String inQuery) throws InterruptedException, IOException {
		Query query = new Query(inQuery);
		query.setCount(100);
		Tweet[] tweetSentiments = new Tweet[500];
		int[] sentCounts = new int[3];
		for(int i=0;i<3;i++) sentCounts[i] = 0;
		try {
			int count=0;
			QueryResult r;
			do {
				r = twitter.search(query);
				ArrayList ts= (ArrayList) r.getTweets();

				for (int i = 0; i < ts.size() && count < LIMIT; i++) {
					count++;
					Status t = (Status) ts.get(i);
					String text = t.getText();
					//System.out.println("Text: " + text);
					String name = t.getUser().getScreenName();
					//System.out.println("User: " + name);
					
					Dictionary dic = new Dictionary();
					String tweet = "";//t.getText();
					String[] wordsInTweet = t.getText().split(" ");
					//System.out.println(wordsInTweet.length);
					for(String word : wordsInTweet)
					{  
						String meaning = Dictionary.meaningOfWord(word);
												
						if(meaning == null) 
						{
							tweet += word + " ";
						}
						
						else
						{
							tweet += meaning + " ";
						}
						 
						
					}
					//System.out.println(tweet);
					String sent = sentClassifier.classify(tweet);
					//System.out.println("Sentiment: " + sent); 
					tweetSentiments[i] = new Tweet(tweet, sent);
					/*
					if(sent.compareTo("pos")==0) sentCounts[0]++;
					else if(sent.compareTo("neg")==0) sentCounts[1]++;
					else if(sent.compareTo("neu")==0) sentCounts[2]++;
					*/
				}   
			} while ((query = r.nextQuery()) != null && count < LIMIT);
		}
		
		catch (TwitterException te) {
			System.out.println("Couldn't connect: " + te);
		}
		return tweetSentiments;
	}
	
	
	/*
	public static void main(String args[]){

		train newTrain = new train();
		try {
			newTrain.train();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		SentimentClassifier sentiClassifier = new SentimentClassifier();
		System.out.println(sentiClassifier.classify("i am girl"));
		
		
		TwitterManager twitterManager = new TwitterManager();
		try {
			int[] sentCounts = twitterManager.performQuery("seqhack");
			System.out.println("Positive count :"+sentCounts[0]);
			System.out.println("Negative count :"+sentCounts[1]);
			System.out.println("Neutral count :"+sentCounts[2]);
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
*/


}