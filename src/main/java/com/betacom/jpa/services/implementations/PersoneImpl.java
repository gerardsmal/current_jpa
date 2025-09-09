package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.PersoneDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Persone;
import com.betacom.jpa.repositories.IPersoneRepository;
import com.betacom.jpa.requests.PersoneReq;
import com.betacom.jpa.services.interfaces.IPersoneServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersoneImpl implements IPersoneServices{
	private IPersoneRepository perR;
	
	public PersoneImpl(IPersoneRepository perR) {
		super();
		this.perR = perR;
	}
	
	@Override
	public void create(PersoneReq req) throws AcademyException {
		log.debug("Create :" + req);
		Persone p = new Persone();
		p.setCognome(req.getCognome());
		p.setNome(req.getNome());
		p.setColore(req.getColore());
		p.setEmail(req.getEmail());
		
		perR.save(p);
	}

	@Override
	public void update(PersoneReq req) throws AcademyException {
		log.debug("Create :" + req);
		Optional<Persone> per = perR.findById(req.getId());
		if (per.isEmpty())
			throw new AcademyException("Persona non trovato");
		Persone p = per.get();
	
		if (req.getCognome() != null)
			p.setCognome(req.getCognome());
		
		if (req.getNome() != null)
			p.setNome(req.getNome());
		
		if (req.getColore() != null)
			p.setColore(req.getColore());
		
		if (req.getEmail() != null)
			p.setEmail(req.getEmail());
		
		perR.save(p);
	
	}

	@Override
	public void remove(PersoneReq req) throws AcademyException {
		log.debug("Remove :" + req);
		Optional<Persone> per = perR.findById(req.getId());
		if (per.isEmpty())
			throw new AcademyException("Persona non trovato");
		
		perR.delete(per.get());
		
	}

	@Override
	public List<PersoneDTO> list() {
		log.debug("list ");
		List<Persone> lP = perR.findAll();
		
		return lP.stream()
				.map(p -> PersoneDTO.builder()
						.id(p.getId())
						.nome(p.getNome())
						.cognome(p.getCognome())
						.colore(p.getColore())
						.email(p.getEmail())
						.build())
				.toList();
	}

	@Override
	public PersoneDTO getById(Integer id) throws AcademyException {
		log.debug("getById :" + id);
		Optional<Persone> per = perR.findById(id);
		if (per.isEmpty())
			throw new AcademyException("Persona non trovato");
		Persone p = per.get();
		
		return PersoneDTO.builder()
				.id(p.getId())
				.nome(p.getNome())
				.cognome(p.getCognome())
				.colore(p.getColore())
				.email(p.getEmail())
				.build();
	}



}
