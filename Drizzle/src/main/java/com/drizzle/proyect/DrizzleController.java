package com.drizzle.proyect;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

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
import com.drizzle.persistence.hibernateTransations;


@MultipartConfig
@Controller
public class DrizzleController {
	
	
	
	@RequestMapping("index")
	public ModelAndView redireccionPrincipal() {
		return new ModelAndView("Index", "command", new Account());
	}
	@RequestMapping("volverregistrar")
	public ModelAndView volverRegistrar() {
		return new ModelAndView("Registrado", "command", new Account());
	}

	@RequestMapping(value = "/registrar", method = { RequestMethod.GET, RequestMethod.POST })
	public String redireccionregistro(@ModelAttribute Account account, HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		
		String login = request.getParameter("botonLogin");

		if (login == null) {

			if ((hibernateTransations.consultarAccount(account.getEmail())).isEmpty()) {
				if (hibernateTransations.registrarAccount(account)) {
					hibernateTransations.registrarProfile(account.getId_account());
					
					List<Object> Dts;
					Dts=hibernateTransations.consultarDatosSesion(account.getEmail());
					
					//sesion.setAttribute("usuario", Dts.get(0));
					//sesion.setAttribute("nombres", Dts.get(1)+" "+Dts.get(2));
					//sesion.setAttribute("photo", datosSesion.get());
					//System.out.println(Dts.toArray()+" "+Dts.get(0));
					
					return "Registrado";
				}else{
					return "redirect:/index.html#contact";
				}
			} else {
				return "redirect:/index.html#contact";
			}

		} else {
			//HttpSession sesion = request.getSession();
			String usu = request.getParameter("user_name");
			String pass = request.getParameter("pass");
			List<Account> results;  
			
			if(!(hibernateTransations.consultarAccount(usu).isEmpty())){
				System.out.println("Entro el consul tenia algo!");
				results=(hibernateTransations.consultarAccount(usu));
				//System.out.println(results.get(0));
				
				
				if(usu.equals(results.get(0).getEmail()) && pass.equals(results.get(0).getPassword())){
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

	}
	
	
		
		@RequestMapping(value = "/validarFoto", method = { RequestMethod.GET, RequestMethod.POST })
		public String cargarImg(@ModelAttribute Profile profile, HttpServletRequest request) {
			//PrintWriter out = response.getWriter();
			try{
			              
			       FileItemFactory Interfaz = new DiskFileItemFactory();
			       ServletFileUpload servlet_up = new ServletFileUpload(Interfaz);
			       List objetos =servlet_up.parseRequest(request);
				    String ruta ="C://Users//RICARDO OSPINA//WorkspaceSpring//ProjectDrizzle//IMG//";
				   for(int i=0;i<objetos.size();i++){
				       FileItem item = (FileItem) objetos.get(i);
				       if(item.getFieldName().equals("imageprofile")){
					      if (!item.isFormField()){
					          File archivo= new File(ruta+item.getName());
					          item.write(archivo);
					          //p.setVc_foto(item.getName());
					          byte[] b = new byte[(int) archivo.length()];
					          FileInputStream fs = new FileInputStream(archivo);
					          fs.read(b);
					          fs.close();
					          profile.setPhoto(b);
					          profile.setProfile_account(3);
					          profile.setReputation(7);
					          
					          System.out.println(b+"");
					          if(hibernateTransations.actualizarProfile(profile)){
					        	  System.out.println("Actualizo la Foto de perfil");
					          }
					          
					      }
				       	}
				    }
			
				}catch(Exception e){
			        System.out.print("<p>"+e.getMessage()+"</p>");
			        }
			//return "redirect:/index.html#contact";
			return "redirect:/volverregistrar.html";
		}
	
	

}
