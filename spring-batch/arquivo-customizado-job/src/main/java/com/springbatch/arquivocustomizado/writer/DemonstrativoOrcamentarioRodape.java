package com.springbatch.arquivocustomizado.writer;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.context.annotation.Configuration;

import com.springbatch.arquivocustomizado.dominio.GrupoLancamento;

@Configuration
public class DemonstrativoOrcamentarioRodape implements FlatFileFooterCallback {

	private BigDecimal totalGeral = BigDecimal.ZERO;

	@Override
	public void writeFooter(Writer writer) throws IOException {
		writer.append(String.format("\t\t\t\t\t\t\t  Total: %s\n", NumberFormat.getCurrencyInstance().format(totalGeral)));
		writer.append(String.format("\t\t\t\t\t\t\t  Código de Autenticação: %s", "fkyew6868fewjfhjjewf"));
	}
	
	@BeforeWrite
	public void beforeWrite(List<GrupoLancamento> listaGrupoLancamento) {
		for (GrupoLancamento grupoLancamento : listaGrupoLancamento) {
			totalGeral = totalGeral.add(new BigDecimal(grupoLancamento.getTotal()));
		}
	}
	
	@AfterChunk
	public void afterChunk(ChunkContext context) {
		this.totalGeral = BigDecimal.ZERO;
	}

}
