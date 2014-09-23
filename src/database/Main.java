package database;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;



public class Main {
	public static final String TWEET = "Hai blaa";
	public String getTweet() {
		return TWEET;
		
	}
	
	public static void main(String[] args) {
		
		MainLogic dbConnection = new MainLogic();
		//dbConnection.insertCounter(compignid, count);
		String[] posCodes = {"hack","coders","hackers","laughter","techie"};
		int[][] sentCounts = new int[5][3];
		for(int i=0;i<5;i++){
			sentCounts[i][0] = 0;  //neu
			sentCounts[i][1] = 0;  //neg
			sentCounts[i][2] = 0;  //pos
		}
		Tweet[] tweets = null;
		try {
		   SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		   TwitterManager twitterManager = new TwitterManager();
		   tweets = twitterManager.performQuery("seqhack");
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Tweet sentiTweet : tweets){
			if(sentiTweet == null) {
				continue;
			}
			String sent = sentiTweet.senti;
			int polarity = 0;
			if(sent != null){
				if("pos".compareTo(sent)==0) {
					polarity = 2;
				}
				else if("neg".compareTo(sent)==0){ 
					polarity = 1;
				}
				else if("neu".compareTo(sent)==0) {
					polarity = 0;
				}
			}
			
			String tweetContent = sentiTweet.tweet;
			if(tweetContent != null)
				{
				tweetContent = tweetContent.toLowerCase();
				String[] words = tweetContent.split(" ");
				for(String word : words){
					for(int j=0; j <5;j++){
						if(posCodes[j].compareTo(word)== 1){
							sentCounts[j][polarity]++;
						}
					}
				}
				}
			
			try{
			for(int j=0;j<5;j++)
				dbConnection.insertCounter(posCodes[j], sentCounts[j]);
			if(sentiTweet.tweet != null){
				dbConnection.insertTweets(sentiTweet.tweet, polarity, "", "");	
			}
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
		}
		
		
	}


}
