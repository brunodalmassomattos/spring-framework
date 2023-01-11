package med.voll.api.records.medicos;

import med.voll.api.records.endereco.DadosEndereco;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade,
		DadosEndereco dadosEndereco) {

}
