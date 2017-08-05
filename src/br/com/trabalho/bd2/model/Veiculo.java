package br.com.trabalho.bd2.model;

public class Veiculo {
	
	private String placa;
	private String cor;
	private String nroChassi;
	private String nroMotor;
	private Double kmAtual;
	private TipoVeiculo tipo;
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getNroChassi() {
		return nroChassi;
	}
	public void setNroChassi(String nroChassi) {
		this.nroChassi = nroChassi;
	}
	public String getNroMotor() {
		return nroMotor;
	}
	public void setNroMotor(String nroMotor) {
		this.nroMotor = nroMotor;
	}
	public Double getKmAtual() {
		return kmAtual;
	}
	public void setKmAtual(Double kmAtual) {
		this.kmAtual = kmAtual;
	}
	public TipoVeiculo getTipo() {
		if(tipo == null)
			tipo = new TipoVeiculo();
		
		return tipo;
	}
	public void setTipo(TipoVeiculo tipo) {
		this.tipo = tipo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
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
		Veiculo other = (Veiculo) obj;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		return true;
	}
	
	
}
