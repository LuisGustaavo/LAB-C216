package br.inatel.labs.labjpa.dto;

import java.math.BigDecimal;

public class TotalCompradoPeloFornecedorDTO {

	
	private String fornecedorRazaoSocial;
	
	private BigDecimal totalComprado;

	public TotalCompradoPeloFornecedorDTO(String fornecedorRazaoSocial, BigDecimal totalComprado) {
		super();
		this.fornecedorRazaoSocial = fornecedorRazaoSocial;
		this.totalComprado = totalComprado;
	}

	public String getFornecedorRazaoSocial() {
		return fornecedorRazaoSocial;
	}

	public BigDecimal getTotalComprado() {
		return totalComprado;
	}

	@Override
	public String toString() {
		return "TotalCompradoPeloFornecedorDTO [fornecedorRazaoSocial=" + fornecedorRazaoSocial + ", totalComprado="
				+ totalComprado + "]";
	}
	
	
	
}
