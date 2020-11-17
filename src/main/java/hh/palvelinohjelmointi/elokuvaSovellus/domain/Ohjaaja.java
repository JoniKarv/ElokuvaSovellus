package hh.palvelinohjelmointi.elokuvaSovellus.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

@Entity
public class Ohjaaja {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ohjaaja_id;
	@NotNull
	@Size(min=1, max=50)
	private String nimi;

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ohjaaja")
	private List<Elokuva> elokuvat;

	public Ohjaaja() {

	}
	
	public Ohjaaja(String nimi, List<Elokuva> elokuvat) {
		this.nimi = nimi;
		this.elokuvat = elokuvat;
	}

	public Ohjaaja(String nimi) {
		this.nimi = nimi;
	}

	public Long getOhjaaja_id() {
		return ohjaaja_id;
	}

	public void setOhjaaja_id(Long ohjaaja_id) {
		this.ohjaaja_id = ohjaaja_id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public List<Elokuva> getElokuvat() {
		return elokuvat;
	}

	public void setElokuvat(List<Elokuva> elokuvat) {
		this.elokuvat = elokuvat;
	}

}
