package com.betacom.jpa;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.requests.SocioReq;
import com.betacom.jpa.services.interfaces.ISocioServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SocioServicesTest {
	
	@Autowired
	private ISocioServices socioS;
	
	@Test
	@Order(1)
	public void createSocioTest() throws Exception {
		log.debug("create socio");
		SocioReq req = new SocioReq();
		req.setCodiceFiscale("PW12345LV");
		req.setCognome("Verde");
		req.setNome("Paolo");
		req.setEmail("aa@tin.it");
		
		socioS.insert(req);
		
		
		List<SocioDTO> lS = socioS.list(null, null, null, null);
		
		SocioDTO createSocio = lS.stream()
				.filter(s -> "PW12345LV".equals(s.getCodiceFiscale()))
				.findFirst()
				.orElseThrow(() -> new AssertionError("Socio non trovato"));
		
		Assertions.assertThat(createSocio.getCognome()).isEqualTo("Verde");
		
		req = new SocioReq();
		req.setCodiceFiscale("PW12345LW");
		req.setCognome("Rosso");
		req.setNome("Andrea");
		req.setEmail("bb@tin.it");
		
		socioS.insert(req);
		
		
		lS = socioS.list(null, null, null, null);
		
		lS.forEach(s -> log.debug(s.toString()));
		
	}

	@Test
	@Order(2)
	public void createSocioErrorTest() throws Exception{

		SocioReq req = new SocioReq();
		req.setCodiceFiscale("PW12345LV");
		req.setCognome("Verde");
		req.setNome("Paolo");
		req.setEmail("aa@tin.it");

		assertThrows(Exception.class, () -> {
			socioS.insert(req);
		});
	}
}
