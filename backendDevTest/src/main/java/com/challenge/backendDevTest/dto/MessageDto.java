package com.challenge.backendDevTest.dto;

public class MessageDto {

	private String message;
	 
	public MessageDto() {
	}
	
	public MessageDto(String mensaje) {
		this.message = mensaje;
	}

	public String getMensaje() {
		return message;
	}

	public void setMensaje(String mensaje) {
		this.message = mensaje;
	}
}
