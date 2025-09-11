package com.betacom.jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class SignInDTO {
	private Integer id;
	private Boolean logged;
	private String  role;
}
