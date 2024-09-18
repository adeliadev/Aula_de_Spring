package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Ator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {

    @Query("select a from Ator a where a.nome like :nome%")
    List<Ator> buscarPorNomeIniciadoCom(@Param("nome") String nome);
}
