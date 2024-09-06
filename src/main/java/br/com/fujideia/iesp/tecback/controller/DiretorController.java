package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Diretor;
import br.com.fujideia.iesp.tecback.repository.DiretorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diretores")
public class DiretorController {
    private final DiretorRepository diretorRepository;

    @GetMapping
    public List<Diretor> listarTodos() {
        return diretorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diretor> buscarPorId(@PathVariable Long id) {
        Optional<Diretor> diretor = diretorRepository.findById(id);
        return diretor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diretor> atualizarDiretor(@PathVariable Long id, @RequestBody Diretor diretorDetalhes) {
        Optional<Diretor> diretorOptional = diretorRepository.findById(id);
        if (diretorOptional.isPresent()) {
            Diretor diretor = diretorOptional.get();
            diretor.setNome(diretorDetalhes.getNome());
            diretor.setFilmesDirigidos(diretorDetalhes.getFilmesDirigidos());

            Diretor diretorAtualizado = diretorRepository.save(diretorDetalhes);
            return ResponseEntity.ok(diretorAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDiretor(@PathVariable Long id) {
        if (diretorRepository.existsById(id)) {
            diretorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


