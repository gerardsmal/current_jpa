package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.UtenteDTO;
import com.betacom.jpa.requests.UtenteReq;

public interface IUtenteServices {
	void create(UtenteReq req) throws Exception;
	void update(UtenteReq req) throws Exception;
	void remove(UtenteReq req) throws Exception;
	
		
	List<UtenteDTO> listAll();
	
	UtenteDTO findById(Integer id) throws Exception;
}
