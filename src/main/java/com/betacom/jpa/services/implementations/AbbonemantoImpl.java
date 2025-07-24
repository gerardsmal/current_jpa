package com.betacom.jpa.services.implementations;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repositories.IAbbonamentoRepository;
import com.betacom.jpa.repositories.ISocioRepository;
import com.betacom.jpa.requests.AbbonamentoReq;
import com.betacom.jpa.services.interfaces.IAbbonamentoServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AbbonemantoImpl implements IAbbonamentoServices{

	private IAbbonamentoRepository abboR;
	private ISocioRepository socioR;
	
	public AbbonemantoImpl(IAbbonamentoRepository abboR, ISocioRepository socioR) {
		this.abboR = abboR;
		this.socioR = socioR;
	}

	@Override
	public void create(AbbonamentoReq req) throws AcademyException {
		log.debug("create :" + req);
		Optional<Socio> s = socioR.findById(req.getSocioId());
		if (s.isEmpty())
			throw new AcademyException("Socio non presente id database:" + req.getSocioId());
		if (req.getDataIscrizione() == null)
			throw new AcademyException("Data iscrizione non presente");
		
		Abbonamento abbo = new Abbonamento();
		abbo.setDataIscrizione(req.getDataIscrizione());
		abbo.setSocio(s.get());
		
		abboR.save(abbo);
	}



}
