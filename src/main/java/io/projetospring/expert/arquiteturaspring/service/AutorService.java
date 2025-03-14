package io.projetospring.expert.arquiteturaspring.service;

import io.projetospring.expert.arquiteturaspring.model.Autor;
import io.projetospring.expert.arquiteturaspring.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    public Autor salvarAutor(Autor autor){
        return autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(Long id){
        return autorRepository.findById(id);
    }

    public void deleteAutor(Autor autor) {
        autorRepository.delete(autor);
    }
}
