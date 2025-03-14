package io.projetospring.expert.arquiteturaspring.service;

import io.projetospring.expert.arquiteturaspring.model.Autor;
import io.projetospring.expert.arquiteturaspring.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Autor> searchAutor(String nome, String nacionalidade){

        if(nome != null && nacionalidade != null){
            return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
        }

        if(nome != null){
            return autorRepository.findByNome(nome);
        }

        if(nacionalidade != null){
            return autorRepository.findByNacionalidade(nacionalidade);
        }

        return autorRepository.findAll();

    }

}
