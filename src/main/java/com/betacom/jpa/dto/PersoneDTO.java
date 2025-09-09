package com.betacom.jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Getter
@Setter
public class PersoneDTO {
	private Integer id;
	
	private String nome;
	private String cognome;
	private String email;
	private String colore;
}
