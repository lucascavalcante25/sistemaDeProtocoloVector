package com.protocoloApp.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.protocoloApp.dao.ProtocoloDao;
import com.protocoloApp.entity.Protocolo;

@Controller
@RequestMapping(value = "/protocolo")
public class ProtocoloController {

	private final ProtocoloDao protocoloDao;

	public ProtocoloController(ProtocoloDao protocoloDao) {
		this.protocoloDao = protocoloDao;
	}
	
	@RequestMapping(value = "/cadastrar-Protocolo", method = RequestMethod.GET)
	public String cadastrar(Protocolo protocolo) {
		return "cruds/protocolo/formProtocolo";
	}

	@PostMapping("/cadastrarProtocolo")
	public String salvarProtocolo(@Valid Protocolo protocolo, BindingResult result, Model model) {
		if (result.hasErrors()) {
            return "cruds/protocolo/formProtocolo";
        }
		
		protocolo.setDataInclusao(new Date());
		protocoloDao.save(protocolo);
		
		return "redirect:/";
	}
	
	@RequestMapping("/protocolos")
	public ModelAndView listaProtocolos() {
		ModelAndView mv = new ModelAndView("cruds/consultas/consultaProtocolos");
		Iterable<Protocolo> protocolos = protocoloDao.findAll();
		mv.addObject("protocolos", protocolos);
		return mv;
	}
}
