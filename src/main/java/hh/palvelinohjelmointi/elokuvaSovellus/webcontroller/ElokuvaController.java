package hh.palvelinohjelmointi.elokuvaSovellus.webcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@RequestMapping(value="/login")
	public String login() {
		return "Login";
	}   
	
	@RequestMapping("/")
	public String elokuvaList(Model model) {
		model.addAttribute("elokuvat", elokuvaRepository.findAll());
		return "elokuvalist";
	}
	
	@GetMapping(value = "/addelokuva")
	@PreAuthorize("hasRole('ADMIN')")
	public String addohjaaja(Model model, Long elokuva_id) {
			model.addAttribute("elokuva", new Elokuva());
			model.addAttribute("Kategoriat", kategoriaRepository.findAll());
			model.addAttribute("Ohjaajat", ohjaajaRepository.findAll());
			return "luoelokuva";
	}
	
	//Jos vastaanotettu elokuva ei ole validi, ohjaa uuden elokuvan luontiin jos
	//elokuvalla ei ole id:tä ja editointiin jos elokuvalla on id
	@PostMapping(value = "/saveelokuva")
	@PreAuthorize("hasRole('ADMIN')")
	public String save(@Valid Elokuva elokuva, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			if (elokuva.getElokuva_id() == null) {
				model.addAttribute("Kategoriat", kategoriaRepository.findAll());
				model.addAttribute("Ohjaajat", ohjaajaRepository.findAll());
				return "luoelokuva";
			}else {
				model.addAttribute("elokuva", elokuva);
				model.addAttribute("Kategoriat", kategoriaRepository.findAll());
				model.addAttribute("Ohjaajat", ohjaajaRepository.findAll());
				return "editelokuva";
			}
		}else {
			elokuvaRepository.save(elokuva);
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/deleteelokuva/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteElokuva(@PathVariable("id") Long id, Model model) {
		elokuvaRepository.deleteById(id);
		return "redirect:/";
	}
	
	@GetMapping(value = "/edit/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String editElokuva(@PathVariable("id") Long id, Model model) {
		model.addAttribute("elokuva", elokuvaRepository.findById(id));
		model.addAttribute("Kategoriat", kategoriaRepository.findAll());
		model.addAttribute("Ohjaajat", ohjaajaRepository.findAll());
		return "editelokuva";
	}
}
