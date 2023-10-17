package br.com.api.g5.dto;

public class EnderecoDto {

	private String cep;
	private String complemento;
	private String numero;
		
	public EnderecoDto(String cep, String complemento, String numero) {
		super();
		this.cep = cep;
		this.complemento = complemento;
		this.numero = numero;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
}
