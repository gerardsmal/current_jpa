package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.services.interfaces.ISubmitServices;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/submit")
@Slf4j
public class SubmitBatchController {

	private ISubmitServices subS;

	public SubmitBatchController(ISubmitServices subS) {
		this.subS = subS;
	}

	@GetMapping("/batch")
	public ResponseBase submit() {
		log.debug("submit");
		ResponseBase r = new ResponseBase();
		try {
			subS.run();
			r.setRc(true);
		} catch (AcademyException e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
		
	}
	
	
}
