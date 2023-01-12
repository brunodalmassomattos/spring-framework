package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dao.medico.Medico;
import med.voll.api.records.medicos.DadosAtualizacaoMedico;
import med.voll.api.records.medicos.DadosCadastroMedico;
import med.voll.api.records.medicos.DadosDetalhamentoMedico;
import med.voll.api.records.medicos.DadosListarMedico;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
@SuppressWarnings("rawtypes")
public class MedicoController {

	@Autowired
	MedicoRepository medicoRepository;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico request, UriComponentsBuilder uriBuilder) {
		var medico = new Medico(request);
		medicoRepository.save(medico);

		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}

	@GetMapping
	public ResponseEntity<Page<DadosListarMedico>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = this.medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListarMedico::new);
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		var medico = this.medicoRepository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico request) {
		var medico = this.medicoRepository.getReferenceById(request.id());
		medico.atualizarInformacoes(request);

		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity excluir(@PathVariable Long id) {
		var medico = this.medicoRepository.getReferenceById(id);
		medico.excluir();

		return ResponseEntity.noContent().build();
	}

}
