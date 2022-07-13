package com.springbatch.arquivomultiplosformatos.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Configuration
public class MultiplosArquivosClienteTransacaoReaderConfig {

	@Bean
	@StepScope
	public MultiResourceItemReader multiplosArquivosClienteTransacaoReader(
			@Value("#{jobParameters['arquivosClientes']}") Resource[] arquivos,
			FlatFileItemReader leituraArquivoMultiplosFormatosReader) {
		return new MultiResourceItemReaderBuilder<>()
				.name("multiplosArquivosClienteTransacaoReader")
				.resources(arquivos)
				.delegate(new ArquivoClienteTransacaoReader(leituraArquivoMultiplosFormatosReader))
				.build();
	}
}
