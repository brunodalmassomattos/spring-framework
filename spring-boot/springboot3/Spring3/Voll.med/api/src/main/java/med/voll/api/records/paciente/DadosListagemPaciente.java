package med.voll.api.records.paciente;

import med.voll.api.dao.paciente.Paciente;

public record DadosListagemPaciente(Long id, String nome, String email, String cpf) {

	public DadosListagemPaciente(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
	}

}
