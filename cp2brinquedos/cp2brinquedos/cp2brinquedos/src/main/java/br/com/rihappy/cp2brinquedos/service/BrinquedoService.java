package br.com.rihappy.cp2brinquedos.service;

import br.com.rihappy.cp2brinquedos.dto.BrinquedoDTO;
import br.com.rihappy.cp2brinquedos.model.Brinquedo;
import br.com.rihappy.cp2brinquedos.repository.BrinquedoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrinquedoService {

    @Autowired
    private BrinquedoRepository repository;

    public Brinquedo criar(BrinquedoDTO dto) {
        Brinquedo brinquedo = new Brinquedo();
        mapDtoToEntity(dto, brinquedo);
        return repository.save(brinquedo);
    }

    public List<Brinquedo> listarTodos() {
        return repository.findAll();
    }

    public Optional<Brinquedo> atualizar(Long id, BrinquedoDTO dto) {
        return repository.findById(id).map(brinquedo -> {
            mapDtoToEntity(dto, brinquedo);
            return repository.save(brinquedo);
        });
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // Método auxiliar para não repetir código de conversão
    private void mapDtoToEntity(BrinquedoDTO dto, Brinquedo entity) {
        entity.setNome(dto.getNome());
        entity.setTipo(dto.getTipo());
        entity.setClassificacao(dto.getClassificacao());
        entity.setTamanho(dto.getTamanho());
        entity.setPreco(dto.getPreco());
    }
}