package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.SignInDTO;
import com.betacom.jpa.dto.UtenteDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.SignInReq;
import com.betacom.jpa.requests.UtentePwdReq;
import com.betacom.jpa.requests.UtenteReq;

public interface IUtenteServices {
	void create(UtenteReq req) throws AcademyException;
	void update(UtenteReq req) throws AcademyException;
	void updatePwd(UtentePwdReq req) throws AcademyException;
	UtenteDTO remove(UtenteReq req) throws AcademyException;
	
		
	List<UtenteDTO> listAll();
	
	UtenteDTO findById(Integer id) throws AcademyException;
	
	SignInDTO signIn(SignInReq req);
}
