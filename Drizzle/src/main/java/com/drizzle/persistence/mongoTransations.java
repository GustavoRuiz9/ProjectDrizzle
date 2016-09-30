package com.drizzle.persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.drizzle.persistence.mongoConfig;
import com.drizzle.model.Publication;

public class mongoTransations {

	static MongoOperations operation = prepareOperation();
	
	public static MongoOperations prepareOperation() {

		// For Annotation
		ApplicationContext ctx = new AnnotationConfigApplicationContext(mongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		
		return mongoOperation;
	}
	
	public static MongoOperations closeOperation() {

		// For Annotation
		ApplicationContext ctx = new AnnotationConfigApplicationContext(mongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		
		return mongoOperation;
	}

	public static void registrarPublication(Publication new_publication) {
		operation.save(new_publication);
		//operation = null;
	}

	public void borrarPublication(Publication publication) {
		operation.remove(publication);
	}



}
