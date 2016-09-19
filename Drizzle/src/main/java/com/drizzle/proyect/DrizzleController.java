package com.drizzle.proyect;

import javax.servlet.http.HttpServletRequest;

import org.junit.runner.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.drizzle.model.Account;
import com.drizzle.persistence.hibernateTransations;

@Controller
public class DrizzleController {
	@RequestMapping("index")
	public ModelAndView redireccionPrincipal() {
		return new ModelAndView("Index", "command", new Account());
	}

	@RequestMapping(value = "/registrar", method = { RequestMethod.GET, RequestMethod.POST })
	public String redireccionregistro(@ModelAttribute Account account, HttpServletRequest request) {

		
		String login = request.getParameter("botonLogin");

		if (login == null) {

			if (hibernateTransations.consultarAccount(account.getEmail())) {
				if (hibernateTransations.registrarAccount(account)) {
					hibernateTransations.registrarProfile(account.getId_account());
					return "Registrado";
				}else{
					return "redirect:/index.html#contact";
				}
			} else {
				return "redirect:/index.html#contact";
			}

		} else {
			
			String usu = request.getParameter("user_name");
			String pass = request.getParameter("pass");
			
			if(!hibernateTransations.consultarAccount(usu)){
				return "Registrado";
			}else{
				return "redirect:/index.html#contact";
			}
		}

	}

}
