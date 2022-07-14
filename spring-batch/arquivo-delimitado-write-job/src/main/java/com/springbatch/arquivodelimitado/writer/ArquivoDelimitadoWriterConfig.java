package com.springbatch.arquivodelimitado.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.springbatch.arquivodelimitado.dominio.Cliente;

@Configuration
public class ArquivoDelimitadoWriterConfig {
	
	@Value("${arquivos.clientes.saida}")
	private Resource arquivoClientesSaida;
	
	@Bean
	public FlatFileItemWriter<Cliente> escritaArquivoLarguraFixaWriter() {
		return new FlatFileItemWriterBuilder<Cliente>()
				.name("escritaArquivoLarguraFixaWriter")
				.resource(arquivoClientesSaida)
				.delimited()
//				.delimiter(";") /** PARA DETERMINAR UM LIMITADOS **/
				.names("nome", "sobrenome", "idade", "email")
				.build();
	}
}
