package br.com.springbatch.migracaodados.reader;


import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import br.com.springbatch.migracaodados.dominio.DadosBancarios;

@Configuration
public class ArquivoDadosBancariosReaderConfig {

	@Value("${arquivos.dados_bancarios}")
	private Resource arquivo;
	
	@Bean
	public FlatFileItemReader<DadosBancarios> arquivoDadosBancariosReader() {
		return new FlatFileItemReaderBuilder<DadosBancarios>()
				.name("arquivoDadosBancariosReader")
				.resource(arquivo)
				.delimited()
				.names("pessoaId", "agencia", "conta", "banco", "id")
				.addComment("--")
				.targetType(DadosBancarios.class)
				.build();
	}
	
}
