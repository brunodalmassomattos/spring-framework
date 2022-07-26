package br.com.springbatch.migracaodados.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import br.com.springbatch.migracaodados.dominio.DadosBancarios;

@Configuration
public class ArquivoDadosBancariosInvalidasWriterConfig {
	
	@Value("${arquivos.dados_bancarios_invalidos}")
	private Resource arquivoSaida;

	@Bean
	public FlatFileItemWriter<DadosBancarios> arquivoDadosBancariosInvalidasWrite() {
		return new FlatFileItemWriterBuilder<DadosBancarios>()
				.name("arquivoDadosBancariosInvalidasWrite")
				.resource(arquivoSaida)
				.delimited()
				.names("id")
				.build();
	}

}
