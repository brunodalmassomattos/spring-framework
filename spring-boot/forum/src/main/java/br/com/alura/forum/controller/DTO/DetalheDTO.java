package br.com.alura.forum.controller.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.model.StatusTopicoEnum;
import br.com.alura.forum.model.Topico;

public class DetalheDTO {
	private Long id;
	private String titulo;
	private String mensagens;
	private LocalDateTime dataCriacao;
	private String nomeAutor;
	private StatusTopicoEnum statusTopicoEnum;
	private List<RespostaDTO> respostas;

	public DetalheDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagens = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.nomeAutor = topico.getAutor().getNome();
		this.statusTopicoEnum = topico.getStatus();
		this.respostas = new ArrayList<RespostaDTO>();
		this.respostas.addAll(topico.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagens() {
		return mensagens;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public StatusTopicoEnum getStatusTopicoEnum() {
		return statusTopicoEnum;
	}

	public List<RespostaDTO> getRespostas() {
		return respostas;
	}
}
