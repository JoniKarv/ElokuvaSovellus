package hh.palvelinohjelmointi.elokuvaSovellus.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import hh.palvelinohjelmointi.elokuvaSovellus.domain.Ohjaaja;

@Entity
public class Elokuva {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long elokuva_id;
	
	@NotNull
	@Size(min=1, max=50)
	String nimi;
	
	@NotNull
	@Size(min=1, max=5)
	String ikaraja;
	
	@NotNull
	@Size(min=1, max=100)
	String kuvaus;
	
	@ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ohjaaja_id")
    private Ohjaaja ohjaaja;
	
	@ManyToOne
    @JsonIgnore
    @JoinColumn(name = "kategoria_id")
    private Kategoria kategoria;

	public Elokuva(String nimi, String ikaraja, String kuvaus) {
		super();
		this.nimi = nimi;
		this.ikaraja = ikaraja;
		this.kuvaus = kuvaus;
	
	}
	
	public Elokuva(String nimi, String ikaraja, String kuvaus, Ohjaaja ohjaaja) {
		super();
		this.nimi = nimi;
		this.ikaraja = ikaraja;
		this.kuvaus = kuvaus;
		this.ohjaaja = ohjaaja;
	}
	
	public Elokuva(String nimi, String ikaraja, String kuvaus, Ohjaaja ohjaaja, Kategoria kategoria) {
		super();
		this.nimi = nimi;
		this.ikaraja = ikaraja;
		this.kuvaus = kuvaus;
		this.ohjaaja = ohjaaja;
		this.kategoria = kategoria;
	}

	public Elokuva(String kuvaus, Ohjaaja ohjaaja) {
		super();
		this.kuvaus = kuvaus;
		this.ohjaaja = ohjaaja;
	}
	
	public Elokuva() {
		
	}

	public Kategoria getKategoria() {
		return kategoria;
	}

	public void setKategoria(Kategoria kategoria) {
		this.kategoria = kategoria;
	}

	public Long getElokuva_id() {
		return elokuva_id;
	}

	public void setElokuva_id(Long elokuva_id) {
		this.elokuva_id = elokuva_id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getIkaraja() {
		return ikaraja;
	}

	public void setIkaraja(String ikaraja) {
		this.ikaraja = ikaraja;
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public Ohjaaja getOhjaaja() {
		return ohjaaja;
	}

	public void setOhjaaja(Ohjaaja ohjaaja) {
		this.ohjaaja = ohjaaja;
	}

	@Override
	public String toString() {
		return "elokuva [nimi=" + nimi + ", ikaraja=" + ikaraja + ", kuvaus=" + kuvaus + ", ohjaaja=" + ohjaaja + "]";
	}
	
}
