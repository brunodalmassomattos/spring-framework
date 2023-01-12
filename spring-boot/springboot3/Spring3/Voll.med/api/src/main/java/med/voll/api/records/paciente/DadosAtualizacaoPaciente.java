package med.voll.api.records.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.records.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
		@NotNull Long id, 
		String nome, 
		String telefone, 
		DadosEndereco endereco) {
}
