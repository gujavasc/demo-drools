package br.com.ricardolonga.drools.domain;

public class Resultado {
	
	public enum Status {
		SUCESSO, ERRO
	}
	
	private Status status;
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Status getStatus() {
		return status;
	}

}
