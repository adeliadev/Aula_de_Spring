package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Premio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PremioRepository extends JpaRepository<Premio, Long> {

    @Query("select p from Premio p where p.categoria=:categoria and p.ano=:ano")
    List<Premio> listarPremioPorCategoriaEAno(@Param("categoria") String categoria, @Param("ano") int ano);
}
