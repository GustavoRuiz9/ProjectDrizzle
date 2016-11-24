package com.drizzle.persistence;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.helpers.ISO8601DateFormat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.drizzle.persistence.mongoConfig;
import com.drizzle.model.Publication;
import com.drizzle.model.Ubication;

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
	
	public static List ConsultarPublicationes(String comuna) {
		System.out.println("COMUNA " + comuna);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Publication> q1s = null;
		
		Date fecha = new Date();
		fecha.setHours(0);
		fecha.setMinutes(0);
		fecha.setSeconds(0);
		Date date = new Date(fecha.getTime()-18000000);
		String dateAsString = sdf.format(fecha); //"08.01.2013"
		//Date dateFromString = sdf.parse(dateAsString);
		System.out.println("entro al consultar publicaciones"+fecha);
		//System.out.println(dateAsString);
		//Date ayer = new Date( fecha.getTime()-86400000);
		
		if(comuna==null){ //cuando se logue y comuna esta vacia! --machetazo
			q1s = operation.find((new Query((Criteria.where("date").gte(fecha))).with(new Sort(Sort.Direction.DESC, "date"))),Publication.class);
		}else{
		
			if(Integer.parseInt(comuna)==0 || Integer.parseInt(comuna)==-1){
				System.out.println("Entro con comuna " + comuna);
				q1s = operation.find((new Query((Criteria.where("date").gte(fecha))).with(new Sort(Sort.Direction.DESC, "date"))),Publication.class);
				
				if(Integer.parseInt(comuna)==-1){
					q1s = operation.find((new Query((Criteria.where("date").gte(fecha))).with(new Sort(Sort.Direction.ASC, "date"))),Publication.class);
				}
				
			}else{	
				
				q1s = operation.find(new Query(
						
						Criteria.where("date").gte(fecha).andOperator(Criteria.where("Id_Barrio").regex("^"+comuna+"([^ ][^ ])$")		

						)).with(new Sort(Sort.Direction.DESC, "date")),Publication.class);
			}
		}	

		System.out.println(q1s.size());
		return q1s;
	}
	public static List ActualizarPublic(int Id) {
		List<Publication> list;
		Date fecha = new Date();
		fecha.setHours(0);
		fecha.setMinutes(0);
		fecha.setSeconds(0);
		list =operation.find(new Query(
				Criteria.where("date").gte(fecha).andOperator(Criteria.where("id_publication").gt(Id)		
				)).with(new Sort(Sort.Direction.ASC, "date")),Publication.class);
		System.out.println("tamaño lista de actualizar : " + list.size());
		return list;
	}

	public void borrarPublication(Publication publication) {
		operation.remove(publication);
	}
	
	public static List ConsultarPosition(String Barrio1, String Barrio2) {
		System.out.println("entro "+Barrio1+" "+Barrio2);
		//registrarUbication();
		List<Ubication> list = prepareOperation().find(
				new Query((Criteria.where("bar").is(Barrio1))),
				Ubication.class);
		
		if(list.isEmpty()){
			System.out.println("Entro al empty");
			list= operation.find(
					new Query((Criteria.where("bar").is(Barrio2))),Ubication.class);
		}
		
		return list;
	}

	public static void registrarUbication() {
		Ubication ub = new Ubication();
		ub.setId_(10111);
		ub.setBar("Terron");
		ub.setComuna(1);
		ub.setPunto_cardinal("Occidente");
		 prepareOperation().save(ub);
		//operation.find(,Publication.class);
		//operation = null;
	}


}
