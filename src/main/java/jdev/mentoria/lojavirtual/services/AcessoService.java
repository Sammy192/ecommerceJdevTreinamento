package jdev.mentoria.lojavirtual.services;

import jdev.mentoria.lojavirtual.dtos.AcessoDTO;
import jdev.mentoria.lojavirtual.model.Acesso;
import jdev.mentoria.lojavirtual.repositories.AcessoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcessoService {

    @Autowired
    private AcessoRepository acessoRepository;

    private ModelMapper mapper = new ModelMapper();

    @Transactional
    public AcessoDTO insert(AcessoDTO dto) {
        Acesso entity = mapper.map(dto, Acesso.class);

        entity = acessoRepository.save(entity);

        return mapper.map(entity, AcessoDTO.class);
    }

    @Transactional
    public AcessoDTO findById(Long id) {
        Acesso acesso = acessoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return mapper.map(acesso, AcessoDTO.class);
    }

    @Transactional
    public List<AcessoDTO> findAll() {
        List<Acesso> result = acessoRepository.findAll();
        return result.stream().map(x -> mapper.map(x, AcessoDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        if (!acessoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            acessoRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Falha de integridade referencial");
        }
    }

    @Transactional
    public AcessoDTO update(Long id, AcessoDTO dto) {
        Acesso entity = acessoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        entity.setPerfil(dto.getPerfil());
        entity = acessoRepository.save(entity);
        return mapper.map(entity, AcessoDTO.class);
    }
}
