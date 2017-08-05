package br.com.trabalho.bd2.model;

import java.io.Serializable;
import java.util.Date;

public class Aluguel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Veiculo veiculo;
	private Cliente cliente;
	private Date dataRetirada;
	private Date dataDevolucao;
	private String tipoFranquia;
	private String nroCnh;
	private Date dataVencimentoCnf;
	
	
	public Veiculo getVeiculo() {
		if(veiculo == null)
			veiculo = new Veiculo();
		
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public Cliente getCliente() {
		if(cliente == null)
			cliente = new Cliente();
		
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public Date getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public String getTipoFranquia() {
		return tipoFranquia;
	}
	public void setTipoFranquia(String tipoFranquia) {
		this.tipoFranquia = tipoFranquia;
	}
	public String getNroCnh() {
		return nroCnh;
	}
	public void setNroCnh(String nroCnh) {
		this.nroCnh = nroCnh;
	}
	public Date getDataVencimentoCnf() {
		return dataVencimentoCnf;
	}
	public void setDataVencimentoCnf(Date dataVencimentoCnf) {
		this.dataVencimentoCnf = dataVencimentoCnf;
	}
	
	
}
