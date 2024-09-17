package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Diretor;
import br.com.fujideia.iesp.tecback.service.DiretorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diretores")
public class DiretorController {
    private final DiretorService diretorService;

    @GetMapping
    public List<Diretor> listarTodos() {
        return diretorService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diretor> buscarPorId(@PathVariable Long id) {
        Optional<Diretor> diretor = diretorService.buscarPorId(id);
        return diretor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Diretor> criarDiretor(@RequestBody Diretor diretor) {
        Diretor diretorCriado = diretorService.criarDiretor(diretor);
        return ResponseEntity.ok(diretorCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diretor> atualizarDiretor(@PathVariable Long id, @RequestBody Diretor diretorDetalhes) {
        Optional<Diretor> diretorAtualizado = diretorService.atualizarDiretor(id, diretorDetalhes);
        return diretorAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDiretor(@PathVariable Long id) {
        boolean deletado = diretorService.deletarDiretor(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


