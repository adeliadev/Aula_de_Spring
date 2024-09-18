package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Genero;
import br.com.fujideia.iesp.tecback.model.dto.GeneroDTO;
import br.com.fujideia.iesp.tecback.service.GeneroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/generos")
public class GeneroController {
    private final GeneroService generoService;

    @GetMapping
    public List<GeneroDTO> listarTodos() {
        return generoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> buscarPorId(@PathVariable Long id) {
        Optional<GeneroDTO> genero = generoService.buscarPorId(id);
        return genero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/{nome}")
    public Genero buscarGeneroPorNome(@PathVariable String nome) {
        return generoService.buscarGeneroPorNome(nome);
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> criarGenero(@RequestBody GeneroDTO genero) {
        GeneroDTO generoCriado = generoService.criarGenero(genero);
        return ResponseEntity.ok(generoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroDTO> atualizarGenero(@PathVariable Long id, @RequestBody Genero generoDetalhes) {
        Optional<GeneroDTO> generoAtualizado = generoService.atualizarGenero(id, generoDetalhes);
        return generoAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGenero(@PathVariable Long id) {
        boolean deletado = generoService.deletarGenero(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
