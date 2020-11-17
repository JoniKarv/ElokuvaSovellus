package hh.palvelinohjelmointi.elokuvaSovellus.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ElokuvaRepository extends CrudRepository<Elokuva, Long> {
	List<Elokuva> findByOhjaaja(Ohjaaja ohjaaja);
}
