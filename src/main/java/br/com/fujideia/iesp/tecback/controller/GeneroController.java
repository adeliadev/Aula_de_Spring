package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Genero;
import br.com.fujideia.iesp.tecback.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/generos")
public class GeneroController {
    private final GeneroRepository generoRepository;

    @GetMapping
    public List<Genero> listarTodos() {
        return generoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> buscarPorId(@PathVariable Long id) {
        Optional<Genero> genero = generoRepository.findById(id);
        return genero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Genero criarFilme(@RequestBody Genero genero) {
        return generoRepository.save(genero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> atualizarGenero(@PathVariable Long id, @RequestBody Genero generoDetalhes) {
        Optional<Genero> generoOptional = generoRepository.findById(id);
        if (generoOptional.isPresent()) {
            Genero genero = generoOptional.get();
            genero.setNome(generoDetalhes.getNome());

            Genero generoAtualizado = generoRepository.save(genero);
            return ResponseEntity.ok(generoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id) {
        if (generoRepository.existsById(id)) {
            generoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
