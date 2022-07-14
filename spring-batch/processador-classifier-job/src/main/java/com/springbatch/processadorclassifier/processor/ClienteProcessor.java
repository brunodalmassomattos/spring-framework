package com.springbatch.processadorclassifier.processor;

import org.springframework.batch.item.ItemProcessor;

import com.springbatch.processadorclassifier.dominio.Cliente;

public class ClienteProcessor implements ItemProcessor<Cliente, Cliente> {

	@Override
	public Cliente process(Cliente cliente) throws Exception {
		System.out.println(String.format("Aplicando regras no cliente %s", cliente.getEmail()));
 		return null;
	}

}
