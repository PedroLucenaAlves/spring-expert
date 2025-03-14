package io.projetospring.expert.arquiteturaspring.controller;

import io.projetospring.expert.arquiteturaspring.dto.request.AutorDTO;
import io.projetospring.expert.arquiteturaspring.dto.response.AutorResponseDto;
import io.projetospring.expert.arquiteturaspring.model.Autor;
import io.projetospring.expert.arquiteturaspring.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

/**
 * A camada de serveço trata do domínio da aplicação. É a ponte entre o controller e o repository executando as regras de negócios
 */

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    public AutorService autorService;

    @PostMapping      //ResponseEntity e um tipo de classe que representa uma resposta  / @RequestBody = indica que o objeto vem no body
    public ResponseEntity<Void> saveAutor(@RequestBody AutorDTO autor){

        var autorEntidade = autor.convertToAutor(); //convertendo o dto para o tipo entidade

        autorService.salvarAutor(autorEntidade);

        //Construindo url para visualizar os dados do autor cadastrado conforme pedido em contrato
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}") //aqui temos o id do autor cadastrado que aparece no campo header na response da requisicao
                .buildAndExpand(autorEntidade.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorResponseDto> obterDetalhes(@PathVariable String id){
        Long idAutor = Long.parseLong(id);

        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

        //verificando a existencia de um autor
        if(autorOptional.isPresent()){

            Autor autor = autorOptional.get();
            AutorDTO dto = new AutorDTO(autor.getId(),
                    autor.getNome(),
                    autor.getDataNascimento(),
                    autor.getNacionalidade());

        AutorResponseDto response = new AutorResponseDto("Detalhes do autor ecnontrado com sucesso !:", dto);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new AutorResponseDto("Autor não encontrado!", null)); //se nao existir 404

    }

    @DeleteMapping("{id}")
    public ResponseEntity<AutorResponseDto> deleteAutor(@PathVariable("id") Long id){

        Autor autor = autorService.obterPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado!"));
        //orElseThrow lança um erro caso o resultado esperado nao seja encontrado. "No nosso caso se o id não for encontrado"

        autorService.deleteAutor(autor);

        AutorDTO dto = new AutorDTO(
                autor.getId(),
                autor.getNome(),
                autor.getDataNascimento(),
                autor.getNacionalidade()
        );

        AutorResponseDto response = new AutorResponseDto("Autor deletado com sucesso!", dto);

        return ResponseEntity.ok(response);

    }


}
