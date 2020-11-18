package hh.palvelinohjelmointi.elokuvaSovellus.webcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.palvelinohjelmointi.elokuvaSovellus.domain.Elokuva;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.ElokuvaRepository;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.Kategoria;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.KategoriaRepository;

@CrossOrigin
@Controller
public class KategoriaController {
	
	@Autowired
	private KategoriaRepository kategoriaRepository;

	@Autowired
	private ElokuvaRepository elokuvaRepository;
	
	@RequestMapping("/kategorialist")
	public String kategoriaList(Model model) {
		model.addAttribute("kategoriat", kategoriaRepository.findAll());
		return "kategorialist";
	}

	// Lisää kategorian
	@RequestMapping(value = "/addkategoria")
	@PreAuthorize("hasRole('ADMIN')")
	public String addkategoria(Model model) {
		model.addAttribute("kategoria", new Kategoria());
		return "luokategoria";
	}

	// tallentaa kategorian
	@RequestMapping(value = "/savekategoria", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String save(@Valid Kategoria kategoria, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "luokategoria";
		}else {
			kategoriaRepository.save(kategoria);
			return "redirect:/kategorialist";
		}
	}

	@RequestMapping(value = "/deletekategoria/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteKategoria(@PathVariable("id") Long kategoria_id, Model model) {
		kategoriaRepository.deleteById(kategoria_id);
		return "redirect:/kategorialist";
	}
	
	@RequestMapping(value = "/kategoriat/{id}/elokuvat")
	public String kategorianElokuvat(@PathVariable("id") Long id, Model model) {
		Iterable<Elokuva> all = elokuvaRepository.findAll();
		List<Elokuva> kategorianElokuvat = new ArrayList<>();
		for (Elokuva elokuva : all) {
			if (elokuva.getKategoria().getKategoria_id() == id) {
				kategorianElokuvat.add(elokuva);
			}
		}
		Optional<Kategoria> kategoriaopt = kategoriaRepository.findById(id);
		Kategoria kategoria = kategoriaopt.get();
		model.addAttribute("kategoria", kategoria);
		model.addAttribute("elokuvat", kategorianElokuvat);
		return "kategorianelokuvat";
	}

}
