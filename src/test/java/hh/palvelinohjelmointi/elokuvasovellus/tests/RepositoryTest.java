package hh.palvelinohjelmointi.elokuvasovellus.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.palvelinohjelmointi.elokuvaSovellus.domain.Elokuva;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.ElokuvaRepository;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.Kategoria;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.KategoriaRepository;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.Ohjaaja;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.OhjaajaRepository;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.User;
import hh.palvelinohjelmointi.elokuvaSovellus.domain.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RepositoryTest {

    @Autowired
    private ElokuvaRepository repository;
    
    @Autowired
    private OhjaajaRepository Orepository;
    
    @Autowired
    private KategoriaRepository Krepository;
    
    @Autowired
	private UserRepository URepository;
    
    
    @Test //ElokuvaRepositoryn save() testi
    public void createNewElokuva() {
    	Ohjaaja ohjaaja = new Ohjaaja("Testi Ohjaaja");
    	Kategoria kategoria = new Kategoria("Test");
    	Elokuva elokuva = new Elokuva("Testi", "Testi", "Testikuvaus", ohjaaja, kategoria);
    	repository.save(elokuva);
    	assertThat(elokuva.getElokuva_id()).isNotNull();
    }    
    
    @Test //OhjaajaRepositoryn save() testi
    public void createNewOhjaaja() {
    	Ohjaaja ohjaaja = new Ohjaaja("Testi Ohjaaja");
    	Orepository.save(ohjaaja);
    	assertThat(ohjaaja.getOhjaaja_id()).isNotNull();
    }  
    
    @Test //KategoriaRepositoryn save() testi
    public void createNewKategoria() {
    	Kategoria kategoria = new Kategoria("Testi");
    	Krepository.save(kategoria);
    	assertThat(kategoria.getKategoria_id()).isNotNull();
    }  
    
    @Test //UserRepositoryn save() testi
    public void createNewUser() {
    	User user = new User("admin2", "$2a$10$QbafKD4U.0RzjCY7TB7ulOtyvP0LbWmt598UUcfeWxQTV.PYiecJu", "ROLE_ADMIN");
		URepository.save(user);
    	assertThat(user.getId()).isNotNull();
    } 
    
    @Test //ElokuvaRepositoryn FindByOhjaaja testi
    public void findByOhjaajaTest() {
    	Ohjaaja ohjaaja = new Ohjaaja("Testi Ohjaaja");
    	Kategoria kategoria = new Kategoria("Test");
    	Elokuva elokuva = new Elokuva("Testi", "Testi", "Testikuvaus", ohjaaja, kategoria);
    	repository.save(elokuva);
    	Orepository.save(ohjaaja);
    	Krepository.save(kategoria);
    	List<Elokuva> elokuvat = repository.findByOhjaaja(ohjaaja);
    	assertThat(elokuvat).hasSize(1);
    	assertThat(elokuvat.get(0).getNimi()).isEqualTo("Testi");
    }
    
    @Test //KategoriaRepositoryn FindByNimi testi
    public void findByNimiTest() {
    	Kategoria kategoria = new Kategoria("Test");
    	Krepository.save(kategoria);
    	List<Kategoria> kategoriat = Krepository.findByNimi("Test");
    	assertThat(kategoriat).hasSize(1);
    	assertThat(kategoriat.get(0).getNimi()).isEqualTo("Test");
    }
    
    @Test //OhjaajaRepositoryn FindByNimi testi
    public void ohjaajaFindByNimiTest() {
    	Ohjaaja ohjaaja = new Ohjaaja("Testi Ohjaaja");
    	Orepository.save(ohjaaja);
    	List<Ohjaaja> ohjaajat = Orepository.findByNimi("Testi Ohjaaja");
    	assertThat(ohjaajat).hasSize(1);
    	assertThat(ohjaajat.get(0).getNimi()).isEqualTo("Testi Ohjaaja");
    }
    
    @Test //UserRepositoryn FindByUsername testi
    public void findByUsernameTest() {
    	User user = new User("admin2", "$2a$10$QbafKD4U.0RzjCY7TB7ulOtyvP0LbWmt598UUcfeWxQTV.PYiecJu", "ROLE_ADMIN");
		URepository.save(user);
		User kayttaja = URepository.findByUsername("admin2");
		assertThat(kayttaja.getUsername()).isNotNull();
		assertThat(kayttaja.getUsername()).isEqualTo("admin2");
    }
}