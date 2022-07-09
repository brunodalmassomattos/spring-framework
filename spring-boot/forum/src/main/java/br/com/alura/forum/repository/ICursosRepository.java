package br.com.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.model.Curso;

public interface ICursosRepository extends JpaRepository<Curso, Long> {

	Curso getByNome(String nomeCurso);

}