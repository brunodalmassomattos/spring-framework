package br.com.arquivodelimitadojob.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ArquivoDelimatadoJobConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Bean
	public Job arquivoLarguraDelimitado(Step leituraArquivoDelimatadoStep) {
		return this.jobBuilderFactory
				.get("arquivoLarguraFixa")
				.start(leituraArquivoDelimatadoStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}

}
