package com.protocoloApp.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.protocoloApp.dao.UserRepository;
import com.protocoloApp.entity.Usuario;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	private final UserRepository repository;
	
	public UsuarioController(UserRepository repository) {
		this.repository = repository;
	}
	
	@RequestMapping(value = "/cadastrar-form", method = RequestMethod.GET)
    public String cadastrar(Usuario usuario) {
        return "cruds/usuario/formUsuario";
    }
	
	@PostMapping("/cadastrar")
    public String salvarUsuario(@Valid Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cruds/usuario/formUsuario";
        }
        
        repository.save(usuario);
        return "redirect:/";
    }
	
	@RequestMapping("/usuarios")
	public ModelAndView listaUsuarios() {
		ModelAndView mv = new ModelAndView("cruds/consultas/consultaUsuarios");
		Iterable<Usuario> usuarios = repository.findAll();
		mv.addObject("usuarios", usuarios);
		return mv;
	}
	
	//método não finalizado
	@RequestMapping("/deletar")
	public String deletarUsuario(String cpf) {
		Usuario usuario = repository.findByCpf(cpf);
		repository.delete(usuario);
		return("redirect:/usuario/usuarios");
	}
}
