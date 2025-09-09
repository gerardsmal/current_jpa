package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.AttivitaDTO;
import com.betacom.jpa.dto.PersoneDTO;
import com.betacom.jpa.requests.AttivitaReq;
import com.betacom.jpa.requests.PersoneReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.response.ResponseObject;
import com.betacom.jpa.services.interfaces.IAttivitaServices;
import com.betacom.jpa.services.interfaces.IPersoneServices;

@RestController
@RequestMapping("/rest/persone")
public class PersoneController {

	
	private IPersoneServices perS;

	public PersoneController(IPersoneServices perS) {
		this.perS = perS;
	}
	
	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true)  PersoneReq req) {
		ResponseBase r = new ResponseBase();
		try {
			perS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true)  PersoneReq req) {
		ResponseBase r = new ResponseBase();
		try {
			perS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@PostMapping("delete")
	public ResponseBase delete(@RequestBody (required = true)  PersoneReq req) {
		ResponseBase r = new ResponseBase();
		try {
			perS.remove(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	
	@GetMapping("/list")
	public ResponseList<PersoneDTO> list() {
		ResponseList<PersoneDTO> r = new ResponseList<PersoneDTO>();
		try {
			r.setDati(perS.list());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getById")
	public ResponseObject<PersoneDTO> getById(@RequestParam(required = true) Integer id) {
		ResponseObject<PersoneDTO> r = new ResponseObject<PersoneDTO>();
		try {
			r.setDati(perS.getById(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

}
