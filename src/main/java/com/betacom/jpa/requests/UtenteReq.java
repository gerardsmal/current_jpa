package com.betacom.jpa.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class UtenteReq {
	private Integer id;
	
	private String userName;
	private String pwd;
	private String email;
	private String role;

}
