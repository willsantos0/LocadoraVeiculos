package br.com.trabalho.bd2.model;

import java.io.Serializable;

public class TipoVeiculo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer codTv;
	private String tamanho;
	private Integer nroPassageiros;
	private Integer nroPortas;
	private double valorDiarioLocacao;
	private double valorKmRodado;
	private double valorFranquiaNormal;
	private double valorFranquiaReduzida;
	private String arCondicionado;
	
	public Integer getCodTv() {
		return codTv;
	}
	public void setCodTv(Integer codTv) {
		this.codTv = codTv;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public Integer getNroPassageiros() {
		return nroPassageiros;
	}
	public void setNroPassageiros(Integer nroPassageiros) {
		this.nroPassageiros = nroPassageiros;
	}
	public Integer getNroPortas() {
		return nroPortas;
	}
	public void setNroPortas(Integer nroPortas) {
		this.nroPortas = nroPortas;
	}
	public double getValorDiarioLocacao() {
		return valorDiarioLocacao;
	}
	public void setValorDiarioLocacao(double valorDiarioLocacao) {
		this.valorDiarioLocacao = valorDiarioLocacao;
	}
	public double getValorKmRodado() {
		return valorKmRodado;
	}
	public void setValorKmRodado(double valorKmRodado) {
		this.valorKmRodado = valorKmRodado;
	}
	public double getValorFranquiaNormal() {
		return valorFranquiaNormal;
	}
	public void setValorFranquiaNormal(double valorFranquiaNormal) {
		this.valorFranquiaNormal = valorFranquiaNormal;
	}
	public double getValorFranquiaReduzida() {
		return valorFranquiaReduzida;
	}
	public void setValorFranquiaReduzida(double valorFranquiaReduzida) {
		this.valorFranquiaReduzida = valorFranquiaReduzida;
	}
	public String getArCondicionado() {
		return arCondicionado;
	}
	public void setArCondicionado(String arCondicionado) {
		this.arCondicionado = arCondicionado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codTv == null) ? 0 : codTv.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoVeiculo other = (TipoVeiculo) obj;
		if (codTv == null) {
			if (other.codTv != null)
				return false;
		} else if (!codTv.equals(other.codTv))
			return false;
		return true;
	}
	
	
	
}
