package br.com.springbatch.migracaodados.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.springbatch.migracaodados.dominio.DadosBancarios;

@Configuration
@SuppressWarnings("serial")
public class DadosBancariosClassifierWriterConfig {

	@Bean
	public ClassifierCompositeItemWriter<DadosBancarios> dadosBancariosClassifierWriter(
			JdbcBatchItemWriter<DadosBancarios> bancoDadosBancariosWriter,
			FlatFileItemWriter<DadosBancarios> arquivoDadosBancariosInvalidasWrite) {
		return new ClassifierCompositeItemWriterBuilder<DadosBancarios>()
				.classifier(classifier(bancoDadosBancariosWriter, arquivoDadosBancariosInvalidasWrite))
				.build();
	}

	private Classifier<DadosBancarios, ItemWriter<? super DadosBancarios>> classifier(
			JdbcBatchItemWriter<DadosBancarios> bancoDadosBancariosWriter,
			FlatFileItemWriter<DadosBancarios> arquivoDadosBancariosInvalidasWrite) {
		
		return new Classifier<DadosBancarios, ItemWriter<? super DadosBancarios>>() {
			@Override
			public ItemWriter<? super DadosBancarios> classify(DadosBancarios dadosBancarios) {
				if (dadosBancarios.isValida()) {
					return bancoDadosBancariosWriter;
				} else {
					return arquivoDadosBancariosInvalidasWrite;
				}
			}
		};
	}
}
