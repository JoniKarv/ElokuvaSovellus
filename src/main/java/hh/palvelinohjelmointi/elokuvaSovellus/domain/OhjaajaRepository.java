package hh.palvelinohjelmointi.elokuvaSovellus.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OhjaajaRepository extends CrudRepository<Ohjaaja, Long>{
	List<Ohjaaja> findByNimi(String nimi);
}
