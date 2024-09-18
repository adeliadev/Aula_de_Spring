package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Diretor;
import br.com.fujideia.iesp.tecback.model.dto.DiretorDTO;
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
    public List<DiretorDTO> listarTodos() {
        return diretorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiretorDTO> buscarPorId(@PathVariable Long id) {
        Optional<DiretorDTO> diretor = diretorService.buscarPorId(id);
        return diretor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/{nome}")
    public List<Diretor> listarDiretorPorNome(@PathVariable String nome) {
        return diretorService.listarDiretorPorNome(nome);
    }

    @PostMapping
    public ResponseEntity<DiretorDTO> criarDiretor(@RequestBody Diretor diretor) {
        DiretorDTO diretorCriado = diretorService.criarDiretor(diretor);
        return ResponseEntity.ok(diretorCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiretorDTO> atualizarDiretor(@PathVariable Long id, @RequestBody DiretorDTO diretorDetalhes) {
        Optional<DiretorDTO> diretorAtualizado = diretorService.atualizarDiretor(id, diretorDetalhes);
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


