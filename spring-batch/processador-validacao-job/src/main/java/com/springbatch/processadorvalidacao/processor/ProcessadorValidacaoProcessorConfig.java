package com.springbatch.processadorvalidacao.processor;

import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.processadorvalidacao.dominio.Cliente;

@Configuration
public class ProcessadorValidacaoProcessorConfig {
	private Set<String> emails = new HashSet<String>();

	@Bean
	public ItemProcessor<Cliente, Cliente> procesadorValidacaoProcessor() {
//		BeanValidatingItemProcessor<Cliente> processor = new BeanValidatingItemProcessor<>();
//		processor.setFilter(Boolean.TRUE);

		ValidatingItemProcessor<Cliente> processor = new BeanValidatingItemProcessor<Cliente>();
		processor.setValidator(valitador());
		processor.setFilter(Boolean.TRUE);
		return processor;
	}

	
	private Validator<Cliente> valitador() {
		return new Validator<Cliente>() {
			@Override
			public void validate(Cliente cliente) throws ValidationException {
				if (emails.contains(cliente.getEmail())) {
					throw new ValidationException(String.format("O cliente %s ja foi processado!", cliente.getEmail()));
				}

				emails.add(cliente.getEmail());
			}
		};
	}
}
