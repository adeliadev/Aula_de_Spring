package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.model.dto.FilmeDTO;
import br.com.fujideia.iesp.tecback.service.FilmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
@RequiredArgsConstructor
public class FilmeController {

    private final FilmeService filmeService;

    @GetMapping
    public List<FilmeDTO> listarTodos() {
        return filmeService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeDTO> buscarPorId(@PathVariable Long id) {
        Optional<FilmeDTO> filme = filmeService.buscarPorId(id);
        return filme.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/{ano}")
    public List<Filme> listarFilmesPorAno(@PathVariable Integer ano) {
        return filmeService.listarFilmesPorAno(ano);
    }

    @GetMapping("/buscar/{titulo}")
    public List<Filme> buscarPorNomeIniciadoCom(@PathVariable String titulo) {
        return filmeService.buscarPorNomeIniciadoCom(titulo);
    }

    @GetMapping("/buscar/{nome}")
    public List<Filme> buscarFilmePorNome(@PathVariable String nome) {
        return filmeService.buscarFilmePorNome(nome);
    }

    @PostMapping
    public ResponseEntity<FilmeDTO> criarFilme(@RequestBody FilmeDTO filme) {
        FilmeDTO filmeCriado = filmeService.criarFilme(filme);
        return ResponseEntity.ok(filmeCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmeDTO> atualizarFilme(@PathVariable Long id, @RequestBody FilmeDTO filmeDetalhes) {
        Optional<FilmeDTO> filmeAtualizado = filmeService.atualizarFilme(id, filmeDetalhes);
        return filmeAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id) {
        boolean deletado = filmeService.deletarFilme(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
