package com.drizzle.proyect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.drizzle.model.Account;

@Controller
public class DrizzleController {
	@RequestMapping("index")
	public ModelAndView redireccionPrincipal(){
		return new ModelAndView("Index","command",new Account());
	}
	@RequestMapping(value="/registrar",method={RequestMethod.GET,RequestMethod.POST})
	public String redireccionregistro(ModelMap Model,Account Usu){
		//ModelAndView Mv = new ModelAndView();
		//Mv.setViewName("Index");
		//Mv.addObject("Mensaje","Hola soy adolfo");
		Model.addAttribute("name",Usu.getName());
		return "Saludo";
	}

}
