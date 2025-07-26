package com.betacom.jpa.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.betacom.jpa.dto.AbbonamentoDTO;
import com.betacom.jpa.dto.AttivitaDTO;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Attivita;

public class Utilities {
	public List<AbbonamentoDTO> buildAbbonamento(List<Abbonamento> ab){
		return ab.stream()
				.map(a -> AbbonamentoDTO.builder()
						.id(a.getId())
						.dataIscrizione(a.getDataIscrizione())
						.attivita(buildAttivita(a.getAttivita()))
						.build())
				.collect(Collectors.toList());
	}
	
	public List<AttivitaDTO> buildAttivita(List<Attivita> at){
		return at.stream()
				.map((a -> AttivitaDTO.builder()
						.id(a.getId())
						.descrizione(a.getDescrizione())
						.prezzo(a.getPrezzo())
						.build())
						)
				.collect(Collectors.toList());
				
		
	}
}
