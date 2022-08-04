package com.example.carros.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.carros.dto.CarroDTO;
import com.example.carros.modal.Carro;
import com.example.carros.repository.CarrosRepository;

@Service
public class CarrosService {

	@Autowired
	private CarrosRepository carrosRepository;

	public List<CarroDTO> getCarros() {
		List<CarroDTO> retorno = new ArrayList<CarroDTO>();

		List<Carro> carros = this.carrosRepository.findAll();
		for (Carro carro : carros) {
			retorno.add(new CarroDTO(carro));
		}

		return retorno;
	}

	public Optional<CarroDTO> getCarroById(Long id) {
		// return this.carrosRepository.findById(id);
		Optional<Carro> carro = this.carrosRepository.findById(id);
		return carro.isPresent() ? Optional.of(new CarroDTO(carro.get())) : null;
	}

	public List<CarroDTO> getCarroByTipo(String tipo) {
		return this.carrosRepository.findByTipo(tipo).stream().map(carro -> new CarroDTO(carro))
				.collect(Collectors.toList());
	}

	public CarroDTO saveCarros(Carro carro) {
		Assert.isNull(carro.getId(), "Não é possivel inserir o registro!");

		Carro carroInserido = this.carrosRepository.save(carro);
		return new CarroDTO(carroInserido);
	}

	public CarroDTO updateCarros(Long id, Carro carroFiltro) throws Exception {
		Assert.notNull(id, "Não foi possivel atualizaro registro!");

		Optional<Carro> carro = this.carrosRepository.findById(id);
		if (carro.isPresent()) {
			Carro carroAtulizar = carro.get();
			carroAtulizar.setNome(carroFiltro.getNome());
			carroAtulizar.setTipo(carroFiltro.getTipo());

			return new CarroDTO(this.carrosRepository.save(carroAtulizar));
		}

		throw new Exception("Não foi possivel atualizaro registro!");
	}

	public Boolean delete(Long id) {
		if (this.getCarroById(id).isPresent()) {
			this.carrosRepository.deleteById(id);

			return Boolean.TRUE;
		}
		return Boolean.FALSE;

	}

}
