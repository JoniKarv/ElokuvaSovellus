package hh.palvelinohjelmointi.elokuvaSovellus.webcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.palvelinohjelmointi.elokuvaSovellus.domain.Ohjaaja;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.OhjaajaRepository;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.Elokuva;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.ElokuvaRepository;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.Kategoria;

@CrossOrigin
@Controller
public class OhjaajaController {

	@Autowired
	private OhjaajaRepository ohjaajaRepository;

	@Autowired
	private ElokuvaRepository elokuvaRepository;

	// Listaa ohjaajat
	@RequestMapping("/ohjaajalist")
	public String ohjaajaList(Model model) {
		model.addAttribute("ohjaajat", ohjaajaRepository.findAll());
		return "ohjaajalist";
	}

	// Lisää ohjaajan
	@RequestMapping(value = "/add")
	@PreAuthorize("hasRole('ADMIN')")
	public String addohjaaja(Model model) {
		model.addAttribute("ohjaaja", new Ohjaaja());
		return "luoohjaaja";
	}

	// tallentaa ohjaajan
	@RequestMapping(value = "/saveohjaaja", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String save(@Valid Ohjaaja ohjaaja, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "luoohjaaja";
		}else{
			ohjaajaRepository.save(ohjaaja);
			return "redirect:/ohjaajalist";
		}
	}

	@RequestMapping(value = "/ohjaajat/{id}/elokuvat")
	public String kategorianElokuvat(@PathVariable("id") Long id, Model model) {
		Iterable<Elokuva> all = elokuvaRepository.findAll();
		List<Elokuva> ohjaajanElokuvat = new ArrayList<>();
		for (Elokuva elokuva : all) {
			if (elokuva.getOhjaaja().getOhjaaja_id() == id) {
				ohjaajanElokuvat.add(elokuva);
			}
		}
		Optional<Ohjaaja> ohjaajaopt = ohjaajaRepository.findById(id);
		Ohjaaja ohjaaja = ohjaajaopt.get();
		model.addAttribute("ohjaaja", ohjaaja);
		model.addAttribute("elokuvat", ohjaajanElokuvat);
		return "ohjaajanelokuvat";
	}

	@RequestMapping(value = "/deleteohjaaja/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteOhjaaja(@PathVariable("id") Long id, Model model) {
		ohjaajaRepository.deleteById(id);
		return "redirect:/ohjaajalist";
	}

}
