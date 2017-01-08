package com.drizzle.proyect;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import java.util.SimpleTimeZone;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.jasper.tagplugins.jstl.core.Out;
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
import com.drizzle.model.Correo;
import com.drizzle.model.Estadistica;
import com.drizzle.model.Like;
import com.drizzle.model.Profile;
import com.drizzle.model.Publication;
import com.drizzle.model.Setting;
import com.drizzle.model.Ubication;
import com.drizzle.model.cifrar_decifrar;
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

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

@MultipartConfig
@Controller
public class DrizzleController {
	
	byte[] b;
	boolean BtnPerfil;
	
	@RequestMapping(value = "/index", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView redireccionPrincipal(HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		boolean pull=false;
		Object[] Dts;
		
		if(request.getParameter("Sign_out")!=null){
			sesion.removeAttribute("usuario");
			sesion.removeAttribute("nombres");
			sesion.removeAttribute("photo");
			System.out.println("Entro a cerrar sesion!");
			
		}
		if(sesion.getAttribute("usuario")!=null){
			System.out.println("usuario encontrado!"+sesion.getAttribute("usuario"));
			pull=true;
		}
		
		
		try {
			String usu=request.getParameter("usuario");
			String ale=request.getParameter("aleatorio");
			if(!usu.equals("") && !ale.equals("")){
				Object[] usuario=hibernateTransations.validarUsuario(usu,ale);
				if(usuario!=null){
					boolean habi=hibernateTransations.habilitarUsuario(Integer.parseInt(usuario[2].toString()));
					if(habi){
						pull=true;
						System.out.println("Tu Cuenta ha sido Verificada "+usuario[0]);
						Dts=hibernateTransations.consultarDatosSesion(usu);
						byte[] photo = (byte[])Dts[3];			
						String photoBase64 = Base64.encode(photo);
						sesion.setAttribute("usuario", Dts[0]);
						sesion.setAttribute("nombres", Dts[1]+" "+Dts[2]);
						sesion.setAttribute("photo",photoBase64);
						sesion.setAttribute("email",Dts[5]);
						//Limpiar el Objeto!
						Dts=null;
						System.out.println("Entro a verificar cuenta!");
						
					}
					
				}
				
					
			}
			
			
			
		} catch (Exception e) {
			System.out.println("no hay valores de verificacion index.html");
			// TODO: handle exception
			//return new ModelAndView("Index");
		}
		
		if(pull==true){
			pull=false;
			System.out.println("puso el registrado");
			return new ModelAndView("Registrado");
		}else{
			System.out.println("puso el index");
			//sesion.invalidate();
			return new ModelAndView("Index");
		}
		
		
		
		
	}
	
	@RequestMapping(value = "/Registrado", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView Registrado(HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		if(sesion.getAttribute("usuario")!=null){
			return new ModelAndView("Registrado");
		}else{
			return new ModelAndView("Index");
		}
		
		
	}
	@RequestMapping(value = "/form", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView formOlviCont(HttpServletRequest request) {
		
		return new ModelAndView("form");
	}
	
	
	@RequestMapping(value = "/olvCont", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String RContrasena(HttpServletRequest request) {
		//MODIFICAR PARA QUE ACTUALICE EL DIV DE LAS PUBLICACIONES Y NO TODO REGISTRADO.jsp
		Object[] Dts;
		String correo = request.getParameter("correo");
		//String ingreso = request.getParameter("botoningreso");
		Correo c= new Correo();
		
		
		
		try {
			
					Dts=hibernateTransations.consultarDatosSesion(correo);
					if(Dts!=null){
						cifrar_decifrar ciDe=new cifrar_decifrar();
						c.setContrasenia("smcovdpwpmuvijzq");
						c.setUsuarioCorreo("drizzleweb@hotmail.com");
						c.setAsunto("Solicitu Recuperacion de contraseña drizzleWeb");
						c.setMensaje("<h2>solicitud de Recuperacion de contraseña</h2> <br> "
								+ "<h4>"+Dts[1]+" Tu password Es: "+ciDe.descifra((byte[]) Dts[4])+"</h4> <br>"
								+ "<a href='http://localhost:8080/proyect/'>Drizzleweb</a> <br>"
								+ "<h4>DrizzleWeb.com</h4>");
						c.setDestino(correo);
						if(enviarCorreo(c)){
							System.out.println("Mensaje Enviado");
						}else{
							System.out.println("Mensaje no enviado");
							}
						return "success";
					}else{
						return "error";
					}
			
		} catch (Exception e) {
			System.out.println("error: "+e.getMessage());
			//return "error";
			return "fatal";
		}
		//return aleatorio;
	
		
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
						b = resize(archivo, true);
						//b = new byte[(int) archivo.length()];
						//FileInputStream fs = new FileInputStream(archivo);
						//fs.read(b);
						//fs.close();
						int Id_Usu= Integer.parseInt(sesion.getAttribute("usuario").toString());
						if (hibernateTransations.actualizarProfile(b,Id_Usu)) {
							BtnPerfil = true;
							sesion.setAttribute("photo",Base64.encode(b));
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
	

	@RequestMapping(value = "/registrar", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String redireccionregistro(HttpServletRequest request) {
		//MODIFICAR PARA QUE ACTUALICE EL DIV DE LAS PUBLICACIONES Y NO TODO REGISTRADO.jsp
		
		Account account = new Account();
		//Object[] Dts;
		//String login = request.getParameter("botonLogin");
		//String ingreso = request.getParameter("botoningreso");
		String aleatorio=CadenaAlfanumAleatoria(8); 
		Correo c= new Correo();
		
		
		
	
	try {
			
			FileItemFactory Interfaz = new DiskFileItemFactory();
			ServletFileUpload servlet_up = new ServletFileUpload(Interfaz);
			List objetos = servlet_up.parseRequest(request);
			for (int i = 0; i < objetos.size(); i++) {
				FileItem item = (FileItem) objetos.get(i);
				//System.out.println("objet: "+objetos.get(i).toString() + ", item.getFieldName: " + item.getFieldName() + ", item.getString: " + item.getString());
				
				if (item.getFieldName().equals("name")) {
					account.setName(item.getString());
				}
				if (item.getFieldName().equals("lastname")) {
					account.setLast_name(item.getString());
				}
				if (item.getFieldName().equals("email")) {
					account.setEmail(item.getString());
				}
				if (item.getFieldName().equals("birth")) {
					account.setBirth_date(item.getString());
				}
				if (item.getFieldName().equals("phone")) {
					account.setNumber_phone(item.getString());
				}
				if (item.getFieldName().equals("password")) {
					//account.setPassword(item.getString());
					cifrar_decifrar ciDe=new cifrar_decifrar();
					//String str = IOUtils.toString(ciDe.cifra(item.getString()),"UTF-8");
					account.setPassword(ciDe.cifra(item.getString()));
					byte[] bi=ciDe.cifra(item.getString());
					System.out.println("contraseña encryctada: "+ ciDe.cifra(item.getString())+" contraseña desencriptada: "+ciDe.descifra(bi));
				}
				
			}
			System.out.println(account.getName()+"--"+account.getLast_name()+"--"+account.getNumber_phone()+"--"+account.getEmail()+"--"+account.getBirth_date()+"--"+account.getPassword()+"--"+account.getId_account());
			
			if ((hibernateTransations.consultarAccount(account.getEmail())).length==0) {
				
				if (hibernateTransations.registrarAccount(account)) {
					
					Setting new_setting = new Setting();
					new_setting.setId_profile_account(account.getId_account());
					new_setting.setCorreo(false);
					new_setting.setTelefono(false);
					System.out.println(new_setting.getId_profile_account()+"--"+new_setting.isCorreo()+"--"+new_setting.isTelefono());
					
					if (hibernateTransations.registrarAjustes(new_setting)) {
					
						hibernateTransations.registrarProfile(account.getId_account(),aleatorio);
					
					c.setContrasenia("smcovdpwpmuvijzq");
					c.setUsuarioCorreo("drizzleweb@hotmail.com");
					c.setAsunto("Bienvenido a drizzleWeb");
					c.setMensaje("<h2>Bievenido ahora puedes compartir El estado del Clima</h2> <br> "
							+ "<h3>Recuerda solo en la ciudad de cali!</h3> <br> "
							+ "<h4>Este es un correo de verificacion Por favor pulsa en el enlace: </h4> <br>"
							+ "<a href='http://localhost:8080/proyect/index.html?usuario="+account.getEmail()+"&aleatorio="+aleatorio+"'>Enlace</a> <br>"
							+ "<h4>DrizzleWeb.com</h4>");
					c.setDestino(account.getEmail());
					
				}
				
				}
				System.out.println("entro!");
				
				if(enviarCorreo(c)){
					System.out.println("Mensaje Enviado");
				}else{
					System.out.println("Mensaje no enviado");
					}
				return "success";
			}else{
				return "error";
			}
			
				
			
		} catch (Exception e) {
			System.out.println("error: "+e.getMessage());
			//return "error";
			return "fatal";
		}
		//return aleatorio;
	
		
		
		
	}
	
	
	//pulido2
		@RequestMapping(value = "/reactivarCuenta", method = { RequestMethod.GET, RequestMethod.POST })
		public @ResponseBody String reactivarCuenta(HttpServletRequest request) {
			
			int id_account = 0;
			String email = "";

			String aleatorio=CadenaAlfanumAleatoria(8); 
			Correo c= new Correo();
			
		try {
				
				FileItemFactory Interfaz = new DiskFileItemFactory();
				ServletFileUpload servlet_up = new ServletFileUpload(Interfaz);
				List objetos = servlet_up.parseRequest(request);
				
				for (int i = 0; i < objetos.size(); i++) {
					FileItem item = (FileItem) objetos.get(i);
					if(item.getFieldName().equals("id_account")){
						id_account = Integer.parseInt(item.getString());
					}
					if(item.getFieldName().equals("email")){
						email = item.getString();
					}
				}
				
				//actualizar profile aleatorio
				if (hibernateTransations.actualizarStatus(id_account, aleatorio)){
					
					
					//if (hibernateTransations.reactivarProfile(account.getId_account(),aleatorio)) {
						System.out.println("TRAE!! " + id_account +" y " + email);
						
						c.setContrasenia("smcovdpwpmuvijzq");
						c.setUsuarioCorreo("drizzleweb@hotmail.com");
						c.setAsunto("Reactivar cuenta DrizzleWeb");
						c.setMensaje("<h2>Bievenido ahora puedes seguir compartiendo el estado del Clima</h2> <br> "
								+ "<h3>Recuerda solo en la ciudad de cali!</h3> <br> "
								+ "<h4>Este es un correo de verificacion Por favor pulsa en el enlace: </h4> <br>"
								//pulido2 --quermar por q ni idea en java como obtener eso
								//+ "<a href='http://drizzleweb2.j.facilcloud.com/drizzleweb/index.html?usuario="+email+"&aleatorio="+aleatorio+"'>Enlace</a> <br>"
								+ "<a href='http://localhost:8080/proyect/index.html?usuario="+email+"&aleatorio="+aleatorio+"'>Enlace</a> <br>"
								+ "<h4>DrizzleWeb.com</h4>");
						c.setDestino(email);
						
					if(enviarCorreo(c)){
						System.out.println("Mensaje Enviado");
						return "success";
					}else{
						System.out.println("Mensaje no enviado");
						return "error";
					}
						
				}else{
					return "error";
				}
				
			} catch (Exception e) {
				System.out.println("error: "+e.getMessage());
				//return "error";
				return "fatal";
			}
			//return aleatorio;
		
			
			
			
		}

	
	
	//pulido
		public static byte[] resize(File icon, boolean perfil) {
	        try {
	        	
	        	int ancho = 900;
	        	int alto = 650;
	        	
	        	if(perfil){
	        		ancho = 215;
	        		alto = 215;
	        	}
	        	
	        	System.out.println("pongalos en acho:"+ancho + " alto:"+alto);
	        	
	           BufferedImage originalImage = ImageIO.read(icon);

	           	originalImage = Scalr.resize(originalImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, ancho, alto);
	            //To save with original ratio uncomment next line and comment the above.
	            //originalImage= Scalr.resize(originalImage, 153, 128);
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ImageIO.write(originalImage, "png", baos);
	            baos.flush();
	            byte[] imageInByte = baos.toByteArray();
	            baos.close();
	            return imageInByte;
	        } catch (Exception e) {
	        	System.out.println("error en el metodo resize");
	            return null;
	        }
		}

	
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String Logger(HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		Account account = new Account();
		Object[] Dts;
		
		try {
					String usu="";
					String pass="";
					FileItemFactory Interfaz = new DiskFileItemFactory();
					ServletFileUpload servlet_up = new ServletFileUpload(Interfaz);
					List objetos = servlet_up.parseRequest(request);
					for (int i = 0; i < objetos.size(); i++) {
						FileItem item = (FileItem) objetos.get(i);
						//System.out.println("objet: "+objetos.get(i).toString() + ", item.getFieldName: " + item.getFieldName() + ", item.getString: " + item.getString());
						
						if (item.getFieldName().equals("user_name")) {
							usu=item.getString();
						}
						if (item.getFieldName().equals("pass")) {
							pass=item.getString();
						}
						
					}
					
					Object[] results;  
					Date fecha = new Date();
					Calendar cal = Calendar.getInstance();
					Date date = new Date(fecha.getTime()-18000000);
					
						System.out.println("Esta fecha menos 5 Horas: "+date );
						results=(hibernateTransations.consultarAccount(usu));
						if(!(results.length==0)){
							System.out.println("estatus es: "+results[2]);
							//pulido2
							if(!results[3].toString().isEmpty()){
								if(results[2].toString().equals("true")){
									cifrar_decifrar ciDe=new cifrar_decifrar();
									String passBd=ciDe.descifra((byte[]) results[1]);
									System.out.println("password desifrado: "+passBd);
									if(usu.equals(results[0]) && pass.equals(passBd)){
										
										Dts=hibernateTransations.consultarDatosSesion(results[0].toString());
										byte[] photo = (byte[])Dts[3];			
										String photoBase64 = Base64.encode(photo);
										sesion.setAttribute("usuario", Dts[0]);
										sesion.setAttribute("nombres", Dts[1]+" "+Dts[2]);
										sesion.setAttribute("photo",photoBase64);
										sesion.setAttribute("email",Dts[5]);
										//Limpiar el Objeto!
										Dts=null;
										return "success";
										}else{
										System.out.println("Datos incorrectos");
										return "warning";
										}
								}else{
									return "invalidate";
								}
							//pulido2
							}else{
								return results[0]+" "+results[4];
							}
							
						}else{
							return "error";
						}
					
					
					
				} catch (Exception e) {
					System.out.println("error: "+e.getMessage());
					//return "error";
					return "fatal";
				}
		
		
		
		/*
	
			
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
					return "Registrado";
					}else{
					System.out.println("Usuario o Password incorrectos");
					return "redirect:/index.html#";
					
					}
				
				}else{
					return "redirect:/index.html#contact";
				}*/
	}
	
	
	//pulido
		@RequestMapping(value = "/registrarPublicacion", method = { RequestMethod.GET, RequestMethod.POST })
		public @ResponseBody String registrarPub(HttpServletRequest request) {
			HttpSession sesion = request.getSession();
			Publication pub = new Publication();
			String UltDiv="";
			
				try {
						
						FileItemFactory Interfaz = new DiskFileItemFactory();
						ServletFileUpload servlet_up = new ServletFileUpload(Interfaz);
						List objetos = servlet_up.parseRequest(request);
						String ruta = "C://Users//RICARDO OSPINA//WorkspaceSpring//ProjectDrizzle//IMG//";
						//String ruta = "/opt/tomcat/webapps/drizzleweb/resources/img/";
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
									//byte[] b = new byte[(int) archivo.length()];
									byte[] b = resize(archivo,false);
									//FileInputStream fs = new FileInputStream(archivo);
									//fs.read(b);
									//fs.close();
									pub.setPhoto(b);
								}
							}}catch (Exception e) {
								System.out.println("no hay foto!");
							}
							if (item.getFieldName().equals("textareaPublicacion")) {
								FileItem Descripcion = (FileItem) objetos.get(i);
								//pulido
								if(!Descripcion.getString().trim().isEmpty()){
									pub.setDescripcion(Descripcion.getString());
								}
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
						 
						//pulido2
						 mongoTransations.UpdateEstadistica(Comuna,id,pub,true);
						 pub.setPtos_publicacion(hibernateTransations.ObtVlEdt(id));
						 
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
			//pulido
			if(publication.getDescripcion()!=null){
				object.addProperty("Descripcion", publication.getDescripcion());
			}
			//pulido
			if(publication.getPhoto()!=null){
				object.addProperty("photo", Base64.encode(publication.getPhoto()));
			}
			
			int author = publication.getAuthor();
			object.addProperty("authorperfil",author);
			
			
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
			//pulido
			String datosUbication = mongoTransations.consultarUbication(Integer.parseInt(publication.getId_Barrio()));
			
			//pulido
			if(ListPub.get(ListPub.size()-1).getId_publication() == publication.getId_publication()){
				jsonCompleto += object.toString().substring(0,object.toString().length()-1) +datosUbication+ datosAuthor;
			}else{
				jsonCompleto += object.toString().substring(0,object.toString().length()-1) +datosUbication+ datosAuthor+",";
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
	
	//pulido
		@RequestMapping(value = "/DeletePublicacion", method = { RequestMethod.GET, RequestMethod.POST })
		public @ResponseBody String DeletePublicacion(HttpServletRequest request) {
			HttpSession sesion = request.getSession();
			int Usuario=Integer.parseInt(sesion.getAttribute("usuario").toString());
			int Comuna = 0;
			String id_Pbl = request.getParameter("id_Pbl");
			String UltDiv = request.getParameter("UltDiv");
			
			System.out.println("elminar id_publicacion y comments: "+id_Pbl);
			Publication Pb = new Publication();
			Pb = mongoTransations.consultarPublication(Integer.parseInt(id_Pbl));
			System.out.println("id_publicacion "+Pb.getId_publication());
			//consultar Publicacion por id_Pbl
			//Pb.setId_publication(Integer.parseInt(id_Pbl));
			id_Pbl="";
			
			//Actudalizar estadisticas-->
			if(Pb.getId_Barrio().length()==3){
				 Comuna= Integer.parseInt(Pb.getId_Barrio().substring(0, 1));
			 }else{
				 Comuna= Integer.parseInt(Pb.getId_Barrio().substring(0, 2));
			 }
			
			//pulido2
			mongoTransations.UpdateEstadistica(Comuna,Usuario,Pb,false);
			
			mongoTransations.borrarPublication(Pb);
			//pulido
			mongoTransations.borrarComentarios(Pb);
			
			
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
		
public String crearJsonEstadisticas(List<Estadistica> listEstadisticas) {
			
			String jsonEstadisticas = "[{\"registro\":[";

			if(!listEstadisticas.isEmpty()){
				
				jsonEstadisticas += "[";
				
				int comuna = listEstadisticas.get(0).getComuna();
				
				for(Estadistica estadistica : listEstadisticas){
							
					JsonObject estadisticaJson = new JsonObject();
					
					int[] clima = converClima(estadistica.getStorm(), estadistica.getSunny(), estadistica.getRain(), estadistica.getTempered());
					
					//estadisticaJson.addProperty("tipo",converTipo(estadistica.getTipo()));
					estadisticaJson.addProperty("tipo",estadistica.getTipo());
					estadisticaJson.addProperty("clima",clima[0]);
					estadisticaJson.addProperty("valorClima",clima[1]);
					estadisticaJson.addProperty("comuna",estadistica.getComuna());
					
					/*if(estadistica.getComuna()==comuna){
						jsonEstadisticas += estadisticaJson.toString() + ",";
					}else{
						comuna = estadistica.getComuna();
						jsonEstadisticas = jsonEstadisticas.substring(0,jsonEstadisticas.length()-1) + "],[" + estadisticaJson.toString() + "}";
					}*/
					if(estadistica.getComuna()==comuna){
						jsonEstadisticas += estadisticaJson.toString() + ",";
					}else{
						comuna = estadistica.getComuna();
						jsonEstadisticas = jsonEstadisticas.substring(0,jsonEstadisticas.length()-1) + "],[" + estadisticaJson.toString() + ",";
					}
					
				}

				jsonEstadisticas = jsonEstadisticas.substring(0, jsonEstadisticas.length()-1) + "]]}]";
			
			}else{
				jsonEstadisticas += "]}]";
			}
			
			return jsonEstadisticas;
		}
		
		public int converTipo(String tipoString){
			
			int tipo = 0;
			
			if(tipoString.equals("madrugada")){
				tipo = 10;
			}else{
				if(tipoString.equals("mañana")){
					tipo = 15;
				}else{
					if(tipoString.equals("tarde")){
						tipo = 20;
					}else{
						if(tipoString.equals("noche")){
							tipo = 25;
						}
					}
				}
			}
			
			return tipo;
		}
		
		
		//mejorar
		public int[] converClima(int storm, int sunny, int rain, int temp){
			//int clima = 0;
			int mayor[] = new int[2];
			
			int bigger = Integer.max(Integer.max(Integer.max(storm, sunny), rain),temp);
			System.out.println("el mayor: "+bigger);
			if(bigger==temp){
				mayor[0] = 25;
				mayor[1] = temp;
			}else{
				if(bigger==sunny){
					mayor[0] = 20;
					mayor[1] = sunny;
				}else{
					if(bigger==rain){
						mayor[0] = 10;
						mayor[1] = rain;
						
					}else{
						if(bigger==storm){
							mayor[0] = 15;
							mayor[1] = storm;
						}
					}
					
				}
			}
			
			
			/*
			
			if(rain > sunny && rain > storm && rain > temp){
				clima = 10;
				mayor[0] = clima;
				mayor[1] = rain;
			}else{
				if(storm > sunny && storm > rain && storm > temp){
					clima = 15;
					mayor[0] = clima;
					mayor[1] = storm;
				}else{
					if(sunny > storm && sunny > rain && sunny > temp){
						clima = 20;
						mayor[0] = clima;
						mayor[1] = sunny;
					}else{
						if(temp > sunny && temp > rain && temp > storm){
							clima = 25;
							mayor[0] = clima;
							mayor[1] = temp;
						}else{
							if(temp == sunny && sunny == rain && rain == storm){
								clima = 25;
								mayor[0] = clima;
								mayor[1] = temp;	
							}else{
								if(temp > sunny && sunny == rain && temp == storm){
									clima = 25;
									mayor[0] = clima;
									mayor[1] = temp;	
								}else{
									if(temp > rain && rain == storm && temp == sunny){
										clima = 25;
										mayor[0] = clima;
										mayor[1] = temp;
								}else{
									if(temp > storm && storm == sunny && temp == rain){
										clima = 25;
										mayor[0] = clima;
										mayor[1] = temp;
									}else{
										if(temp > storm && temp == rain && temp == sunny){
											clima = 25;
											mayor[0] = clima;
											mayor[1] = temp;
										}else{
											if(temp > rain && temp == storm && temp == sunny){
												clima = 25;
												mayor[0] = clima;
												mayor[1] = temp;
											}else{
												if(temp > sunny && temp == storm && temp == rain){
													clima = 25;
													mayor[0] = clima;
													mayor[1] = temp;
												}
											}
										}
											
									}
											
								}
							}
						}
					}
				}
			}
		}*/
		
			return mayor;
	}
		
	
		@RequestMapping(value = "/constaGrafica", method = { RequestMethod.GET, RequestMethod.POST })
		public @ResponseBody String constaGrafica(HttpServletRequest request) {
			
			int comuna = Integer.parseInt(request.getParameter("comuna"));
			
			System.out.println("comuna a Consultar " + comuna);
			
			String jsonCompleto = crearJsonEstadisticas(mongoTransations.consultarEstadisticas(comuna));
			
			System.out.println("Estadisticas " + jsonCompleto);
			
			return jsonCompleto;
		}
		
	
	@RequestMapping(value = "/consultarDatosPerfilAuthor", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String consultarDatosPerfilAuthor(HttpServletRequest request) {
		
		int author = Integer.parseInt(request.getParameter("id_author"));
		
		System.out.println("perfil a consultar " + author);
		
		String jsonCompleto = hibernateTransations.consultarDatosProfile(author);
		
		System.out.println("Datos Profile " + jsonCompleto);
		
		return jsonCompleto;
	}
	

	@RequestMapping(value = "/obtenerAjustes", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String obtenerAjustes(HttpServletRequest request) {
		
		HttpSession sesion = request.getSession();
		String CambioNombre="";
		
		
		try {
			String nombre = "";
			String apellido = ""; 
			String contrasena = "";
			int telefono = 0;
			boolean checkCorreo = false;
			boolean checkTelefono = false;
			
			int Usuario=Integer.parseInt(sesion.getAttribute("usuario").toString());
			FileItemFactory Interfaz = new DiskFileItemFactory();
			ServletFileUpload servlet_up = new ServletFileUpload(Interfaz);
			List objetos = servlet_up.parseRequest(request);
			
			for (int i = 0; i < objetos.size(); i++) {
				FileItem item = (FileItem) objetos.get(i);
				//System.out.println("objet: "+objetos.get(i).toString() + ", item.getFieldName: " + item.getFieldName() + ", item.getString: " + item.getString());
				
				if (item.getFieldName().equals("nombreModificar")) {
					nombre = item.getString();
					//pub.setDescripcion(Descripcion.getString());
				}
				if (item.getFieldName().equals("apellidoModificar")) {
					apellido=item.getString();
				}
				if (item.getFieldName().equals("contrasenaModificar")) {
					contrasena=item.getString();
				}
				if (item.getFieldName().equals("telefonoModificar")) {
					telefono=Integer.parseInt(item.getString());
				}
				if (item.getFieldName().equals("checkTelefono")) {
					checkTelefono=true;
				}
				if (item.getFieldName().equals("checkCorreo")) {
					checkCorreo=true;
				}
				
			}
			
			sesion.getAttribute("nombres");
			if(!sesion.getAttribute("nombres").equals(nombre+" "+apellido)){
				sesion.setAttribute("nombres",nombre+" "+apellido);
				//CambioNombre=nombre+" "+apellido;
				CambioNombre="[{\"nombre\":\""+ nombre + "\",\"apellido\": \"" + apellido + "\", \"codigo\":\"" + sesion.getAttribute("usuario").toString() +"\"}]";
				
			}
			
			
			cifrar_decifrar ciDe=new cifrar_decifrar();
			//String str = IOUtils.toString(ciDe.cifra(item.getString()),"UTF-8");
			hibernateTransations.actualizarAjustes(Usuario, checkCorreo, checkTelefono, nombre, apellido, ciDe.cifra(contrasena), telefono);
			return CambioNombre;
			
		} catch (Exception e) {
			// TODO: handle exception
			return CambioNombre;
		}
		
		
	}
	
	//pulido2
		@RequestMapping(value = "/setStatus", method = { RequestMethod.GET, RequestMethod.POST })
		public @ResponseBody String setStatus(HttpServletRequest request) {
			
			HttpSession sesion = request.getSession();
			
			int author=Integer.parseInt(sesion.getAttribute("usuario").toString());
			sesion.removeAttribute("usuario");
			sesion.removeAttribute("nombres");
			sesion.removeAttribute("photo");
			return ""+hibernateTransations.actualizarStatus(author, "");
		}
	
	@RequestMapping(value = "/showSettings", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String ShowSettings(HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		int id = Integer.parseInt(sesion.getAttribute("usuario").toString());
			String json=hibernateTransations.consultarSettings(id);
			System.out.println(json);
			return json;
				
	}
	
	//cambios3
	
		@RequestMapping(value = "/getPublicationsRecent", method = { RequestMethod.GET, RequestMethod.POST })
		public @ResponseBody String getPublicationsRecent(HttpServletRequest request) {
			HttpSession sesion = request.getSession();
			String json = "[";
			List<Publication> listPublicaciones = mongoTransations.consultarPublicationesRecientes();
			
			
			for(Publication publicacion : listPublicaciones){
						
				JsonObject object = new JsonObject();
				
				object.addProperty("id_publication",publicacion.getId_publication());
				object.addProperty("photo", Base64.encode(publicacion.getPhoto()));
				object.addProperty("weather", publicacion.getWeather());
				object.addProperty("date", publicacion.getDate().toString());
				object.addProperty("Descripcion", publicacion.getDescripcion());
				String ubicacion = mongoTransations.consultarUbication(Integer.parseInt(publicacion.getId_Barrio()));
				String datosAuthor = hibernateTransations.consultarAuthor(publicacion.getAuthor());
				
				
				json += object.toString().substring(0, object.toString().length()-1)+ubicacion+datosAuthor+","; 
			}
			
			return listPublicaciones.isEmpty()?json+"]":json.substring(0, json.length()-1)+"]";
					
		}
		
	public boolean enviarCorreo(Correo correo){
			
		
		//correo.setNombreArchivo("profile.png");
		//correo.setRutaArchivo("C:/Users/RICARDO OSPINA/WorkspaceSpring/ProjectDrizzle/IMG/profile.png");
		
		
		try {
			Properties p = new Properties();
			p.put("mail.smtp.host","smtp.live.com");
			p.setProperty("mail.smtp.starttls.enable", "true");
			p.setProperty("mail.smtp.port", "587");
			p.setProperty("mail.smtp.user", correo.getUsuarioCorreo());
			p.setProperty("mail.smtp.auth", "true");
			
			Session s=Session.getDefaultInstance(p,null);
			BodyPart texto=new MimeBodyPart();
			//texto.setText(correo.getMensaje());
			texto.setContent(correo.getMensaje(), "text/html; charset=utf-8");
			BodyPart adjunto= new MimeBodyPart();
			
			/*if(!correo.getRutaArchivo().equals("")){
				adjunto.setDataHandler(new DataHandler(new FileDataSource(correo.getRutaArchivo())));
				adjunto.setFileName(correo.getNombreArchivo());
			}*/
			MimeMultipart m=new MimeMultipart();
			m.addBodyPart(texto);
			
			/*if(!correo.getRutaArchivo().equals("")){
				m.addBodyPart(adjunto);
				
			}*/
			MimeMessage mensaje=new MimeMessage(s);
			mensaje.setFrom(new  InternetAddress(correo.getUsuarioCorreo()));
			System.out.println("correo: "+correo.getDestino());
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correo.getDestino()));
			mensaje.setSubject(correo.getAsunto());
			mensaje.setContent(m);
			
			Transport t =s.getTransport("smtp");
			t.connect(correo.getUsuarioCorreo(),correo.getContrasenia());
			t.sendMessage(mensaje, mensaje.getAllRecipients());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error en Enviarcorreo "+e.getMessage());
			return false;
		}
			
			
					
	}
	
	
	 public  String CadenaAlfanumAleatoria (int longitud){
		  String cadenaAleatoria="";
		  long milis = new java.util.GregorianCalendar().getTimeInMillis();
		  Random r = new Random(milis);
		  int i = 0;
		  while ( i < longitud){
		  char c = (char)r.nextInt(255);
		  //System.out.println("char:"+c);
		  if ( (c >= '0' && c <=9) || (c >='A' && c <='Z') ){
		  cadenaAleatoria += c;
		  i ++;
		  }
		  }
		  
		  return cadenaAleatoria;
	 }
	 
	 
	 
	 
	
		
		
	


}
