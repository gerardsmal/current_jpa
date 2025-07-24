package com.betacom.jpa.services.interfaces;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.AbbonamentoReq;

public interface IAbbonamentoServices {

	void create(AbbonamentoReq req) throws AcademyException;
}
