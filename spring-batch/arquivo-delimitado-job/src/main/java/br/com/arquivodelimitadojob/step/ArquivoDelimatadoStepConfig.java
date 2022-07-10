package br.com.arquivodelimitadojob.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.arquivodelimitadojob.model.Cliente;

@Configuration
public class ArquivoDelimatadoStepConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step leituraArquivoDelimatadoStep(ItemReader<Cliente> leituraArquivoDelimatadoReader,
			ItemWriter<Cliente> leituraArquivoDelimatadoWriter) {
		return this.stepBuilderFactory
				.get("leituraArquivoLarguraFixaStep")
				.<Cliente, Cliente>chunk(1)
				.reader(leituraArquivoDelimatadoReader)
				.writer(leituraArquivoDelimatadoWriter)
				.build();
	}
}
