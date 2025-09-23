package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.SignInDTO;
import com.betacom.jpa.dto.UtenteDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Utente;
import com.betacom.jpa.repositories.IUtenteRepository;
import com.betacom.jpa.requests.SignInReq;
import com.betacom.jpa.requests.UtenteReq;
import com.betacom.jpa.requests.UtentePwdReq;
import com.betacom.jpa.services.interfaces.IUtenteServices;
import com.betacom.jpa.utils.Roles;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtenteImpl implements IUtenteServices{
	private IUtenteRepository utenR;

	public UtenteImpl(IUtenteRepository utenR) {
		this.utenR = utenR;
	}

	
	@Override
	public void create(UtenteReq req) throws AcademyException {
		log.debug("Create:" + req);
		Optional<Utente> u = utenR.findByUserName(req.getUserName().trim());
		if (u.isPresent())
			throw new AcademyException("Username esistante");
		Utente ut = new Utente();
		ut.setUserName(req.getUserName());
		ut.setPwd(req.getPwd());
		ut.setEmail(req.getEmail());
		ut.setRole(Roles.valueOf(req.getRole()));
		
		utenR.save(ut);
		
	}

	@Override
	public void update(UtenteReq req) throws AcademyException {
		log.debug("update :" + req);
		Optional<Utente> u = utenR.findById(req.getId());
		if (u.isEmpty())
			throw new AcademyException("Username inesistente");
		if (req.getPwd() != null)
			u.get().setPwd(req.getPwd());
		if (req.getRole() != null)
			u.get().setRole(Roles.valueOf(req.getRole()));
		if (req.getEmail() != null)
			u.get().setEmail(req.getEmail());

		utenR.save(u.get());
	}

	@Override
	public void updatePwd(UtentePwdReq req) throws AcademyException {
		log.debug("updatePwd :" + req);
		Optional<Utente> u = utenR.findById(req.getId());
		if (u.isEmpty())
			throw new AcademyException("Username inesistente");
	
		if (req.getPwdNew() == null)
			throw new AcademyException("Nuovo pwd non iserito");
		
		if (!u.get().getPwd().equals(req.getPwd()))
			throw new AcademyException("Password inserito non valido");
			
		u.get().setPwd(req.getPwdNew());

		utenR.save(u.get());
	}
	@Override
	public UtenteDTO remove(UtenteReq req) throws AcademyException {
		log.debug("remove:" + req);
		Optional<Utente> u = utenR.findById(req.getId());
		if (u.isEmpty())
			throw new AcademyException("Username inesistente");

		utenR.delete(u.get());
		return UtenteDTO.builder()
				.id(u.get().getId())
				.userName(u.get().getUserName())
				.pwd(u.get().getPwd())
				.email(u.get().getEmail())
				.role(u.get().getRole().toString())
				.build();
	}

	@Override
	public List<UtenteDTO> listAll() {
		log.debug("listAll");
		List<Utente> lU = utenR.findAll();
		return lU.stream()
				.map(u -> UtenteDTO.builder()
						.id(u.getId())
						.userName(u.getUserName())
						.pwd(u.getPwd())
						.email(u.getEmail())
						.role(u.getRole().toString())
						.build())
				.collect(Collectors.toList());
						
	}

	@Override
	public UtenteDTO findById(Integer id) throws AcademyException {
		log.debug("findById:" + id);
		Optional<Utente> u = utenR.findById(id);
		if (u.isEmpty())
			throw new AcademyException("Username inesistente");
		
		return UtenteDTO.builder()
				.id(u.get().getId())
				.userName(u.get().getUserName())
				.pwd(u.get().getPwd())
				.email(u.get().getEmail())
				.role(u.get().getRole().toString())
				.build();
	}


	@Override
	public SignInDTO signIn(SignInReq req) {
		log.debug("signIn:" + req);
		SignInDTO r = new SignInDTO();
		Optional<Utente> u = utenR.findByUserNameAndPwd(req.getUser(), req.getPwd());
		if (u.isEmpty()) {
			r.setLogged(false);
		} else {
			r.setId(u.get().getId());
			r.setLogged(true);
			r.setRole(u.get().getRole().toString());
		}
		
		return r;
	}


}
