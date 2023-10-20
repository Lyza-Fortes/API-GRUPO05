package br.com.api.g5.enums;

public enum TipoRoleEnum {

	ROLE_CLIENTE("CLIENTE"),
	ROLE_FUNCIONARIO("FUNCIONARIO");

	private String tipo;

	TipoRoleEnum(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
}