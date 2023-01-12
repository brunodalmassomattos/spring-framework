package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dao.Medico;
import med.voll.api.records.medicos.DadosCadastroMedico;
import med.voll.api.records.medicos.DadosListarMedico;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	MedicoRepository medicoRepository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroMedico request) {
		this.medicoRepository.save(new Medico(request));
	}

	@GetMapping
	public Page<DadosListarMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		return this.medicoRepository.findAll(paginacao).map(DadosListarMedico::new);
	}

}
