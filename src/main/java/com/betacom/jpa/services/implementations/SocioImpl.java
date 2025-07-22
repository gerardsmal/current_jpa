package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.CertificatoDTO;
import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repositories.ISocioRepository;
import com.betacom.jpa.requests.SocioReq;
import com.betacom.jpa.services.interfaces.ISocioServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SocioImpl implements ISocioServices{
	
	private ISocioRepository socioR;

	public SocioImpl(ISocioRepository socioR) {
		this.socioR = socioR;
	}

	
	@Override
	public Integer insert(SocioReq req) throws AcademyException{
		log.debug("insert :" + req);
		Socio soc = new Socio();
		Optional<Socio> s = socioR.findByCodiceFiscale(req.getCodiceFiscale());
		
		if (s.isPresent())
			throw new AcademyException("Socio esistente in database");
		
		
		soc.setCodiceFiscale(req.getCodiceFiscale());
		soc.setCognome(req.getCognome());
		soc.setEmail(req.getEmail());
		soc.setNome(req.getNome());
		
		return socioR.save(soc).getId();	
		
	}


	@Override
	public List<SocioDTO> listAll() {
		List<Socio> lS = socioR.findAll();
		return lS.stream()
				.map(s -> new SocioDTO(s.getId(),
						s.getCognome(), 
						s.getNome(), 
						s.getCodiceFiscale(), 
						s.getEmail(),
						new CertificatoDTO(s.getCertificato().getId(), 
								s.getCertificato().getTipo(), 
								s.getCertificato().getDataCertificato())))
				.collect(Collectors.toList());
	}




}
