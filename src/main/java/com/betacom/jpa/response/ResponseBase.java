package com.betacom.jpa.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBase {

	private Boolean rc;
	private String msg;
}
