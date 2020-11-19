package hh.palvelinohjelmointi.elokuvaSovellus.webcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.palvelinohjelmointi.elokuvaSovellus.domain.Elokuva;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.ElokuvaRepository;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.Kategoria;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.KategoriaRepository;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.Ohjaaja;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.OhjaajaRepository;

@CrossOrigin
@Controller
public class RESTController {
	
	@Autowired
	private OhjaajaRepository ohjaajaRepository;
	
	@Autowired
	private ElokuvaRepository elokuvaRepository;
	
	@Autowired
	private KategoriaRepository kategoriaRepository;
	
	
	@RequestMapping(value = "/resthome", method = RequestMethod.GET)
	public String resthome(Model model) {
		return "resthome";
	}
	
	@RequestMapping(value = "/elokuvat", method = RequestMethod.GET)
	public @ResponseBody List<Elokuva> getAllElokuvat() {
		return (List<Elokuva>) elokuvaRepository.findAll();
	}
	
	@RequestMapping(value = "/ohjaajat", method = RequestMethod.GET)
	public @ResponseBody List<Ohjaaja> getAllOhjaajat() {
		return (List<Ohjaaja>) ohjaajaRepository.findAll();
	}
	
	@RequestMapping(value = "/kategoriat", method = RequestMethod.GET)
	public @ResponseBody List<Kategoria> getAllKategoriat() {
		return (List<Kategoria>) kategoriaRepository.findAll();
	}
	
	@RequestMapping(value = "/kategoria/{id}/elokuvat", method = RequestMethod.GET)
	public @ResponseBody List<Elokuva> getKategorianElokuvat(@PathVariable Long id) {
		Iterable<Elokuva> all = elokuvaRepository.findAll();
		List<Elokuva> kategorianElokuvat = new ArrayList<>();
		for (Elokuva elokuva : all) {
			if (elokuva.getKategoria().getKategoria_id() == id) {
				kategorianElokuvat.add(elokuva);
			}
		}
		return kategorianElokuvat;
	}
	
	@RequestMapping(value = "/ohjaaja/{id}/elokuvat")
	public @ResponseBody List<Elokuva> getOhjaajanElokuvat(@PathVariable Long id) {
		Iterable<Elokuva> all = elokuvaRepository.findAll();
		List<Elokuva> ohjaajanElokuvat = new ArrayList<>();
		for (Elokuva elokuva : all) {
			if (elokuva.getOhjaaja().getOhjaaja_id() == id) {
				ohjaajanElokuvat.add(elokuva);
			}
		}
		return ohjaajanElokuvat;
	}
	
	@RequestMapping(value="/elokuva", method = RequestMethod.POST)
	 public @ResponseBody Elokuva saveElokuvaRest(@RequestBody Elokuva elokuva) {	
	    	return elokuvaRepository.save(elokuva);
	 }
	
	@RequestMapping(value="/ohjaaja", method = RequestMethod.POST)
	 public @ResponseBody Ohjaaja saveOhjaajaRest(@RequestBody Ohjaaja ohjaaja) {	
	    	return ohjaajaRepository.save(ohjaaja);
	 }
	
	@RequestMapping(value="/kategoria", method = RequestMethod.POST)
	 public @ResponseBody Kategoria saveKategoriaRest(@RequestBody Kategoria kategoria) {	
	    	return kategoriaRepository.save(kategoria);
	 }
}
