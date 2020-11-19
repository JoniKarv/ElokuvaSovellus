package hh.palvelinohjelmointi.elokuvaSovellus;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import hh.palvelinohjelmointi.elokuvaSovellus.domain.Ohjaaja;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.OhjaajaRepository;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.User;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.UserRepository;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.Elokuva;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.ElokuvaRepository;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.Kategoria;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.KategoriaRepository;

@SpringBootApplication
public class ElokuvaSovellusApplication extends SpringBootServletInitializer implements WebMvcConfigurer {
	private static final Logger log = LoggerFactory.getLogger(ElokuvaSovellusApplication.class);

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ElokuvaSovellusApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ElokuvaSovellusApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(Locale.US);
	    return slr;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("lang");
	    return lci;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor());
	}
	
	@Bean
	public CommandLineRunner demo(OhjaajaRepository ohjaajarepo, ElokuvaRepository elokuvarepo, KategoriaRepository kategoriarepo, UserRepository userRepository) {
		return(args)-> {
			
			log.info("luodaan ohjaaja");
			Ohjaaja ohjaaja1 = new Ohjaaja("Veiko Ounpuu");
			Ohjaaja ohjaaja2 = new Ohjaaja("Mark Waters");
			Ohjaaja ohjaaja3 = new Ohjaaja("Jenni Toivoniemi");
			Ohjaaja ohjaaja4 = new Ohjaaja("Zaida Bergroth");
			Ohjaaja ohjaaja5 = new Ohjaaja("Christopher Landon");
			Ohjaaja ohjaaja6 = new Ohjaaja("Will Wernick");
			Ohjaaja ohjaaja7 = new Ohjaaja("Ville Jankeri");
			Ohjaaja ohjaaja8 = new Ohjaaja("Virpi Suutari");
			
			ohjaajarepo.save(ohjaaja1);
			ohjaajarepo.save(ohjaaja2);
			ohjaajarepo.save(ohjaaja3);
			ohjaajarepo.save(ohjaaja4);
			ohjaajarepo.save(ohjaaja5);
			ohjaajarepo.save(ohjaaja6);
			ohjaajarepo.save(ohjaaja7);
			ohjaajarepo.save(ohjaaja8);
			
			log.info("luodaan pari kategoriaa");
			Kategoria kategoria1 = new Kategoria("Komedia");
			Kategoria kategoria2 = new Kategoria("Kauhu");
			Kategoria kategoria3 = new Kategoria("Trilleri");
			Kategoria kategoria4 = new Kategoria("Draama");
			Kategoria kategoria5 = new Kategoria("Sota");
			Kategoria kategoria6 = new Kategoria("Jännitys");
			Kategoria kategoria7 = new Kategoria("Dokumentti");
			
			kategoriarepo.save(kategoria1);
			kategoriarepo.save(kategoria2);
			kategoriarepo.save(kategoria3);
			kategoriarepo.save(kategoria4);
			kategoriarepo.save(kategoria5);
			kategoriarepo.save(kategoria6);
			kategoriarepo.save(kategoria7);
			
			log.info("luodaan pari elokuvaa");
			Elokuva leffa1 = new Elokuva("Viimeiset", "K16", "Pohjoinen western, jonka tapahtumat sijoittuvat Lapin kesyttömille tunturialueille.", ohjaaja1, kategoria4);
			Elokuva leffa2 = new Elokuva("Honest Thief", "K16", "Tom Carter on rikollinen, joka on onnistunut välttymään kiinnijäämiseltä jo vuosien ajan.", ohjaaja2, kategoria6);
			Elokuva leffa3 = new Elokuva("Seurapeli", "K12", "Seurapeli on draamakomedia pitkitetystä nuoruudesta ja rooleista, joihin ajaudumme.", ohjaaja3, kategoria4);
			Elokuva leffa4 = new Elokuva("Tove", "K12", "Sodan päättyminen antaa taidemaalari Tove Janssonille taiteellista ja sosiaalista vapautta.", ohjaaja4, kategoria4);
			Elokuva leffa5 = new Elokuva("Freaky", "K16", "Kauhukomediassa Freaky teinityttö vaihtaa kehoja säälimättömän sarjamurhaajan kanssa!", ohjaaja5, kategoria2);
			Elokuva leffa6 = new Elokuva("Follow Me", "K16", "Uudessa, hyytävässä kauhuelokuvassa sosiaalisen median tähti matkustaa ystävineen Moskovaan.", ohjaaja6, kategoria2);
			Elokuva leffa7 = new Elokuva("Metsäjätti", "K7", "Ylennys mielessään Pasi ottaa vastaan tehtävän tehostaa vaneritehtaan toimintaa.", ohjaaja7, kategoria4);
			Elokuva leffa8 = new Elokuva("Aalto", "K7", "Elokuva arkkitehti Alvar ja Aino Aallosta sekä heidän teoksistaan ympäri maailmaa.", ohjaaja8, kategoria7);
			
			User user = new User("admin", "$2a$10$QbafKD4U.0RzjCY7TB7ulOtyvP0LbWmt598UUcfeWxQTV.PYiecJu", "ROLE_ADMIN");
			userRepository.save(user);
			
			log.info("tallennetaan repoon");
			elokuvarepo.save(leffa1);
			elokuvarepo.save(leffa2);
			elokuvarepo.save(leffa3);
			elokuvarepo.save(leffa4);
			elokuvarepo.save(leffa5);
			elokuvarepo.save(leffa6);
			elokuvarepo.save(leffa7);
			elokuvarepo.save(leffa8);

		};
		
	}

}
