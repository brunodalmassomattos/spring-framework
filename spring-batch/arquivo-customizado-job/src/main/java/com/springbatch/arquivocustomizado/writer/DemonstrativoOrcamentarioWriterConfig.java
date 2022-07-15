package com.springbatch.arquivocustomizado.writer;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.ResourceSuffixCreator;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.springbatch.arquivocustomizado.dominio.GrupoLancamento;
import com.springbatch.arquivocustomizado.dominio.Lancamento;

@Configuration
public class DemonstrativoOrcamentarioWriterConfig {
	
	@Value("${arquivo.saida}")
	private Resource demonstrativoOrcamentario;

	@Value("${arquivos.saida}")
	private Resource demonstrativosOrcamentarios;
	
	@Bean
	public MultiResourceItemWriter<GrupoLancamento> multiDemonstrativo(
			FlatFileItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter) {
		return new MultiResourceItemWriterBuilder<GrupoLancamento>()
				.name("multiDemonstrativo")
				.resource(demonstrativosOrcamentarios)
				.delegate(demonstrativoOrcamentarioWriter)
				.resourceSuffixCreator(suffixCreator())
				.itemCountLimitPerResource(1)
				.build();
	}
	
	private ResourceSuffixCreator suffixCreator() {
		return new ResourceSuffixCreator() {
			
			@Override
			public String getSuffix(int index) {
				return index + ".txt";
			}
		};
	}

	@Bean
	public FlatFileItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter(
			DemonstrativoOrcamentarioRodape demonstrativoOrcamentarioRodape) {
		return new FlatFileItemWriterBuilder<GrupoLancamento>()
				.name("demonstrativoOrcamentario")
				.resource(demonstrativoOrcamentario)
				.lineAggregator(lineAggregator())
				.headerCallback(headerCallback())
				.footerCallback(demonstrativoOrcamentarioRodape)
				.build();
	}

	private FlatFileHeaderCallback headerCallback() {
		return new FlatFileHeaderCallback() {
			
			@Override
			public void writeHeader(Writer writer) throws IOException {
				writer.append(String.format("SISTEMA INTEGRADO: XPTO \t\t\t\t DATA: %s\n", new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
				writer.append(String.format("MÓDULO: ORÇAMENTO \t\t\t\t\t\t HORA: %s\n", new SimpleDateFormat("HH:MM").format(new Date())));
				writer.append(String.format("\t\t\tDEMONSTRATIVO ORCAMENTARIO\n"));
				writer.append(String.format("----------------------------------------------------------------------------\n"));
				writer.append(String.format("CODIGO NOME VALOR\n"));
				writer.append(String.format("\t Data Descricao Valor\n"));
				writer.append(String.format("----------------------------------------------------------------------------\n"));
			}
		};
	}

	private LineAggregator<GrupoLancamento> lineAggregator() {
		return new LineAggregator<GrupoLancamento>() {

			@Override
			public String aggregate(GrupoLancamento grupo) {
				String formatoGrupoLancamento = String.format("[%d] %s - %s\n", grupo.getCodigoNaturezaDespesa(),
						grupo.getDescricaoNaturezaDespesa(),
						NumberFormat.getCurrencyInstance().format(grupo.getTotal()));

				StringBuilder stringBuilder = new StringBuilder();
				for (Lancamento lancamento : grupo.getLancamentos()) {
					stringBuilder.append(String.format("\t [%s] %s - %s\n",
							new SimpleDateFormat("dd/MM/yyyy").format(lancamento.getData()), lancamento.getDescricao(),
							NumberFormat.getCurrencyInstance().format(lancamento.getValor())));
				}

				return formatoGrupoLancamento + stringBuilder.toString();
			}
		};
	}
	
}
