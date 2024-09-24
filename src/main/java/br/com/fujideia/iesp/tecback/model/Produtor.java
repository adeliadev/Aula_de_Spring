package br.com.fujideia.iesp.tecback.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Produtor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nacionalidade;

    @ManyToMany(mappedBy = "produtores")
    private List<Filme> filmesProduzidos;
}
