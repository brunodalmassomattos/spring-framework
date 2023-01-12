package med.voll.api.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class TratadorDeErros {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErro400(MethodArgumentNotValidException exception) {
		var errors = exception.getFieldErrors();

		return ResponseEntity.badRequest().body(errors.stream().map(DadosErrosValidacaoRecords::new).toList());
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarErro404() {
		return ResponseEntity.notFound().build();
	}

}
