package com.springbatch.arquivomultiplosformatos.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ArquivoMultiplosFormatosReaderConfig {

	@Value("${file.input}")
	private Resource resource;
	
	@Bean
	@StepScope
	public FlatFileItemReader arquivoMultiplosFormatosItemReader(LineMapper lineMapper) {
		return new FlatFileItemReaderBuilder()
				.name("arquivoMultiplosFormatosItemReader")
				.resource(resource)
				.lineMapper(lineMapper)
				.build();
	}

}
