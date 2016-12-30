package com.drizzle.persistence;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.helpers.ISO8601DateFormat;
import org.hibernate.Hibernate;
import org.hibernate.query.criteria.internal.expression.function.TrimFunction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.drizzle.persistence.mongoConfig;
import com.drizzle.model.Comment;
import com.drizzle.model.Estadistica;
import com.drizzle.model.Like;
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
	}
	
	//cambios
	public static void registrarCommentary(Comment new_comment) {
		operation.save(new_comment);		
	}
	
	public static void registrarLike(Like like) {
		operation.save(like);
		
		
	}
	public static void Eliminarlike(Like like) {
		operation.remove(new Query(	
		Criteria.where("id_publicacion").is(like.getId_publicacion()).andOperator(
				Criteria.where("author").is(like.getAuthor()).andOperator(
				Criteria.where("usuario").is(like.getUsuario()))
				)),Like.class);
	}
	public static List Consultarlike(Like like) {
		List<Like> Lik= null;
		
		Lik = operation.find(new Query(	
		Criteria.where("id_publicacion").is(like.getId_publicacion()).andOperator(
				Criteria.where("author").is(like.getAuthor()).andOperator(
				Criteria.where("usuario").is(like.getUsuario()))
				)),Like.class);
	return Lik;
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
				q1s = operation.find((new Query((Criteria.where("date").gte(fecha))).with(new Sort(Sort.Direction.ASC, "date"))),Publication.class);
				
				if(Integer.parseInt(comuna)==-1){
					q1s = operation.find((new Query((Criteria.where("date").gte(fecha))).with(new Sort(Sort.Direction.ASC, "date"))),Publication.class);
				}
				
			}else{	
				
				q1s = operation.find(new Query(
						
						Criteria.where("date").gte(fecha).andOperator(Criteria.where("Id_Barrio").regex("^"+comuna+"([^ ][^ ])$")		

						)).with(new Sort(Sort.Direction.ASC, "date")),Publication.class);
			}
		}	

		System.out.println(q1s.size());
		return q1s;
	}
	

		public static List<Publication> consultarPublicationesRecientes() {

			List<Publication> q1s = null;
			
					
					/*q1s = operation.find(new Query(
							Criteria.where("date").gte(fecha).where("photo").exists(true)).limit(4),Publication.class);*/
					
					q1s = operation.find(new Query(
									Criteria.where("photo").exists(true)
							).limit(6).with(new Sort(Sort.Direction.DESC, "date")),Publication.class);
			
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
	
	public static List<Estadistica> consultarEstadisticas(int comuna) {
		List<Estadistica> list;
		Date fecha = new Date();

		if(comuna != 0){
			//pulido
			list = operation.find(new Query(
					Criteria.where("fecha").is(trim(fecha)).andOperator(Criteria.where("comuna").is(comuna).
							orOperator(Criteria.where("storm").gt(0),Criteria.where("sunny").gt(0),Criteria.where("rain").gt(0),Criteria.where("tempered").gt(0))
			)).with(new Sort(Sort.Direction.ASC, "comuna")).with(new Sort(Sort.Direction.ASC,"tipo")),Estadistica.class);
		}else{
			//pulido
			list = operation.find(new Query(
					Criteria.where("fecha").is(trim(fecha)).
							orOperator(Criteria.where("storm").gt(0),Criteria.where("sunny").gt(0),Criteria.where("rain").gt(0),Criteria.where("tempered").gt(0))
							).with(new Sort(Sort.Direction.ASC, "comuna","tipo")),Estadistica.class);
					//Criteria.where("fecha").is(trim(fecha))),Estadistica.class); //prueba 
		}
		
		System.out.println("tamaño lista de actualizar : " + list.size());
		
		return list;
	}

	public static void borrarPublication(Publication publication) {
		operation.remove(new Query(Criteria.where("id_publication").is(publication.getId_publication())), Publication.class);
	}
	
	//pulido
	public static void borrarComentarios(Publication publication) {
		operation.remove(new Query(Criteria.where("publication").is(publication.getId_publication())), Comment.class);
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

	
	
	public static boolean actualizarLike(int usuario, int id_publicacion,String Classpan) {
		int Comuna=0;
		try{
		
			/*int author = operation.find(new Query(
				Criteria.where("id_publication").is(id_publicacion)		
				),Publication.class).get(0).getAuthor();
			*/
			 List<Publication> list = operation.find(new Query(
						Criteria.where("id_publication").is(id_publicacion)		
						),Publication.class);
			
			
			
			int author=list.get(0).getAuthor();	
			Like like = new Like();
			like.setAuthor(author);
			like.setId_publicacion(id_publicacion);
			like.setUsuario(usuario);
			
			//Insert tabla like - mongodb
			if(Classpan.equals("glyphicon glyphicon-heart-empty")){
				 registrarLike(like);
				 
				 hibernateTransations.actualizarReputacion(author,"+1");
			}else{
				Eliminarlike(like);
				hibernateTransations.actualizarReputacion(author,"-1");
			}
			 
			
			return true;
			
		}catch (Exception e) {
			System.out.println("Error en actualizarLike " + e.getMessage());
			return false;
		}
		
		
	}
	public static List ConsultarLikes(int usuario) {
		List<Like> list= prepareOperation().find(
				new Query((Criteria.where("usuario").is(usuario))),
				Like.class);	
		
		
		return list;
	}
	
	//cambios
		public static Publication consultarPublication(int Id) {
			List<Publication> list;
			list =operation.find(
					new Query((Criteria.where("id_publication").is(Id))),
					Publication.class);
			System.out.println("tamaño p: " + list.size());
		
			if(list.size()==0){
				return null;
			}else{
				return (Publication)list.get(0);
			}
		}
		
		//cambios
		public static String consultarUbication(int IdBarrio) {
			List<Ubication> list;
			list =operation.find(new Query(
					Criteria.where("Id_").is(IdBarrio)),Ubication.class);
			System.out.println("tamaño b: " + list.size());
			
			String ubication = ",\"barrio\":\""+ list.get(0).getBar() + "\",\"comuna\":" + list.get(0).getComuna() + ",\"pto_cardinal\":\"" + 
			list.get(0).getPunto_cardinal() + "\"";
			
			return ubication;
		}
		
		//cambios
		public static List consultarComentarios(int idPub) {
			List<Comment> list;
			list =operation.find(new Query(
					Criteria.where("publication").is(idPub)).with(new Sort(Sort.Direction.ASC, "date")),Comment.class);
			
			System.out.println("tamaño c: " + list.size());
			
			
			return list;
		}
		
		//cambios
		public static List consultarComentarios(int idComentary,int idPub) {
			List<Comment> list;
			list =operation.find(new Query(
					Criteria.where("publication").is(idPub).andOperator(Criteria.where("id_commentary").gt(idComentary)		
							)).with(new Sort(Sort.Direction.ASC, "date")),Comment.class);
			
			System.out.println("tamaño c: " + list.size());
			
			
			return list;
		}

		
	
	public static boolean UpdateEstadistica(int Comuna,int author,String weather,int suma) {
		
		Estadistica Etd=new Estadistica();
		Date fecha = new Date();
		
		int Hora=fecha.getHours();
		Etd.setFecha(trim(fecha));
		Etd.setTipo(Tiempo(Hora));
		Etd.setComuna(Comuna);
		
		int vlrEstado=0;
		
		if(suma==-1){
			vlrEstado = hibernateTransations.ObtVlEdt(author);
		}else{
			vlrEstado = suma;
		}
		
		System.out.println("Valor de estado es: "+vlrEstado);
		if(ConsultarEstadistica(Etd).isEmpty()){
			if(weather.equals("rain"))Etd.setRain(vlrEstado);
			if(weather.equals("storm"))Etd.setStorm(vlrEstado);
			if(weather.equals("tempered"))Etd.setTempered(vlrEstado);
			if(weather.equals("sunny"))Etd.setSunny(vlrEstado);
			registrarEtd(Etd);
			System.out.println("Entro en la lista es empty");
		}else{
			System.out.println("Esta cargada!");
			Query query = new Query();
			query.addCriteria(Criteria.where("fecha").is(trim(fecha)).andOperator(
							Criteria.where("tipo").is(Tiempo(Hora)).andOperator(
							Criteria.where("comuna").is(Comuna))
							));

			Estadistica Etd2 = operation.findOne(query, Estadistica.class);
			System.out.println("Etd1 - " +Etd2 );
			Update update=new Update();
			
			if(suma==-1){
				if(weather.equals("rain"))update.set("rain",Etd2.getRain()+vlrEstado);
				if(weather.equals("storm"))update.set("storm",Etd2.getStorm()+vlrEstado);
				if(weather.equals("tempered"))update.set("tempered",Etd2.getTempered()+vlrEstado);
				if(weather.equals("sunny"))update.set("sunny",Etd2.getSunny()+vlrEstado);
			}else{
				if(weather.equals("rain"))update.set("rain",Etd2.getRain()-vlrEstado);
				if(weather.equals("storm"))update.set("storm",Etd2.getStorm()-vlrEstado);
				if(weather.equals("tempered"))update.set("tempered",Etd2.getTempered()-vlrEstado);
				if(weather.equals("sunny"))update.set("sunny",Etd2.getSunny()-vlrEstado);
			}
			
			operation.updateFirst(query, update, Estadistica.class);
			
		}
		
		
		return true;
	}
	
	public static int Tiempo(int Hora) {
		int resultado = 25; 
		//cambio2
		if (Hora >= 0 && Hora < 8){ 
			resultado = 10; 
		} else if (Hora > 7 && Hora < 13){ 
			resultado = 15; 
		} else if (Hora > 12 && Hora < 19){ 
			resultado = 20; 
		} 
		System.out.println("El momento edl dia es : "+resultado); 
		 
	return resultado;
	}
	
	public static Date trim(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        return calendar.getTime();
    }
	
	public static List ConsultarEstadistica(Estadistica Etd) {
		List<Estadistica> list= null;
		
		
		try{
			list = operation.find(new Query(	
					Criteria.where("fecha").is(Etd.getFecha()).andOperator(
							Criteria.where("tipo").is(Etd.getTipo()).andOperator(
							Criteria.where("comuna").is(Etd.getComuna()))
							)),Estadistica.class);
			
			
		}catch (Exception e) {
			System.out.println("Error en ConsultarEstadistica " + e.getMessage());
			
		}
		
		
	return list;
	}
	
	public static void registrarEtd(Estadistica Etd) {
		operation.save(Etd);
		
		
	}
	

}
