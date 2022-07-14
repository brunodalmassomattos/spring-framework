package com.springbatch.processadorclassifier.reader;

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
public class ProcessadorClassifierReaderConfig {

	@Value("${arquivos.clientes}")
	private Resource resource;

	@Bean
	@StepScope
	public FlatFileItemReader processadorClassifierReader(LineMapper lineMapper) {
		return new FlatFileItemReaderBuilder()
				.name("processadorClassifierReader")
				.resource(resource)
				.lineMapper(lineMapper)
				.build();
	}

}
