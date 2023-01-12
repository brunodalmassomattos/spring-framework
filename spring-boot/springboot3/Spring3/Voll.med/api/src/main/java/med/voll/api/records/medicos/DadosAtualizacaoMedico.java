package med.voll.api.records.medicos;

import jakarta.validation.constraints.NotNull;
import med.voll.api.records.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
		@NotNull Long id, 
		String nome, 
		String telefone, 
		DadosEndereco dadosEndereco) {

}
