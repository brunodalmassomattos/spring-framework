package br.com.alura.forum.controller.DTO;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.alura.forum.model.Topico;

public class TopicoDTO {

	private Long id;
	private String titulo;
	private String mensagens;
	private LocalDateTime dataCriacao;

	public TopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagens = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
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

	public static Page<TopicoDTO> converter(Page<Topico> topicos) {
		return topicos.map(TopicoDTO::new);
	}

}
