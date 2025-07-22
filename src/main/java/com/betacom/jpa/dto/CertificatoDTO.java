package com.betacom.jpa.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CertificatoDTO {

	private Integer id;
	private Boolean tipo;  //false normale true agonistico
	private LocalDate dataCertificato;

}
