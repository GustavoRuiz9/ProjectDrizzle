package com.drizzle.proyect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DrizzleController {
	
	@RequestMapping("index")
	public ModelAndView redireccionPrincipal(){
		ModelAndView Mv = new ModelAndView();
		Mv.setViewName("Index");
		Mv.addObject("Mensaje","Hola soy");
		return Mv;
	}

}
