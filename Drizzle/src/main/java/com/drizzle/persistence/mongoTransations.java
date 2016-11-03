package com.drizzle.persistence;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.helpers.ISO8601DateFormat;
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
		//operation.find(,Publication.class);
		//operation = null;
	}
	
	public static List ConsultarPublicationes() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date fecha = new Date();
		fecha.setHours(0);
		fecha.setMinutes(0);
		fecha.setSeconds(0);
		Date date = new Date(fecha.getTime()-18000000);
		String dateAsString = sdf.format(fecha); //"08.01.2013"
		//Date dateFromString = sdf.parse(dateAsString);
		System.out.println("fecha del query"+fecha);
		//System.out.println(dateAsString);
		//Date ayer = new Date( fecha.getTime()-86400000);
		List<Publication> q1s = operation.find(
		new Query((Criteria.where("date").gte(fecha))),
		Publication.class);
		//System.out.println(fecha.toString());
		return q1s;
	}

	public void borrarPublication(Publication publication) {
		operation.remove(publication);
	}



}
