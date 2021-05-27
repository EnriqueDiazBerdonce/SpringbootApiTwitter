package com.apiTwitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apiTwitter.model.Hashtag;


public interface HashtagRepository extends CrudRepository<Hashtag, Long>{

	
	@Query("select h from Hashtag h where h.name like :name")
    public Hashtag searchHashtagName(@Param("name") String name);
	
	@Query("select h from Hashtag h ORDER BY h.count Desc")
    public List<Hashtag> getTop10Hashtag();
}
