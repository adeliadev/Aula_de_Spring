package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Ator;
import br.com.fujideia.iesp.tecback.service.AtorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/atores")
public class AtorController {
    private final AtorService atorService;

    @GetMapping
    public List<Ator> listarTodos() {
        return atorService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ator> buscarPorId(@PathVariable Long id) {
        Optional<Ator> ator = atorService.buscarPorId(id);
        return ator.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ator> criarAtor(@RequestBody Ator ator) {
        Ator atorCriado = atorService.criarAtor(ator);
        return ResponseEntity.ok(atorCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ator> atualizarAtor(@PathVariable Long id, @RequestBody Ator atorDetalhes) {
        Optional<Ator> atorAtualizado = atorService.atualizarAtor(id, atorDetalhes);
        return atorAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtor(@PathVariable Long id) {
        boolean deletado = atorService.deletarAtor(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
