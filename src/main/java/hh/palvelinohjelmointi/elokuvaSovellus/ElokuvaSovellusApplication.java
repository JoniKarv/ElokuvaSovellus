package hh.palvelinohjelmointi.elokuvaSovellus;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import hh.palvelinohjelmointi.elokuvaSovellus.domain.Ohjaaja;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.OhjaajaRepository;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.User;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.UserRepository;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.Elokuva;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.ElokuvaRepository;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.Kategoria;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.KategoriaRepository;

@SpringBootApplication
public class ElokuvaSovellusApplication extends SpringBootServletInitializer {
	private static final Logger log = LoggerFactory.getLogger(ElokuvaSovellusApplication.class);

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ElokuvaSovellusApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ElokuvaSovellusApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(OhjaajaRepository ohjaajarepo, ElokuvaRepository elokuvarepo, KategoriaRepository kategoriarepo, UserRepository userRepository) {
		return(args)-> {
			
			log.info("luodaan ohjaaja");
			Ohjaaja ekaohjaaja = new Ohjaaja("Matti Mies");
			ohjaajarepo.save(ekaohjaaja);
			
			log.info("luodaan toinen ohjaaja");
			Ohjaaja tokaohjaaja = new Ohjaaja("Mies Matti");
			ohjaajarepo.save(tokaohjaaja);
			
			log.info("luodaan pari kategoriaa");
			Kategoria kategoria1 = new Kategoria("Komedia");
			Kategoria kategoria2 = new Kategoria("Kauhu");
			Kategoria kategoria3 = new Kategoria("Trilleri");
			Kategoria kategoria4 = new Kategoria("Draama");
			Kategoria kategoria5 = new Kategoria("Sota");
			
			kategoriarepo.save(kategoria1);
			kategoriarepo.save(kategoria2);
			kategoriarepo.save(kategoria3);
			kategoriarepo.save(kategoria4);
			kategoriarepo.save(kategoria5);
			
			log.info("luodaan pari elokuvaa");
			Elokuva eka = new Elokuva("Elokuva1", "K13", "Matti tykkää Teposta, mutta tykkääkö Teppo Matista", ekaohjaaja, kategoria1);
			Elokuva toka = new Elokuva("Elokuva2", "K18", "asd", ekaohjaaja, kategoria2);
			Elokuva kolmas = new Elokuva("Elokuva3", "K6", "asd", ekaohjaaja, kategoria3);
			Elokuva nelkku = new Elokuva("Elokuva4", "K8", "asd", ekaohjaaja, kategoria4);
			Elokuva viides = new Elokuva("Elokuva5", "K18", "asd", ekaohjaaja, kategoria1);
			Elokuva neljas = new Elokuva("Elokuva6", "K16", "asd", tokaohjaaja, kategoria5);
			
			User user1 = new User("user", "$2b$10$ELCW1a6XgQdEyE0VokWOP.I09koY3sz9SVDUnPrJNmNaWmyLeLKUC", "ROLE_USER");
			User user2 = new User("admin", "$2b$10$pUGoA3PR3x9ohMfXFP27eeyuSXB4UFvgRugSAwlvduiBBWyCyQTse", "ROLE_ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			
			log.info("tallennetaan repoon");
			elokuvarepo.save(eka);
			elokuvarepo.save(toka);
			elokuvarepo.save(kolmas);
			elokuvarepo.save(nelkku);
			elokuvarepo.save(viides);
			elokuvarepo.save(neljas);
		};
		
	}

}
