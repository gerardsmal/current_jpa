package com.betacom.jpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.requests.SocioReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.response.ResponseObject;
import com.betacom.jpa.services.interfaces.ISocioServices;



@RestController
@RequestMapping("/rest/socio")
public class SocioController {
	
	private ISocioServices socioS;

	public SocioController(ISocioServices socioS) {
		this.socioS = socioS;
	}

	
	@GetMapping("/list")
	public ResponseList<SocioDTO> list() {
		ResponseList<SocioDTO> r = new ResponseList<SocioDTO>();
		try {
			r.setDati(socioS.listAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@GetMapping("/getSocio")
	public ResponseObject<SocioDTO> getSocio(@RequestParam(required = true) Integer id) {
		ResponseObject<SocioDTO> r = new ResponseObject<SocioDTO>();
		try {
			r.setDati(socioS.getSocio(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true)  SocioReq req) {
		ResponseBase r = new ResponseBase();
		try {
			socioS.insert(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@DeleteMapping("delete")
	public ResponseBase delete(@RequestBody (required = true)  SocioReq req) {
		ResponseBase r = new ResponseBase();
		try {
			socioS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true)  SocioReq req) {
		ResponseBase r = new ResponseBase();
		try {
			socioS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
}
