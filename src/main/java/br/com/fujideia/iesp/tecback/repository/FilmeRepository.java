package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    @Query("select f from Filme f where f.anoLancamento=:ano")
    List<Filme> listarFilmesPorAno(@Param("ano") Integer ano);

    @Query("select f from Filme f where f.titulo like :titulo%")
    List<Filme> buscarPorNomeIniciadoCom(@Param("titulo") String titulo);

}
