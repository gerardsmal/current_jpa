package com.betacom.jpa.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AbbonamentoDTO {

	private Integer id;
	private LocalDate dataIscrizione;

}
