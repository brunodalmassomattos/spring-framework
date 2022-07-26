package br.com.springbatch.migracaodados.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import br.com.springbatch.migracaodados.dominio.Pessoa;

@Configuration
public class ArquivoPessoasInvalidasWriterConfig {
	
	@Value("${arquivos.pessoas_invalidas}")
	private Resource arquivoSaida;

	@Bean
	public FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter() {
		return new FlatFileItemWriterBuilder<Pessoa>()
				.name("arquivoPessoasInvalidasWriter")
				.resource(arquivoSaida)
				.delimited()
				.names("id")
				.build();
	}

}
