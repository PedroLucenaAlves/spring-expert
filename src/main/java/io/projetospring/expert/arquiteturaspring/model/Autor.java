package io.projetospring.expert.arquiteturaspring.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor", schema = "public")
@Getter
@Setter
@ToString(exclude = {"livros"})
@EntityListeners(AuditingEntityListener.class)
public class Autor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Livro> livros;

    //toda vez que persistir ele ja ira colocar a data e hora atual
    @CreatedDate
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    //todo update ele ira atualizar com a data atual
    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "id_usuario", columnDefinition = "CHAR(36")
    private  String idUsuario = UUID.randomUUID().toString();

}
