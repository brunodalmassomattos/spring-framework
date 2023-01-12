package med.voll.api.records.medicos;

import med.voll.api.dao.Medico;

public record DadosListarMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

	public DadosListarMedico(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}
}
