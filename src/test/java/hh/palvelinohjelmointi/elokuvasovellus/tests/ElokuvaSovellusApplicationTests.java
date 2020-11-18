package hh.palvelinohjelmointi.elokuvasovellus.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.palvelinohjelmointi.elokuvaSovellus.webcontroller.ElokuvaController;
import hh.palvelinohjelmointi.elokuvaSovellus.webcontroller.KategoriaController;
import hh.palvelinohjelmointi.elokuvaSovellus.webcontroller.OhjaajaController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ElokuvaSovellusApplicationTests {

	@Autowired
	private ElokuvaController elokuvaController;
	@Autowired
	private KategoriaController kategoriaController;
	@Autowired
	private OhjaajaController ohjaajaController;

	
	@Test
	public void contextLoads() {
		assertThat(elokuvaController).isNotNull();
		assertThat(kategoriaController).isNotNull();
		assertThat(ohjaajaController).isNotNull();
	}

}
