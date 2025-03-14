package io.projetospring.expert.arquiteturaspring.repository;

import io.projetospring.expert.arquiteturaspring.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository <Autor, Long> {
}
