package com.example.carros.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.carros.dto.CarroDTO;
import com.example.carros.modal.Carro;
import com.example.carros.service.CarrosService;

@RestController
@RequestMapping("/v1/carros")
public class CarrosController {

	@Autowired
	private CarrosService carrosService;

	@GetMapping
	public ResponseEntity<?> getCarros() {
		// return this.carrosService.getCarros();
		// return new ResponseEntity<>(this.carrosService.getCarros(), HttpStatus.OK);
		return ResponseEntity.ok(this.carrosService.getCarros());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCarroID(@PathVariable("id") Long id) {
		Optional<CarroDTO> carro = this.carrosService.getCarroById(id);

		// return carro;
		// return carro.map(c ->
		// ResponseEntity.ok(c)).orElse(ResponseEntity.notFound().build());
		return carro.isPresent() ? ResponseEntity.ok(carro) : ResponseEntity.notFound().build();
	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<?> getCarroID(@PathVariable("tipo") String tipo) {

		List<CarroDTO> carros = this.carrosService.getCarroByTipo(tipo);

		if (carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(carros);
		}
	}

	@PostMapping
	public ResponseEntity saveCarros(@RequestBody Carro carro) {
		try {
			CarroDTO carroSalvo = this.carrosService.saveCarros(carro);
			URI location = getUri(carroSalvo.getId());
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@PutMapping("/{id}")
	public ResponseEntity atualizarCarros(@PathVariable("id") Long id, @RequestBody Carro carroFiltro) throws Exception {
		CarroDTO carro = this.carrosService.updateCarros(id, carroFiltro);
		return carro != null ? ResponseEntity.ok(carro) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteCarroID(@PathVariable("id") Long id) {
		Boolean exlcuido = this.carrosService.delete(id);
		return exlcuido ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

}
