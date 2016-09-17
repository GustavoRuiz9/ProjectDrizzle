package com.drizzle.proyect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.drizzle.model.Account;
import com.drizzle.persistence.hibernateTransations;

@Controller
public class DrizzleController {
	@RequestMapping("index")
	public ModelAndView redireccionPrincipal(){
		return new ModelAndView("Index","command",new Account());
	}
	@RequestMapping(value="/registrar",method={RequestMethod.GET,RequestMethod.POST})
	public String redireccionregistro(@ModelAttribute Account account){
	
		if(hibernateTransations.consultarAccount(account.getEmail())){
			hibernateTransations.registrarAccount(account);
			return "Registrado";
		}
		
		return "redirect:/index.html#contact";
	}

}
