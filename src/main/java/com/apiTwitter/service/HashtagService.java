package com.apiTwitter.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.apiTwitter.model.Hashtag;
import com.apiTwitter.repository.HashtagRepository;


@Service
public class HashtagService {
	
	@Autowired
	HashtagRepository hashtagRepository;
	
	
    public Hashtag searchHashtagName(@Param("name") String name) {
		return hashtagRepository.searchHashtagName(name);
	}
    
    public List<Hashtag> getTop10Hashtag() {
		return hashtagRepository.getTop10Hashtag();
	}
	

}
