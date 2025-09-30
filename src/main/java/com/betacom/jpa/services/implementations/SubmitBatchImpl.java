package com.betacom.jpa.services.implementations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.services.interfaces.ISubmitServices;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class SubmitBatchImpl implements ISubmitServices{

	@Value ("${batch}")
	private String script;
	
	@Override
	public void run() throws AcademyException {
		log.debug("Begin run :" + script);
		
		try {
			ProcessBuilder pb = new ProcessBuilder(script);
			log.debug("After ProcessBuilder");
			
			Process p = pb.start();
			log.debug("process is started");
			
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
		
		
	}

}
