package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Premio;
import br.com.fujideia.iesp.tecback.model.dto.PremioDTO;
import br.com.fujideia.iesp.tecback.repository.PremioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PremioService {

    private final PremioRepository premioRepository;

    public List<PremioDTO> listarTodos() {
        return premioRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<Premio> listarPorCategoriaEAno(String categoria, int ano){
        return premioRepository.listarPremioPorCategoriaEAno(categoria, ano);
    }

    public Optional<PremioDTO> buscarPorId(Long id) {
        return premioRepository.findById(id)
                .map(this::convertToDTO);
    }

    public PremioDTO criarPremio(PremioDTO premioDTO) {
        Premio premio = convertToEntity(premioDTO);
        return convertToDTO(premioRepository.save(premio));
    }

    public Optional<PremioDTO> atualizarPremio(Long id, PremioDTO premioDTO) {
        return premioRepository.findById(id).map(premio -> {
            premio.setId(premioDTO.getId());
            premio.setAno(premioDTO.getAno());
            premio.setVencedor(premioDTO.getVencedor());
            premio.setCategoria(premioDTO.getCategoria());
            return convertToDTO(premioRepository.save(premio));
        });
    }

    public boolean deletarPremio(Long id) {
        if (premioRepository.existsById(id)) {
            premioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private PremioDTO convertToDTO(Premio premio) {
        return new PremioDTO(
                premio.getId(),
                premio.getAno(),
                premio.getCategoria(),
                premio.getVencedor()
        );
    }

    private Premio convertToEntity(PremioDTO premioDTO) {
        Premio premio = new Premio();
        premio.setId(premioDTO.getId());
        premio.setAno(premioDTO.getAno());
        premio.setCategoria(premioDTO.getCategoria());
        premio.setVencedor(premioDTO.getVencedor());
        return premio;
    }
}
