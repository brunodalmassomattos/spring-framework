package br.com.arquivodelimitadojob.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import br.com.arquivodelimitadojob.model.Cliente;

@Configuration
public class LeituraArquivoDelimatadoReaderConfig {

	@Bean
	@StepScope
	public FlatFileItemReader<Cliente> leituraArquivoLarguraFixaReader(
			@Value("#{jobParameters['arquivoClientes']}") Resource arquivoClientes) {
		return new FlatFileItemReaderBuilder<Cliente>()
				.name("leituraArquivoLarguraFixaReader")
				.resource(arquivoClientes)
				.delimited()
				.names(new String[] { "nome", "sobrenome", "idade", "email" }).targetType(Cliente.class)
				.build();
	}
}
