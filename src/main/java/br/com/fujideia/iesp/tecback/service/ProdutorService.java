package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.model.Produtor;
import br.com.fujideia.iesp.tecback.model.dto.*;
import br.com.fujideia.iesp.tecback.repository.AtorRepository;
import br.com.fujideia.iesp.tecback.repository.ProdutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProdutorService {
    private final ProdutorRepository produtorRepository;
    private final AtorRepository atorRepository;

    public List<ProdutorDTO> listarTodos() {
        return produtorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProdutorDTO> buscarPorId(Long id) {
        return produtorRepository.findById(id)
                .map(this::convertToDTO);
    }

    public ProdutorDTO criarProdutor(ProdutorDTO produtorDTO) {
        Produtor produtor = convertToEntity(produtorDTO);
        return convertToDTO(produtorRepository.save(produtor));
    }

    public Optional<ProdutorDTO> adicionarFilme(Long id, FilmeDTO filmeDTO) {
        return produtorRepository.findById(id).map(produtor -> {
            Filme filme = convertToEntity(filmeDTO);
            produtor.getFilmesProduzidos().add(filme);
            produtorRepository.save(produtor);
            return convertToDTO(produtor);
        });
    }

    public List<String> listarFilmes(Long produtorId) {
        return produtorRepository.findById(produtorId)
                .map(produtor -> produtor.getFilmesProduzidos()
                        .stream()
                        .map(Filme::getTitulo)
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }

    private Filme convertToEntity(FilmeDTO filmeDTO) {
        Filme filme = new Filme();
        filme.setId(filmeDTO.getId());
        filme.setTitulo(filmeDTO.getTitulo());
        return filme;
    }

    public Optional<ProdutorDTO> atualizarProdutor(Long id, ProdutorDTO produtorDTO) {
        return produtorRepository.findById(id).map(produtor -> {
            produtor.setId(produtorDTO.getId());
            produtor.setNome(produtorDTO.getNome());
            produtor.setNacionalidade(produtorDTO.getNacionalidade());
            return  convertToDTO(produtorRepository.save(produtor));
        });
    }

    public boolean deletarProdutor(Long id) {
        if (produtorRepository.existsById(id)) {
            produtorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ProdutorDTO convertToDTO(Produtor produtor) {
        return new ProdutorDTO(
                produtor.getId(),
                produtor.getNome(),
                produtor.getNacionalidade()
        );
    }

    private Produtor convertToEntity(ProdutorDTO produtorDTO) {
        Produtor produtor = new Produtor();
        produtor.setId(produtorDTO.getId());
        produtor.setNome(produtorDTO.getNome());
        produtor.setNacionalidade(produtorDTO.getNacionalidade());
        return produtor;
    }

    public List<Produtor> buscarProdutorPorNome(String nome) {
        return produtorRepository.buscarProdutorPorNome(nome);
    }


}
