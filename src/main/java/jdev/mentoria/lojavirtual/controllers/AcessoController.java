package jdev.mentoria.lojavirtual.controllers;

import jdev.mentoria.lojavirtual.dtos.AcessoDTO;
import jdev.mentoria.lojavirtual.services.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/acessos")
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    @PostMapping
    public ResponseEntity<AcessoDTO> insert(@Valid @RequestBody AcessoDTO dto) {
        dto = acessoService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AcessoDTO> findById(@PathVariable Long id) {
        AcessoDTO list = acessoService.findById(id);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AcessoDTO> update(@PathVariable Long id, @Valid @RequestBody AcessoDTO dto) {
        dto = acessoService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AcessoDTO> findAll() {
        List<AcessoDTO> dto = acessoService.findAll();
        return dto;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        acessoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
