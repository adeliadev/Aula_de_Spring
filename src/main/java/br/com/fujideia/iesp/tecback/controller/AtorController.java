package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Ator;
import br.com.fujideia.iesp.tecback.model.dto.AtorDTO;
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
    public List<AtorDTO> listarTodos() {
        return atorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtorDTO> buscarPorId(@PathVariable Long id) {
        Optional<AtorDTO> ator = atorService.buscarPorId(id);
        return ator.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/{letraInicial}")
    public List<Ator> buscarPorNomeIniciadoCom(@PathVariable String letraInicial) {
        return atorService.buscarPorNomeIniciadoCom(letraInicial);
    }

    @PostMapping
    public ResponseEntity<AtorDTO> criarAtor(@RequestBody AtorDTO ator) {
        AtorDTO atorCriado = atorService.criarAtor(ator);
        return ResponseEntity.ok(atorCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtorDTO> atualizarAtor(@PathVariable Long id, @RequestBody AtorDTO atorDetalhes) {
        Optional<AtorDTO> atorAtualizado = atorService.atualizarAtor(id, atorDetalhes);
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
