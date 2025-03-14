package io.projetospring.expert.arquiteturaspring.dto.request;

import io.projetospring.expert.arquiteturaspring.model.Autor;

import java.time.LocalDate;

/**
 * Classes Records são classes imutávies, ou seja, não modificamos os valores
 *
 * DTO é um objeto que representa um json
 * @param nome
 * @param dataNascimento
 * @param nacionalidade
 */

public record AutorDTO (

        Long id,
        String nome,
        LocalDate dataNascimento,
        String nacionalidade)
{

    //converendo um autor para autordto
    public Autor convertToAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);

        return autor;
    }

}
