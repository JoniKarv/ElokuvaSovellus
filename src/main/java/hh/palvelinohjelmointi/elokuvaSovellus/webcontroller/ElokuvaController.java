package hh.palvelinohjelmointi.elokuvaSovellus.webcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.palvelinohjelmointi.elokuvaSovellus.domain.Ohjaaja;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.OhjaajaRepository;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.Elokuva;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.ElokuvaRepository;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.KategoriaRepository;

@CrossOrigin
@Controller
public class ElokuvaController{

	@Autowired
	private OhjaajaRepository ohjaajaRepository;
	
	@Autowired
	private ElokuvaRepository elokuvaRepository;
	
	@Autowired
	private KategoriaRepository kategoriaRepository;
	
	@RequestMapping("/")
	public String ohjaajaList(Model model) {
		model.addAttribute("elokuvat", elokuvaRepository.findAll());
		return "elokuvalist";
	}
	
	@RequestMapping(value = "/addelokuva")
	public String addohjaaja(Model model) {
		model.addAttribute("elokuva", new Elokuva());
		model.addAttribute("Kategoriat", kategoriaRepository.findAll());
		model.addAttribute("Ohjaajat", ohjaajaRepository.findAll());
		return "luoelokuva";
	}
	
	@PostMapping(value = "/saveelokuva")
	public String save(@Valid Elokuva elokuva, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("Kategoriat", kategoriaRepository.findAll());
			model.addAttribute("Ohjaajat", ohjaajaRepository.findAll());
			return "luoelokuva";
		}else {
			elokuvaRepository.save(elokuva);
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/deleteelokuva/{id}", method = RequestMethod.GET)
	public String deleteOhjaaja(@PathVariable("id") Long id, Model model) {
		elokuvaRepository.deleteById(id);
		return "redirect:/";
	}
}
