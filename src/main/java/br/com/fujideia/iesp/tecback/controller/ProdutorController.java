package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Produtor;
import br.com.fujideia.iesp.tecback.model.dto.FilmeDTO;
import br.com.fujideia.iesp.tecback.model.dto.ProdutorDTO;
import br.com.fujideia.iesp.tecback.service.ProdutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produtores")
public class ProdutorController {
    private final ProdutorService produtorService;

    @GetMapping
    public List<ProdutorDTO> listarTodos() {
        return produtorService.listarTodos();
    }

    @GetMapping("/{produtorId}/filmes")
    public ResponseEntity<List<String>> listarFilmes(@PathVariable Long produtorId) {
        List<String> filmes = produtorService.listarFilmes(produtorId);
        if (filmes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(filmes);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutorDTO> buscarPorId(@PathVariable Long id) {
        Optional<ProdutorDTO> produtor = produtorService.buscarPorId(id);
        return produtor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/{nome}")
    public List<Produtor> buscarPorNome(@PathVariable String nome) {
        return produtorService.buscarProdutorPorNome(nome);
    }

    @PostMapping
    public ResponseEntity<ProdutorDTO> criarProdutor(@RequestBody ProdutorDTO produtor) {
        ProdutorDTO produtorCriado = produtorService.criarProdutor(produtor);
        return ResponseEntity.ok(produtorCriado);
    }

    @PostMapping("/{id}/filmes")
    public ResponseEntity<ProdutorDTO> adicionarFilme(@PathVariable Long id, @RequestBody FilmeDTO filmeDTO) {
        Optional<ProdutorDTO> produtorAtualizado = produtorService.adicionarFilme(id, filmeDTO);
        return produtorAtualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutorDTO> atualizarProdutor(@PathVariable Long id, @RequestBody ProdutorDTO produtorDetalhes) {
        Optional<ProdutorDTO> produtorAtualizado = produtorService.atualizarProdutor(id, produtorDetalhes);
        return produtorAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProdutor(@PathVariable Long id) {
        boolean deletado = produtorService.deletarProdutor(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
