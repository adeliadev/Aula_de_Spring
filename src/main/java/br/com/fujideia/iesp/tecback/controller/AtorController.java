package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Ator;
import br.com.fujideia.iesp.tecback.repository.AtorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/atores")
public class AtorController {
    private final AtorRepository atorRepository;

    @GetMapping
    public List<Ator> listarTodos() {
        return atorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ator> buscarPorId(@PathVariable Long id) {
        Optional<Ator> ator = atorRepository.findById(id);
        return ator.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ator adicionarAtor(@RequestBody Ator ator) {
        return atorRepository.save(ator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ator> atualizarAtor(@PathVariable Long id, @RequestBody Ator atorDetalhes) {
        Optional<Ator> atorOptional = atorRepository.findById(id);
        if (atorOptional.isPresent()){
            Ator ator = atorOptional.get();
            ator.setNome(atorDetalhes.getNome());
            ator.setFilmes(atorDetalhes.getFilmes());

            Ator atorAtualizado = atorRepository.save(ator);
            return ResponseEntity.ok(atorAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ator> deletarAtor(@PathVariable Long id) {
        if (atorRepository.existsById(id)){
            atorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
