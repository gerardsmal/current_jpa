package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.PersoneDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.PersoneReq;

public interface IPersoneServices {
	void create(PersoneReq req) throws AcademyException;
	void update(PersoneReq req) throws AcademyException;
	void remove(PersoneReq req) throws AcademyException;
	
	List<PersoneDTO> list();
	PersoneDTO getById(Integer id) throws AcademyException;

}
