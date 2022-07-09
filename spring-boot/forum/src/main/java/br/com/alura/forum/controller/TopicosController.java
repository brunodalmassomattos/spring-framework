package br.com.alura.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.DTO.DetalheDTO;
import br.com.alura.forum.controller.DTO.TopicoDTO;
import br.com.alura.forum.controller.form.AtualizaTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.ICursosRepository;
import br.com.alura.forum.repository.ITopicosRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private ITopicosRepository topicosRepository;

	@Autowired
	private ICursosRepository cursosRepository;

	@GetMapping
	@Cacheable(value = "listarTopicos")
	public Page<TopicoDTO> listar(@RequestParam(required = false) String nomeCurso,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {

		if (nomeCurso == null) {
			Page<Topico> topicos = this.topicosRepository.findAll(paginacao);

			return TopicoDTO.converter(topicos);
		} else {
			Page<Topico> topicos = this.topicosRepository.findByCurso_Nome(nomeCurso, paginacao);
			return TopicoDTO.converter(topicos);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalheDTO> detalher(@PathVariable Long id) {
		Optional<Topico> optionalTopico = null;

		try {
			optionalTopico = verificarRegistro(id);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(new DetalheDTO(optionalTopico.get()));
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listarTopicos", allEntries = true)
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form,
			UriComponentsBuilder uriComponentsBuilder) {
		Topico topico = form.converter(cursosRepository);
		this.topicosRepository.save(topico);

		URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listarTopicos", allEntries = true)
	public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaTopicoForm form) {

		Topico topico;

		try {
			verificarRegistro(id);
			topico = form.atualizar(id, this.topicosRepository);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(new TopicoDTO(topico));
	}

	@DeleteMapping("{id}")
	@Transactional
	@CacheEvict(value = "listarTopicos", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		try {
			verificarRegistro(id);
			this.topicosRepository.deleteById(id);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().build();

	}

	private Optional<Topico> verificarRegistro(Long id) throws Exception {
		Optional<Topico> optional = topicosRepository.findById(id);
		if (optional.isPresent()) {
			return optional;
		}

		throw new Exception();
	}
}
