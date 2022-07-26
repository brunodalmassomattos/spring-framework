package br.com.springbatch.migracaodados.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.springbatch.migracaodados.dominio.DadosBancarios;

@Configuration
public class MigrarDadosBancariosStepConfig {
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step migrarDadosBancariosStep(
			ItemReader<DadosBancarios> arquivoDadosBancariosReader, 
			ClassifierCompositeItemWriter<DadosBancarios> dadosBancariosClassifierWriter,
			FlatFileItemWriter<DadosBancarios> arquivoDadosBancariosInvalidasWrite) {
		return stepBuilderFactory
				.get("migrarDadosBancariosStep")
				.<DadosBancarios, DadosBancarios> chunk(10000)
				.reader(arquivoDadosBancariosReader)
				.writer(dadosBancariosClassifierWriter)
				.stream(arquivoDadosBancariosInvalidasWrite)
				.build();
	}
}
