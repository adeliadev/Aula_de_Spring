package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.service.FilmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService service;

    @GetMapping
    public List<Filme> listarTodos() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public Filme criarFilme(@RequestBody Filme filme) {
        return service.c;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Long id, @RequestBody Filme filmeDetalhes) {
        Optional<Filme> filmeOptional = service.findById(id);
        if (filmeOptional.isPresent()) {
            Filme filme = filmeOptional.get();
            filme.setTitulo(filmeDetalhes.getTitulo());
            filme.setAnoLancamento(filmeDetalhes.getAnoLancamento());
            filme.setDiretor(filmeDetalhes.getDiretor());
            filme.setAtores(filmeDetalhes.getAtores());
            filme.setGeneros(filmeDetalhes.getGeneros());
            Filme filmeAtualizado = service.save(filme);
            return ResponseEntity.ok(filmeAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id) {
        if (service.existsById(id)) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
