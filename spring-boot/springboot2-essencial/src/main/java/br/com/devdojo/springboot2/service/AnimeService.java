package br.com.devdojo.springboot2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.devdojo.springboot2.domian.Anime;

@Service
public class AnimeService {
	private static List<Anime> animes;
	
	static {
        animes = new ArrayList<>(List.of(new Anime(1L, "Boku No Hero"), new Anime(2L, "Berserk")));
    }

	public List<Anime> listAll() {
		return animes;
	}

	public Anime findById(Long id) {
		return animes.stream().filter(animes -> animes.getId().equals(id)).findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));
	}
	
	public Anime save(Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(3, 100000));
        animes.add(anime);
        return anime;
    }

    public void delete(long id) {
        animes.remove(findById(id));
    }
}
