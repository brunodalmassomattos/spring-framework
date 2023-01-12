package med.voll.api.infra.exception;

import org.springframework.validation.FieldError;

public record DadosErrosValidacaoRecords(String campo, String mensagem) {

	public DadosErrosValidacaoRecords(FieldError fieldError) {
		this(fieldError.getField(), fieldError.getDefaultMessage());
	}
}
