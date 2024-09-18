package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiretorRepository extends JpaRepository<Diretor, Long> {

    @Query("select d from Diretor d where d.nome like :nome%")
    List<Diretor> listarDiretorPorNome(@Param("nome") String nome);
}
