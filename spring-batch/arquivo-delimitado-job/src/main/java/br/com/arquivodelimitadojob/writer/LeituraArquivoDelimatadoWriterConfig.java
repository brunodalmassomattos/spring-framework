package br.com.arquivodelimitadojob.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.arquivodelimitadojob.model.Cliente;

@Configuration
public class LeituraArquivoDelimatadoWriterConfig {
	@Bean
	public ItemWriter<Cliente> leituraArquivoDelimatadoWriter() {
		return items -> items.forEach(System.out::println);
	}
}
