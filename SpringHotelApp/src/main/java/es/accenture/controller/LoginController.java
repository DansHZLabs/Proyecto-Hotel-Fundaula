package es.accenture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.accenture.Interfaces.InServiceUsuario;


/**
 * 
 * @author danih y jorge
 * @version 1.0
 */
@Controller
public class LoginController {
	
	@Autowired
	private InServiceUsuario usuarioService;
	
	@GetMapping("/")
	public String mostrarLogin() {
		
		return "Login";
	}
	
	@PostMapping("/login")
	public String login(Model model, @RequestParam("usuario") String usu, @RequestParam("password") String passw) {
		
		try {
			
			usuarioService.loginUsuario(usu, passw);
			
		} catch (Exception e) {
			
			model.addAttribute("error", e.getMessage());
			
			return "Login";
			
		}
		
		return "Principal";
	}

}
