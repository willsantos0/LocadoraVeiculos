package br.com.trabalho.bd2.model;

import java.io.Serializable;
import java.util.Date;

public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer codC;
	private String cpf;
	private String nome;
	private String endereco;
	private Date dataNasc;
	private char sexo;
	private String telefoneFixo;
	private String telefoneCelular;
	
	
	public Integer getCodC() {
		return codC;
	}
	public void setCodC(Integer codC) {
		this.codC = codC;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getTelefoneFixo() {
		return telefoneFixo;
	}
	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}
	public String getTelefoneCelular() {
		return telefoneCelular;
	}
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codC == null) ? 0 : codC.hashCode());
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
		Cliente other = (Cliente) obj;
		if (codC == null) {
			if (other.codC != null)
				return false;
		} else if (!codC.equals(other.codC))
			return false;
		return true;
	}
	
	
}
