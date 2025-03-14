package io.projetospring.expert.arquiteturaspring.repository;

import io.projetospring.expert.arquiteturaspring.model.Autor;
import io.projetospring.expert.arquiteturaspring.model.GeneroLivro;
import io.projetospring.expert.arquiteturaspring.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    List<Livro> findByTituloOrIsbnOrderByTitulo(String titulo, String isbn);

    // select * from livro where data_publicacao between ? and ?
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    // JPQL -> referencia as entidades e as propriedades
    // select l.* from livro as l order by l.titulo
    @Query(" select l from Livro as l order by l.titulo, l.preco ")
    List<Livro> listarTodosOrdenadoPorTituloAndPreco();

    /**
     * select a.*
     * from livro l
     * join autor a on a.id = l.id_autor
     */
    @Query("select a from Livro l join l.autor a ")
    List<Autor> listarAutoresDosLivros();

    // select distinct l.* from livro l
    @Query("select distinct l.titulo from Livro l")
    List<String> listarNomesDiferentesLivros();

    @Query("""
        select l.genero
        from Livro l
        join l.autor a
        where a.nacionalidade = 'Brasileira'
        order by l.genero
    """)
    List<String> listarGenerosAutoresBrasileiros();

    // named parameters -> parametros nomeados
    @Query("select l from Livro l where l.genero = :genero order by :paramOrdenacao ")
    List<Livro> findByGenero(
            @Param("genero") GeneroLivro generoLivro,
            @Param("paramOrdenacao") String nomePropriedade
    );

    // positional parameters
    @Query("select l from Livro l where l.genero = ?2 order by ?1 ")
    List<Livro> findByGeneroPositionalParameters(String nomePropriedade, GeneroLivro generoLivro);

    @Modifying
    @Transactional
    @Query(" delete from Livro where genero = ?1 ")
    void deleteByGenero(GeneroLivro genero);

    @Modifying
    @Transactional
    @Query(" update Livro set dataPublicacao = ?1 ")
    void updateDataPublicacao(LocalDate novaData);

}
