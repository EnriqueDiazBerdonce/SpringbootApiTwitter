package com.apiTwitter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.apiTwitter.model.Hashtag;
import com.apiTwitter.model.Tweet;
import com.apiTwitter.repository.HashtagRepository;
import com.apiTwitter.repository.TweetRepository;
import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class TweetService {
	
	@Autowired
	TweetRepository tweetRepository;
	@Autowired
	HashtagRepository hashtagRepository;
	
	@Value("${oauth.consumerKey}")
	String apiKey;
	@Value("${oauth.consumerSecretKey}")
	String apiSecretKey;
	@Value("${oauth.bearerToken}")
	String bearerToken;
	@Value("${oauth.accessToken}")
	String accessToken;
	@Value("${oauth.accessTokenSecret}")
	String accessTokenSecret;
	@Value("${nseguidores}")
	int nSeguidores;
	
	enum Languages{
	       es, it, fr;
	   }
	
	
	public List<Tweet> getTweets() {

		Twitter twitter = createConfigurationBuilder();
		List<Status> status = null;
		List<Tweet> tweets = new ArrayList<Tweet>();
		
		try {
			status = twitter.getHomeTimeline();

			for(Status s:status) {
				
				if(enumIterationLang(s.getLang()) && s.getUser().getFollowersCount() >= nSeguidores) {
					Tweet tweet = new Tweet(s.getUser().getName(),s.getText(),s.getUser().getLocation());

					tweets.add(tweet);
					try {
						tweetRepository.save(tweet);
						
						/*Se guardan los hashtags segun el criterio y guardado de los tweets 
							para poder controlar los hashtags y no guardarlos repetidos por 
							tweets repetidos cada vez que se haga la consulta getTweets*/
						hagshtagsIterationSave(s.getHashtagEntities());
						
				    } catch (Exception e) {
				    	//e.printStackTrace();
				    }
					
				}
				
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return tweets;
		
	}
	
	
	public Tweet validateTweetbyId(Long id) {
		Tweet res = null;
		Optional<Tweet> op = tweetRepository.findById(id);
		if(op.isPresent()) {
			res = op.get();
			res.setValidation(true);
			tweetRepository.save(res);
		}
		return res;
	}
	
	public List<Tweet> getValidateTweets() {
		List<Tweet> res = tweetRepository.getValidateTweets();
		return res;
	}
	
	private Twitter createConfigurationBuilder() {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey(apiKey)
		  .setOAuthConsumerSecret(apiSecretKey)
		  .setOAuthAccessToken(accessToken)
		  .setOAuthAccessTokenSecret(accessTokenSecret);
		TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
		return tf.getInstance();
	}

	private boolean enumIterationLang(String langIn) {
		   Languages[] langs = Languages.values();
		   boolean res = false;
	       for (Languages lang : langs) {
	           if(lang.toString().equals(langIn)) {
	        	   res=true;
	           }
	       }
	       return res;
	   }
	
	private void hagshtagsIterationSave(HashtagEntity[] hashtags) {

	       for (HashtagEntity hash : hashtags) {
	    	   Hashtag hashtag = hashtagRepository.searchHashtagName(hash.getText());
	           if(hashtag != null) {
	        	   Hashtag h1 = hashtagRepository.findById(hashtag.getId()).get();
	        	   h1.setCount(hashtag.getCount() + 1);
	        	   hashtagRepository.save(h1);
	           }else {
	        	   Hashtag h = new Hashtag();
	        	   h.setText(hash.getText());
	        	   h.setCount(1);
	        	   hashtagRepository.save(h);
	           }
	       }
	   }

}
