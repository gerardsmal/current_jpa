package com.betacom.jpa.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class UtentePwdReq {
	private Integer id;
	private String pwd;
	private String pwdNew;

}
