package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Premio;
import br.com.fujideia.iesp.tecback.model.dto.PremioDTO;
import br.com.fujideia.iesp.tecback.service.PremioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/premios")
public class PremioController {

    private final PremioService premioService;

    @GetMapping
    public List<PremioDTO> listarTodos() {
        return premioService.listarTodos();
    }

    @GetMapping("buscar/{categoria}/{ano}")
    public List<Premio> listarPorCategoriaEAno(@PathVariable String categoria,@PathVariable int ano) {
        return premioService.listarPorCategoriaEAno(categoria, ano);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PremioDTO> buscarPorId(@PathVariable Long id) {
        Optional<PremioDTO> premio = premioService.buscarPorId(id);
        return premio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PremioDTO> criarPremio(@RequestBody PremioDTO premio) {
        PremioDTO premioCriado = premioService.criarPremio(premio);
        return ResponseEntity.ok(premioCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PremioDTO> atualizarPremio(@PathVariable Long id, @RequestBody PremioDTO premioDetalhes) {
        Optional<PremioDTO> premioAtualizado = premioService.atualizarPremio(id, premioDetalhes);
        return premioAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPremio(@PathVariable Long id) {
        boolean deletado = premioService.deletarPremio(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
