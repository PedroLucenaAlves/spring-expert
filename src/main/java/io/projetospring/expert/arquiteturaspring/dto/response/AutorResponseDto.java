package io.projetospring.expert.arquiteturaspring.dto.response;

import io.projetospring.expert.arquiteturaspring.dto.request.AutorDTO;

public class AutorResponseDto {

    private String mensage;
    private AutorDTO autor;

    public AutorResponseDto(String mensage, AutorDTO autor){
        this.mensage = mensage;
        this.autor= autor;
    }

    public String getMensagem() {
        return mensage;
    }

    public AutorDTO getAutor() {
        return autor;
    }

}
