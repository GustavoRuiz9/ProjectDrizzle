package com.drizzle.proyect;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.SimpleTimeZone;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.log4j.helpers.ISO8601DateFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drizzle.model.Account;
import com.drizzle.model.Comment;
import com.drizzle.model.Like;
import com.drizzle.model.Profile;
import com.drizzle.model.Publication;
import com.drizzle.model.Ubication;
import com.drizzle.persistence.hibernateTransations;
import com.drizzle.persistence.mongoConfig;
import com.drizzle.persistence.mongoTransations;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.fabric.xmlrpc.base.Array;

import jdk.nashorn.internal.parser.JSONParser;

import org.apache.soap.encoding.soapenc.Base64;
import org.bson.types.Binary;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.util.Locale;
import java.util.TimeZone;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

@MultipartConfig
@Controller
public class DrizzleController {
	
	byte[] b;
	boolean BtnPerfil;
	@RequestMapping(value = "/index", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView redireccionPrincipal(HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		if(request.getParameter("Sign_out")!=null){
			sesion.invalidate();
		}
		
		return new ModelAndView("Index", "command", new Account());
	}
	
	
	@RequestMapping(value = "/AjaxImage",  method = { RequestMethod.GET, RequestMethod.POST })
	public String TestAjax(HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		
		try {
			
			FileItemFactory Interfaz = new DiskFileItemFactory();
			ServletFileUpload servlet_up = new ServletFileUpload(Interfaz);
			List objetos = servlet_up.parseRequest(request);
			//String ruta = "/opt/tomcat/webapps/drizzleweb/resources/img/perfil/";
			String ruta = "C://Users//RICARDO OSPINA//WorkspaceSpring//ProjectDrizzle//IMG//";
			for (int i = 0; i < objetos.size(); i++) {
				FileItem item = (FileItem) objetos.get(i);
				if (item.getFieldName().equals("imageprofile")) {
					if (!item.isFormField()) {
						File archivo = new File(ruta + item.getName());
						item.write(archivo);
						b = new byte[(int) archivo.length()];
						FileInputStream fs = new FileInputStream(archivo);
						fs.read(b);
						fs.close();
						int Id_Usu= Integer.parseInt(sesion.getAttribute("usuario").toString());
						if (hibernateTransations.actualizarProfile(b,Id_Usu)) {
							BtnPerfil = true;
							System.out.println("Actualizo la Foto de perfil ");
							
						}

					}
				}
			}

		} catch (Exception e) {
			System.out.print("<p>" + e.getMessage() + "</p>");
			return null;
		}
		
		return null;
		
		}
	

	@RequestMapping(value = "/volveregistrar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView volverRegistrar(@ModelAttribute Account account,HttpServletRequest request) {
		//MODIFICAR PARA QUE ACTUALICE EL DIV DE LAS PUBLICACIONES Y NO TODO REGISTRADO.jsp
		return new ModelAndView("Registrado", "valorCombobox",request.getParameter("comuna"));
	}
	
	
	
	@RequestMapping(value = "/registrar", method = { RequestMethod.GET, RequestMethod.POST })
	public String redireccionregistro(@ModelAttribute Account account, HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		
		Object[] Dts;
		String login = request.getParameter("botonLogin");
		String ingreso = request.getParameter("botoningreso");
		//String Cmbfoto = request.getParameter("botonperfil");
		System.out.println("Login: "+login+" ingreso: "+ingreso+" cambioFoto: "+BtnPerfil);

		if (ingreso!=null) {
			if ((hibernateTransations.consultarAccount(account.getEmail())).isEmpty()) {
				if (hibernateTransations.registrarAccount(account)) {
						hibernateTransations.registrarProfile(account.getId_account());
					
					
					Dts=hibernateTransations.consultarDatosSesion(account.getEmail());
					byte[] photo = (byte[])Dts[3];			
					
					String photoBase64 = Base64.encode(photo);
					
					sesion.setAttribute("usuario", Dts[0]);
					sesion.setAttribute("nombres", Dts[1]+" "+Dts[2]);
					sesion.setAttribute("photo",photoBase64);
					//Limpiar el Objeto!
					Dts=null;
					
					return "Registrado";
				}else{
					return "redirect:/index.html#contact";
				}
			}else{
				return "redirect:/index.html#contact";
				 }

		}else 
			if(login!=null){
			System.out.println("entro al BtnLogin");
			String usu = request.getParameter("user_name");
			String pass = request.getParameter("pass");
			List<Account> results;  
			Date fecha = new Date();
			Calendar cal = Calendar.getInstance();
			Date date = new Date(fecha.getTime()-18000000);
			
			System.out.println("Esta fecha menos 5 Horas: "+date );
			
				if(!(hibernateTransations.consultarAccount(usu).isEmpty())){
				System.out.println("Entro el consul tenia algo!");
				results=(hibernateTransations.consultarAccount(usu));
				
				
					if(usu.equals(results.get(0).getEmail()) && pass.equals(results.get(0).getPassword())){
					
					Dts=hibernateTransations.consultarDatosSesion(results.get(0).getEmail());
					byte[] photo = (byte[])Dts[3];			
					
					String photoBase64 = Base64.encode(photo);
					
					sesion.setAttribute("usuario", Dts[0]);
					sesion.setAttribute("nombres", Dts[1]+" "+Dts[2]);
					sesion.setAttribute("photo",photoBase64);
					//Limpiar el Objeto!
					Dts=null;
					login=null;
					return "Registrado";
					}else{
					//alert
					System.out.println("Usuario o Password incorrectos");
					return "redirect:/index.html#";
					
					}
				
				}else{
					return "redirect:/index.html#contact";
				}
			}
		System.out.println("retorno null");
		return null;

	}
	
	@RequestMapping(value = "/registrarPublicacion", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String registrarPub(HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		Publication pub = new Publication();
		String UltDiv="";
		
		
		
			try {
					
					FileItemFactory Interfaz = new DiskFileItemFactory();
					ServletFileUpload servlet_up = new ServletFileUpload(Interfaz);
					List objetos = servlet_up.parseRequest(request);
					//String ruta = "/opt/tomcat/webapps/drizzleweb/resources/img/perfil/";
					String ruta = "C://Users//RICARDO OSPINA//WorkspaceSpring//ProjectDrizzle//IMG//";
					int Comuna=0;
					int id = Integer.parseInt(sesion.getAttribute("usuario").toString());
					pub.setAuthor(id);
					pub.setDate(new Date());
					for (int i = 0; i < objetos.size(); i++) {
						FileItem item = (FileItem) objetos.get(i);
						try{
						if (item.getFieldName().equals("files")) {
							if (!item.isFormField()) {
								File archivo = new File(ruta + item.getName());
								item.write(archivo);
								byte[] b = new byte[(int) archivo.length()];
								FileInputStream fs = new FileInputStream(archivo);
								fs.read(b);
								fs.close();
								pub.setPhoto(b);
							}
						}}catch (Exception e) {
							System.out.println("no hay foto!");
						}
						if (item.getFieldName().equals("textareaPublicacion")) {
							FileItem Descripcion = (FileItem) objetos.get(i);
							pub.setDescripcion(Descripcion.getString());
						}
						
						if (item.getFieldName().equals("weather")) {
							FileItem weather = (FileItem) objetos.get(i);
							pub.setWeather(weather.getString());
						}
						if (item.getFieldName().equals("Id_")) {
							FileItem Id_Barrio = (FileItem) objetos.get(i);
							pub.setId_Barrio(Id_Barrio.getString());
						}
						if (item.getFieldName().equals("Id_Pb")) {
							FileItem Id_Pb = (FileItem) objetos.get(i);
							UltDiv=Id_Pb.getString();
						}
						
					}
					
					//Actudalizar estadisticas-->
					if(pub.getId_Barrio().length()==3){
						 Comuna= Integer.parseInt(pub.getId_Barrio().substring(0, 1));
					 }else{
						 Comuna= Integer.parseInt(pub.getId_Barrio().substring(0, 2));
					 }
					 
					 mongoTransations.UpdateEstadistica(Comuna,id,pub.getWeather());
					
				} catch (Exception e) {
					System.out.print("<p>" + e.getMessage() + "</p>");
				}
			
				
			
				mongoTransations.registrarPublication(pub);
				
				
				System.out.println("Dta: "+UltDiv);

				
				
				
			return UpdateDiv(Integer.parseInt(sesion.getAttribute("usuario").toString()),UltDiv);
				
	}
	
	//cambios
		@RequestMapping(value = "/registrarCommentary", method = { RequestMethod.GET, RequestMethod.POST })
		public @ResponseBody String registrarComment(HttpServletRequest request) {
			HttpSession sesion = request.getSession();
			Comment comment = new Comment();
			int UltDivCommentary = 0;
			
				try {
						
						FileItemFactory Interfaz = new DiskFileItemFactory();
						ServletFileUpload servlet_up = new ServletFileUpload(Interfaz);
						List objetos = servlet_up.parseRequest(request);
						int id = Integer.parseInt(sesion.getAttribute("usuario").toString());
						
						comment.setAuthor(id);
						comment.setDate(new Date());
						
						
						for (int i = 0; i < objetos.size(); i++) {
							FileItem item = (FileItem) objetos.get(i);
							
							if (item.getFieldName().equals("descriptionComentary")) {
								FileItem Descripcion = (FileItem) objetos.get(i);
							comment.setDescription(Descripcion.getString());
							}
							
							if (item.getFieldName().equals("id_publication")) {
								FileItem id_Publication = (FileItem) objetos.get(i);
								comment.setPublication(Integer.parseInt(id_Publication.getString()));
							}
							
							if (item.getFieldName().equals("UltDivCommentary")) {
								FileItem ultCommentary = (FileItem) objetos.get(i);
								UltDivCommentary = (Integer.parseInt(ultCommentary.getString()));
							}
							
						}
							
						} catch (Exception e) {
							System.out.print("<p>" + e.getMessage() + "</p>");
						}
				
					//System.out.println("DATA Comment " + comment.toString());
				
					mongoTransations.registrarCommentary(comment);
					String json = "["+UpdateDivCommentary(UltDivCommentary,comment.getPublication())+ "]";
					System.out.println(json);
				return json;
					
		}
	
	public String UpdateDiv(int Usuario ,String UltDiv) {
		String jsonCompleto = "";
		
		List<Publication> ListPub;
		if(UltDiv.equals("0")){
			ListPub=mongoTransations.ConsultarPublicationes(UltDiv);	
		}else{
			int Id_Pb=Integer.parseInt(UltDiv);
			ListPub=mongoTransations.ActualizarPublic(Id_Pb);
			
		}
		
		/*for (Publication publication : ListPub) {
			
			JsonObject object = new JsonObject();
			object.addProperty("id_publication",publication.getId_publication());
			object.addProperty("weather", publication.getWeather());
			object.addProperty("date", publication.getDate().toString());
			object.addProperty("Descripcion", publication.getDescripcion());
			object.addProperty("photo", Base64.encode(publication.getPhoto()));
			int author = publication.getAuthor();
			if(Usuario==author){
				object.addProperty("author", "true");
			}else{
				object.addProperty("author", "false");
			}
			//consulta en mysql
			String datosAuthor = hibernateTransations.consultarAuthor(author);
			
			
			if(ListPub.get(ListPub.size()-1).getId_publication() == publication.getId_publication()){
				jsonCompleto += object.toString().substring(0,object.toString().length()-1) + datosAuthor;
			}else{
				jsonCompleto += object.toString().substring(0,object.toString().length()-1) + datosAuthor+",";
			}
			
			object = null;
			
		}
		
		jsonCompleto = "[" + jsonCompleto + "]";
		System.out.println("ASI con Auhtor " + jsonCompleto);*/
		
		return crearJson(ListPub, Usuario);
		
	}
	
	//cambios
		public String UpdateDivCommentary(int UltDiv, int Idpub) {
			String jsonCompleto = "";
			System.out.println("traae " + UltDiv);
			List<Comment> ListComm;
			if(UltDiv==-1){
				System.out.println("Consulta de no habia comentarios! comm:" + UltDiv);
				ListComm=mongoTransations.consultarComentarios(Idpub);	
			}else{
				System.out.println("Consulta de habia comentarios! comm:" + UltDiv + " idPb " + Idpub);
				ListComm=mongoTransations.consultarComentarios(UltDiv,Idpub);
			}
			
			return "{\"comments\":"+crearJsonCommentary(ListComm);
			
		}
	
	
	public String crearJson(List<Publication> ListPub, int Usuario) {
		String jsonCompleto = "";
		
		
		for (Publication publication : ListPub) {
			
			JsonObject object = new JsonObject();
			object.addProperty("id_publication",publication.getId_publication());
			object.addProperty("weather", publication.getWeather());
			object.addProperty("date", publication.getDate().toString());
			object.addProperty("Descripcion", publication.getDescripcion());
			object.addProperty("photo", Base64.encode(publication.getPhoto()));
			int author = publication.getAuthor();
			
			
			if(Usuario==author){
				object.addProperty("author", "true");
			}else{
				Like like= new Like();
				like.setAuthor(author);
				like.setUsuario(Usuario);
				like.setId_publicacion(publication.getId_publication());
				if(mongoTransations.Consultarlike(like).isEmpty()){
					object.addProperty("author", "false");
				}else{
					object.addProperty("author", "nofalse");
				}
				
				
			}
			//consulta en mysql
			String datosAuthor = hibernateTransations.consultarAuthor(author);
			
			
			if(ListPub.get(ListPub.size()-1).getId_publication() == publication.getId_publication()){
				jsonCompleto += object.toString().substring(0,object.toString().length()-1) + datosAuthor;
			}else{
				jsonCompleto += object.toString().substring(0,object.toString().length()-1) + datosAuthor+",";
			}
			
			object = null;
			
		}
		
		jsonCompleto = "[" + jsonCompleto + "]";
		//System.out.println("ASI con Auhtor " + jsonCompleto);
		
		return jsonCompleto;
		
	}
	
	//cambios
		public String crearJson(Publication publication, List<Comment> listComments) {
			String jsonCompleto = "";
			
			JsonObject object = new JsonObject();
			object.addProperty("id_publication",publication.getId_publication());
			object.addProperty("weather", publication.getWeather());
			object.addProperty("date", publication.getDate().toString());
			object.addProperty("Descripcion", publication.getDescripcion());
			//object.addProperty("photo", Base64.encode(publication.getPhoto()));
			int author = publication.getAuthor();
			int idbarrio = Integer.parseInt(publication.getId_Barrio());
			
			//consulta en mysql
			String datosAuthor = hibernateTransations.consultarAuthor(author);
			String datosUbication = mongoTransations.consultarUbication(idbarrio);
			
			String comentarios = crearJsonCommentary(listComments);
			
			/*comentarios ="[";
			for(Comment comment : listComments){
				
				JsonObject commentJson = new JsonObject();
				commentJson.addProperty("id_commentary",comment.getId_commentary());
				commentJson.addProperty("description", comment.getDescription());
				commentJson.addProperty("date", comment.getDate().toString());
				String authorComment = hibernateTransations.consultarAuthor(comment.getAuthor());
				comentarios += commentJson.toString().substring(0,commentJson.toString().length()-1)+authorComment.substring(0,authorComment.length()-1)+"},"; 
			}
			
			if(listComments.isEmpty()){
				comentarios = comentarios + "]}";
			}else{
				comentarios = comentarios.substring(0,comentarios.toString().length()-1) + "]}";
			}*/
					
			//Gson json = new Gson();
			//comentarios = json.toJson(listComments);
			
			jsonCompleto += object.toString().substring(0,object.toString().length()-1) + datosUbication + 
					datosAuthor.substring(0,datosAuthor.toString().length()-1)  + ",\"comments\":"+comentarios;
			
			
			return "["+jsonCompleto+"]";
			
		}
		
		//cambios
		public String crearJsonCommentary(List<Comment> listComments) {
			
			String jsoncomentarios = "[";
			
			for(Comment comment : listComments){
						
				JsonObject commentJson = new JsonObject();
				commentJson.addProperty("id_commentary",comment.getId_commentary());
				commentJson.addProperty("description", comment.getDescription());
				commentJson.addProperty("date", comment.getDate().toString());
				String authorComment = hibernateTransations.consultarAuthor(comment.getAuthor());
				jsoncomentarios += commentJson.toString().substring(0,commentJson.toString().length()-1)+authorComment.substring(0,authorComment.length()-1)+"},"; 
			}
			if(listComments.isEmpty()){
				jsoncomentarios = jsoncomentarios + "]}";
			}else{
				jsoncomentarios = jsoncomentarios.substring(0,jsoncomentarios.toString().length()-1) + "]}";
			}
			
			return jsoncomentarios;
		}
	
	
	@RequestMapping(value = "/Public", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String Publication(HttpServletRequest request) {
		//HttpSession sesion = request.getSession();
		String Barrio = request.getParameter("Barrio1");
		String Barrio2 = request.getParameter("Barrio2");
		String dato;
		
		
		List<Ubication> list = mongoTransations.ConsultarPosition(Barrio, Barrio2);
		if(list.isEmpty()){
			dato="error al consultar tu posicion,";
		}else{
			dato=list.get(0).getBar()+","+list.get(0).getId_();
			System.out.println(dato);
		}
		return dato;
	}
	
	@RequestMapping(value = "/DeletePublicacion", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String DeletePublicacion(HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		int Usuario=Integer.parseInt(sesion.getAttribute("usuario").toString());
		
		String id_Pbl = request.getParameter("id_Pbl");
		String UltDiv = request.getParameter("UltDiv");
		
		System.out.println("id_publicacion: "+id_Pbl);
		Publication Pb = new Publication();
		Pb.setId_publication(Integer.parseInt(id_Pbl));
		
		mongoTransations.borrarPublication(Pb);
		 return UpdateDiv(Usuario,UltDiv);
	}
	
	
	
	@RequestMapping(value = "/LikePublicacion", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String LikePublicacion(HttpServletRequest request) {
		
		//consulta del author publi..
		//insertamos con respecto public Mongo
		//update con respecto a al usuario MysQl
		//System.out.println("Entro Al like");

		HttpSession sesion = request.getSession();
		int Usuario=Integer.parseInt(sesion.getAttribute("usuario").toString());
		String SpanCls=request.getParameter("SpanCls");
		int id_Pbl = Integer.parseInt(request.getParameter("Lk_Pbl"));
		System.out.println("clase del span: "+SpanCls);
		return mongoTransations.actualizarLike(Usuario, id_Pbl,SpanCls)+"";
	}
	
	@RequestMapping(value = "/FiltroPb", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String FiltroPb(HttpServletRequest request) {
		
		HttpSession sesion = request.getSession();
		int Usuario=Integer.parseInt(sesion.getAttribute("usuario").toString());
		int Comuna = Integer.parseInt(request.getParameter("Comuna"));
		List <Publication> listaPublicaciones = mongoTransations.ConsultarPublicationes(Comuna+"");
		
		String publicacicones = crearJson(listaPublicaciones, Usuario);
		
		return publicacicones;
	}
	
	//cambios
		@RequestMapping(value = "/loadComments", method = { RequestMethod.GET, RequestMethod.POST })
		public @ResponseBody String loadComments(HttpServletRequest request) {
			HttpSession sesion = request.getSession();
			
			int Usuario=Integer.parseInt(sesion.getAttribute("usuario").toString());
			
			int id_pub = Integer.parseInt(request.getParameter("id_pub"));
			
			System.out.println("id_publicacion load: "+id_pub);
			
			Publication publicacion = null;
			String json = "";
			
			publicacion = mongoTransations.consultarPublication(id_pub);
			
			if(publicacion == null){
				json="false";
			}else{
				json = crearJson(publicacion,mongoTransations.consultarComentarios(id_pub));
			}
			
			System.out.println(json);
			
			return json;
		}
	
	@RequestMapping(value = "/constaGrafica", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String constaGrafica(HttpServletRequest request) {
		
		return "";
	}
	




}
