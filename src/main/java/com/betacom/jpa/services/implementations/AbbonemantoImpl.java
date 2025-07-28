package com.betacom.jpa.services.implementations;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.AbbonamentoDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repositories.IAbbonamentoRepository;
import com.betacom.jpa.repositories.ISocioRepository;
import com.betacom.jpa.requests.AbbonamentoReq;
import com.betacom.jpa.services.interfaces.IAbbonamentoServices;
import com.betacom.jpa.utils.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AbbonemantoImpl extends Utilities  implements IAbbonamentoServices{

	private IAbbonamentoRepository abboR;
	private ISocioRepository socioR;
	
	public AbbonemantoImpl(IAbbonamentoRepository abboR, ISocioRepository socioR) {
		this.abboR = abboR;
		this.socioR = socioR;
	}
	
	@Transactional(rollbackFor = Exception.class)
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
	
	@Override
	public AbbonamentoDTO getById(Integer id) throws AcademyException {
		Optional<Abbonamento> ab = abboR.findById(id);
		if (ab.isEmpty())
			throw new AcademyException("Abbonamento non presente id database:" + id);
		
		Abbonamento a = ab.get();
		
		return AbbonamentoDTO.builder()
				.id(a.getId())
				.dataIscrizione(a.getDataIscrizione())
				.attivita(buildAttivita(a.getAttivita()))
				.build();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void remove(AbbonamentoReq req) throws AcademyException {
		Optional<Abbonamento> ab = abboR.findById(req.getId());
		if (ab.isEmpty())
			throw new AcademyException("Abbonamento non presente id database:" + req.getId());
				
		if (!ab.get().getAttivita().isEmpty()) {
			ab.get().getAttivita().removeAll(ab.get().getAttivita());
			abboR.save(ab.get());
		}

		abboR.delete(ab.get());
	}



}
