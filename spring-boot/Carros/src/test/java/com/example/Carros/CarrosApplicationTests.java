package com.example.Carros;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.carros.dto.CarroDTO;
import com.example.carros.modal.Carro;
import com.example.carros.service.CarrosService;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;;

@SpringBootTest
class CarrosApplicationTests {

	@Autowired
	private CarrosService carrosService; 
	
	@Test
	void testeInserindo() {
		Carro carro = new Carro();
		carro.setNome("Bugatti");
		carro.setTipo("Super carro");
		
		CarroDTO saveCarros = this.carrosService.saveCarros(carro);
		
		Assertions.assertNotNull(saveCarros);
		Assertions.assertNotNull(saveCarros.getId());
		
		Optional<CarroDTO> carroById = this.carrosService.getCarroById(saveCarros.getId());
		
		Assertions.assertTrue(carroById.isPresent());
		Assertions.assertEquals("Bugatti", carroById.get().getNome());
		Assertions.assertEquals("Super carro", carroById.get().getTipo());
		
		this.carrosService.delete(saveCarros.getId());
		Assertions.assertNull(this.carrosService.getCarroById(saveCarros.getId()).isPresent());
	}

}
