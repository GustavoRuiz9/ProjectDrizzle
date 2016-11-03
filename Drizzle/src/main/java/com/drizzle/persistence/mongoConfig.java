package com.drizzle.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
public class mongoConfig {

	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		//return new SimpleMongoDbFactory(new MongoClient("node19762-drizzle.j.facilcloud.com"), "Drizzle_Social");
		return new SimpleMongoDbFactory(new MongoClient(), "Drizzle_Social");
	}

	public @Bean
	MongoTemplate mongoTemplate() throws Exception {

		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

		return mongoTemplate;

	}

}
