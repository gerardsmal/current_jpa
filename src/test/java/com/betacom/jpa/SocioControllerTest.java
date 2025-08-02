package com.betacom.jpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.jpa.controller.SocioController;
import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.requests.SocioReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.response.ResponseObject;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SocioControllerTest {
	
	@Autowired
	private SocioController socioC; 

	@Test
	@Order(1)
	public void listTest() {
		log.debug("Test list socio" );
		
		ResponseList<SocioDTO> r = socioC.list(null, "Paolo", null, null);
		
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		Assertions.assertThat(r.getDati().get(0).getCognome()).isEqualTo("Verde");
		
	}
	
	@Test
	@Order(2)
	public void getSocioTest() {
		log.debug("Test Get socio" );
		
		ResponseObject<SocioDTO> r = socioC.getSocio(1);
		
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		Assertions.assertThat(r.getDati().getCognome()).isEqualTo("Verde");
		
	}
	
	@Test
	@Order(3)
	public void getSocioTestError() {
		log.debug("Test Get socio in errore" );
		
		ResponseObject<SocioDTO> r = socioC.getSocio(99);
		
		Assertions.assertThat(r.getRc()).isEqualTo(false);
		Assertions.assertThat(r.getMsg()).isEqualTo("socio-not-exist");
		
	}
	
	@Test
	@Order(4)
	public void uodateSocioTest() {
		log.debug("Test Update socio" );
		SocioReq req = new SocioReq();
		req.setId(2);
		req.setNome("Enrico");
		req.setCodiceFiscale("ENR999FVZ");
		
		ResponseBase r = socioC.update(req);
		
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
		ResponseObject<SocioDTO> s = socioC.getSocio(2);
		Assertions.assertThat(s.getRc()).isEqualTo(true);
		Assertions.assertThat(s.getDati().getNome()).isEqualTo("Enrico");
		Assertions.assertThat(s.getDati().getCodiceFiscale()).isEqualTo("ENR999FVZ");
		
	}
	@Test
	@Order(5)
	public void createSocioTest(){
		log.debug("Test Create socio" );
		SocioReq req = new SocioReq();
		req.setCodiceFiscale("AQSJDJOSNO");
		req.setCognome("Viola");
		req.setNome("Anna");
		req.setEmail("av@tin.it");
		
		ResponseBase r = socioC.create(req);

		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
	}
		
}
