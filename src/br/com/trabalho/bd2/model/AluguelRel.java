package br.com.trabalho.bd2.model;

import java.io.Serializable;

public class AluguelRel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String mes;
	private int ano;
	private String veiculo;
	private int qtdMes;
	private int qtdMesVeiculo;
	private int qtdAno;
	
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}
	public int getQtdMes() {
		return qtdMes;
	}
	public void setQtdMes(int qtdMes) {
		this.qtdMes = qtdMes;
	}
	public int getQtdMesVeiculo() {
		return qtdMesVeiculo;
	}
	public void setQtdMesVeiculo(int qtdMesVeiculo) {
		this.qtdMesVeiculo = qtdMesVeiculo;
	}
	public int getQtdAno() {
		return qtdAno;
	}
	public void setQtdAno(int qtdAno) {
		this.qtdAno = qtdAno;
	}
	
	
	
	
	
}
