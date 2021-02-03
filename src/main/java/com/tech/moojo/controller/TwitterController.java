package com.tech.moojo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.moojo.config.TwitterConfig;
import com.tech.moojo.response.SuccessResponse;

import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@RestController
@RequestMapping("/api/v1")
public class TwitterController {

	public static final String TWITTER_BASE_URI="tweets";
	

	@Autowired
	TwitterConfig config;
	@GetMapping("/gettop10tweets")
	public ResponseEntity<?>getTweets(){
		try {
			int idTrendLocation = 0;
		 TwitterFactory tf = new TwitterFactory(config.getConfiguration().build());
	        Twitter twitter = tf.getInstance();
	        ResponseList<Location> locations;
	        locations = twitter.getAvailableTrends();
	        String locationName="India";
	        for (Location location : locations) {
	            if (location.getName().toLowerCase().equals(locationName.toLowerCase())) {
	                idTrendLocation = location.getWoeid();
	                break;
	            }
	        }
	        List<String> toptenHashtags=new ArrayList<String>();
	        Trends trends = twitter.getPlaceTrends(idTrendLocation);
	        System.out.println(trends.getTrends().length); 
	        for (int i = 0; i < 10; i++) {
	           
	            toptenHashtags.add(trends.getTrends()[i].getName());
	            }
	      
	        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("true",toptenHashtags,"Top 10 Hashtags"));
		} catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to get trends: " + te.getMessage());
	        System.exit(-1);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SuccessResponse("false", "", "Failed to login "+te.getErrorMessage())); 
	    }		
	}
}
