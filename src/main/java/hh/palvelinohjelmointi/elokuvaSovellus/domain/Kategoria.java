package hh.palvelinohjelmointi.elokuvaSovellus.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Kategoria {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long kategoria_id;
	@NotNull
	@Size(min=1, max=50)
	private String nimi;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kategoria")
	private List<Elokuva> elokuvat;

	public Kategoria(String nimi, List<Elokuva> elokuvat) {
		super();
		this.nimi = nimi;
		this.elokuvat = elokuvat;
	}
	
	public Kategoria(String nimi) {
		this.nimi = nimi;
	}

	public Kategoria() {
		
	}

	public Long getKategoria_id() {
		return kategoria_id;
	}

	public void setKategoria_id(Long kategoria_id) {
		this.kategoria_id = kategoria_id;
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