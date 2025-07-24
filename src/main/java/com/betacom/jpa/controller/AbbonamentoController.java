package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.requests.AbbonamentoReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.services.interfaces.IAbbonamentoServices;

@RestController
@RequestMapping("/rest/abbonamento")
public class AbbonamentoController {

	private IAbbonamentoServices abboS;

	public AbbonamentoController(IAbbonamentoServices abboS) {
		this.abboS = abboS;
	}

	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true)  AbbonamentoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			abboS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}


	
}
