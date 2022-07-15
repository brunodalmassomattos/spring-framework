package com.springbatch.arquivocustomizado.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.arquivocustomizado.dominio.GrupoLancamento;
import com.springbatch.arquivocustomizado.reader.GrupoLancamentoReader;
import com.springbatch.arquivocustomizado.writer.DemonstrativoOrcamentarioRodape;

@Configuration
public class DemonstrativoOrcamentarioStepConfig {
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step demonstrativoOrcamentarioStep(
			// Esse aqui lê dos arquivos
			// MultiResourceItemReader<GrupoLancamento> demonstrativoOrcamentarioReader,
			
			// Esse aqui lê do banco de dados
			GrupoLancamentoReader demonstrativoOrcamentarioReader,
			
			// Para gerar um arquivo
			// ItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter,
			
			// Para gerar varios arquivos
			MultiResourceItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter,
			
			DemonstrativoOrcamentarioRodape demonstrativoOrcamentarioRodape) {
		return stepBuilderFactory
				.get("demonstrativoOrcamentarioStep")
				.<GrupoLancamento,GrupoLancamento>chunk(1)
				.reader(demonstrativoOrcamentarioReader)
				.writer(demonstrativoOrcamentarioWriter)
				.listener(demonstrativoOrcamentarioRodape)
				.build();
	}
}
