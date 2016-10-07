package com.drizzle.proyect;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.drizzle.model.Account;
import com.drizzle.model.Profile;
import com.drizzle.model.Publication;
import com.drizzle.persistence.hibernateTransations;
import com.drizzle.persistence.mongoConfig;
import com.drizzle.persistence.mongoTransations;

import org.apache.soap.encoding.soapenc.Base64;


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

	@RequestMapping(value = "/volveregistrar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView volverRegistrar(@ModelAttribute Account account,HttpServletRequest request) {
		
		
		return new ModelAndView("Registrado", "command", new Account());
	}
	
	@RequestMapping(value = "/registrar", method = { RequestMethod.GET, RequestMethod.POST })
	public String redireccionregistro(@ModelAttribute Account account, HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		
		
		
		
		
		Object[] Dts;
		String login = request.getParameter("botonLogin");
		String ingreso = request.getParameter("botoningreso");
		String Cmbfoto = request.getParameter("botonperfil");
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
					
					//Registrar primera publicacion
					Publication pub = new Publication();
					
					File filephoto = new File("/home/tavoruiz/git/ProjectDrizzle/Drizzle/src/main/webapp/resources/img/perfil/avatar.png");
					byte[] photoBytes = new byte[(int)filephoto.length()];
					
					Date fecha = new Date();
					Date ayer = new Date( fecha.getTime()-86400000);
					pub.setAuthor(Integer.parseInt(sesion.getAttribute("usuario")+""));
					pub.setId_publication(1);
					pub.setDate(fecha+"");
					pub.setWeather("Lluvia Fuerte");
					pub.setDescripcion("fuerte lluvia llevar paraguas si desea salir");
					pub.setPhoto(photoBytes);
					mongoTransations.registrarPublication(pub);
					
					return "Registrado";
				}else{
					return "redirect:/index.html#contact";
				}
			}else{
				return "redirect:/index.html#contact";
				 }

		}else 
			if(login!=null){
			//hibernateTransations.consultarDatos(93);
			System.out.println("entro al BtnLogin");
			String usu = request.getParameter("user_name");
			String pass = request.getParameter("pass");
			List<Account> results;  
			
				if(!(hibernateTransations.consultarAccount(usu).isEmpty())){
				System.out.println("Entro el consul tenia algo!");
				results=(hibernateTransations.consultarAccount(usu));
				//System.out.println(results.get(0));
				
				
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
					//alert
					System.out.println("Usuario o Password incorrectos");
					return "redirect:/index.html#";
					
					}
				
				}else{
				return "redirect:/index.html#contact";
				}
			}else
				if(BtnPerfil==true){
					
					System.out.println("entro en el boton btnperfil");
					String photoBase64 = Base64.encode(b);
					sesion.setAttribute("photo",photoBase64);
					BtnPerfil=false;
					return "Registrado";
				}
		System.out.println("retorno null");
		return null;

	}

	@RequestMapping(value = "/validarFoto", method = { RequestMethod.GET, RequestMethod.POST })
	public String cargarImg(@ModelAttribute Profile profile, HttpServletRequest request) {
		// PrintWriter out = response.getWriter();
		HttpSession sesion = request.getSession();
		
		try {
			
			FileItemFactory Interfaz = new DiskFileItemFactory();
			ServletFileUpload servlet_up = new ServletFileUpload(Interfaz);
			List objetos = servlet_up.parseRequest(request);
			String ruta = "/home/tavoruiz/git/ProjectDrizzle/IMG/";
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
						//profile.setProfile_account(3);
						//profile =(Profile)session.get(Profile.class, Id_Usu);
						//profile.setProfile_account(Id_Usu);
						//profile.setPhoto(b);
						//System.out.println(b + "");
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
		return "redirect:/registrar.html";
	}


@RequestMapping(value = "/registrarPublicacion", method = { RequestMethod.GET, RequestMethod.POST })
	public String registrarPub(@ModelAttribute Publication publication, HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		Publication pub = new Publication();
		
			try {
					
					FileItemFactory Interfaz = new DiskFileItemFactory();
					ServletFileUpload servlet_up = new ServletFileUpload(Interfaz);
					List objetos = servlet_up.parseRequest(request);
					String ruta = "/home/tavoruiz/git/ProjectDrizzle/IMGPublicaciones/";
					for (int i = 0; i < objetos.size(); i++) {
						FileItem item = (FileItem) objetos.get(i);
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
						}
						if (item.getFieldName().equals("comboboxTipoClima")) {
							FileItem weather = (FileItem) objetos.get(i);
							pub.setWeather(weather.getString());
						}
					}
					
					int id = Integer.parseInt(sesion.getAttribute("usuario").toString());
					pub.setAuthor(id);
					pub.setDate(new Date().toString());
					pub.setId_publication(0);
					pub.setDescripcion("Empezo a llover");
					
				} catch (Exception e) {
					System.out.print("<p>" + e.getMessage() + "</p>");
				}
			
				mongoTransations.registrarPublication(pub);
			
		return "redirect:/volveregistrar.html";
	}

}
