package com.example.carros.dto;

import com.example.carros.modal.Carro;

public class CarroDTO {
	private Long id;
	private String nome;
	private String tipo;

	public CarroDTO() {
		super();
	}

	public CarroDTO(Long id, String nome, String tipo) {
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
	}

	public CarroDTO(Carro carro) {
		this.id = carro.getId();
		this.nome = carro.getNome();
		this.tipo = carro.getTipo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
