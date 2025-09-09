package com.betacom.jpa.requests;

import lombok.Data;

@Data
public class PersoneReq {
	private Integer id;
	
	private String nome;
	private String cognome;
	private String email;
	private String colore;
}
