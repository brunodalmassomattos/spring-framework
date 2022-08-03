package br.com.devdojo.springboot2.domian;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Anime {
	private Long id;
	private String name;
}
