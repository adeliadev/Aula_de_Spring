package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

    @Query("select g from Genero g where g.nome=:nome")
    Genero listarGeneroPorNome(@Param("nome") String nome);
}
