package com.betacom.jpa;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.jpa.controller.CertificateController;
import com.betacom.jpa.requests.CertificatoReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.services.interfaces.ICertificatoServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CertificatoControllerTest {
	
	@Autowired
	private CertificateController certC;

	
	@Test
	@Order(1)
	public void createCertificatoTest() {
		log.debug("Test create certificato");
		
		CertificatoReq req = new CertificatoReq();
		req.setDataCertificato(LocalDate.now());
		req.setTipo(false);
		req.setSocioId(1);
		
		ResponseBase r = certC.create(req);
		
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
	}
}
