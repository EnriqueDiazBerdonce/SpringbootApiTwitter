package com.apiTwitter.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.apiTwitter.model.Tweet;

public interface TweetRepository extends CrudRepository<Tweet, Long>{

	@Query("select t from Tweet t where t.validation = 'true'")
    public List<Tweet> getValidateTweets();
}
