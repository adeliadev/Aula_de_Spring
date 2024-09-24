package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Produtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutorRepository extends JpaRepository<Produtor, Long> {

    @Query("select p from Produtor p where p.nome=:nome")
    List<Produtor> buscarProdutorPorNome(@Param("nome") String nome);
}
