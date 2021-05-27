package com.apiTwitter.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.apiTwitter.model.Hashtag;
import com.apiTwitter.model.Tweet;
import com.apiTwitter.service.HashtagService;
import com.apiTwitter.service.TweetService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/twitter")
public class TweetRestController {
	
	    @Autowired
		TweetService tweetService;
	    @Autowired
		HashtagService hasgtagService;
		

	    @ApiOperation(value = "Servicio que usamos para cargar Tweets"
	            ,notes = "Veremos todos los tweets pero bajo un criterio específico según "
	            		+ "lenguaje y número de followers, solo algunos tweets se guardarán en base de datos")
		@GetMapping(path = "/tweets", produces = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<Tweet> getTweet() {	
			
			return tweetService.getTweets();
			
		}
	    
	    @ApiOperation(value = "Servicio para marcar un Tweet como validado según id")
		@GetMapping(path = "/validateTweetbyId", produces = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Tweet validateTweetbyId(@RequestParam(name = "idTweet") Long id) {	
			
			return tweetService.validateTweetbyId(id);
			
		}
	    
	    @ApiOperation(value = "Servicio para consultar todos los Tweets validados")
		@GetMapping(path = "/getValidateTweets", produces = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<Tweet> getValidateTweets() {	
			
			return tweetService.getValidateTweets();
			
		}
		
		@ApiOperation(value = "Servicio para mostrar una lista de los 10 Hashtags mas usados."
	            ,notes = "Se mostrarán una lista top 10 Hashtags mas usados, teniendo en cuenta "
	            		+ "que los hashtags consultados son los que se han guardado previamente junto "
	            		+ "a los tweets según el criterio especificado, de esta manera se controlarán "
	            		+ "que la suma de los Hashtags son reales y no se repiten según cuantas veces haces la consulta y llames al servicio de getTweets  ")
		@GetMapping(path = "/top10hashtags", produces = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<Hashtag> getTop10Hashtag() {	
			
			return hasgtagService.getTop10Hashtag();
			
		}
}
