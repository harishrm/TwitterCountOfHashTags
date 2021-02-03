package com.tech.moojo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class TwitterConfig {

	  @Value("${spring.social.twitter.appId}")
	    private String consumerKey;

	    @Value("${spring.social.twitter.appSecret}")
	    private String consumerSecret;

	    @Value("${twitter.access.token}")
	    private String accessToken;

	    @Value("${twitter.access.token.secret}")
	    private String accessTokenSecret;
	    
	    public ConfigurationBuilder getConfiguration() {
	     
	        ConfigurationBuilder cb = new ConfigurationBuilder();
	        cb.setDebugEnabled(true)
	        .setOAuthConsumerKey(consumerKey)
	        .setOAuthConsumerSecret(consumerSecret)
	        .setOAuthAccessToken(accessToken)
	        .setOAuthAccessTokenSecret(accessTokenSecret);
	        return cb;
	    }
}
