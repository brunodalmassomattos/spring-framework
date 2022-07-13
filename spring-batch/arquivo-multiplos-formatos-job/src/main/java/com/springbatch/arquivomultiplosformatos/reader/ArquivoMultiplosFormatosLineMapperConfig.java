package com.springbatch.arquivomultiplosformatos.reader;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.arquivomultiplosformatos.dominio.Cliente;
import com.springbatch.arquivomultiplosformatos.dominio.Transacao;

@Configuration
public class ArquivoMultiplosFormatosLineMapperConfig {

	@SuppressWarnings("rawtypes")
	@Bean
	public PatternMatchingCompositeLineMapper lineMapper() {
		PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper<>();
		lineMapper.setTokenizers(tokenizers());
		lineMapper.setFieldSetMappers(fieldSetMappers());
		return lineMapper;
	}

	private Map<String, FieldSetMapper> fieldSetMappers() {
		Map<String, FieldSetMapper> mapFieldSetMappers = new HashMap<>();
		mapFieldSetMappers.put("0*", fieldSetMappers(Cliente.class));
		mapFieldSetMappers.put("1*", fieldSetMappers(Transacao.class));
		return mapFieldSetMappers;
	}

	private FieldSetMapper fieldSetMappers(Class classe) {
		BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(classe);
		return fieldSetMapper;
	}

	private Map<String, LineTokenizer> tokenizers() {
		Map<String, LineTokenizer> mapTokenizers = new HashMap<>();
		mapTokenizers.put("0*", clienteLineTokenizer());
		mapTokenizers.put("1*", transacaoLineTokenizer());
		return mapTokenizers;
	}

	private LineTokenizer clienteLineTokenizer() {
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames("nome", "sobrenome", "idade", "email");
		lineTokenizer.setIncludedFields(1, 2, 3, 4);
		return lineTokenizer;
	}

	private LineTokenizer transacaoLineTokenizer() {
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames("id", "descricao", "valor ");
		lineTokenizer.setIncludedFields(1, 2, 3);
		return lineTokenizer;
	}

}
