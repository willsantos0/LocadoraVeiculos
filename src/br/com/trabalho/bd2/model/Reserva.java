package br.com.trabalho.bd2.model;

import java.io.Serializable;
import java.util.Date;

public class Reserva implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer codR;
	private Date dataPrevistaRetirada;
	private String local;
	private Cliente cliente;
	private TipoVeiculo tipoVeiculo;
	public Integer getCodR() {
		return codR;
	}
	public void setCodR(Integer codR) {
		this.codR = codR;
	}
	public Date getDataPrevistaRetirada() {
		return dataPrevistaRetirada;
	}
	public void setDataPrevistaRetirada(Date dataPrevistaRetirada) {
		this.dataPrevistaRetirada = dataPrevistaRetirada;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public Cliente getCliente() {
		if(cliente == null)
			cliente = new Cliente();
			
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public TipoVeiculo getTipoVeiculo() {
		if(tipoVeiculo == null)
			tipoVeiculo = new TipoVeiculo();
		
		return tipoVeiculo;
	}
	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codR == null) ? 0 : codR.hashCode());
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
		Reserva other = (Reserva) obj;
		if (codR == null) {
			if (other.codR != null)
				return false;
		} else if (!codR.equals(other.codR))
			return false;
		return true;
	}
	
	

}
