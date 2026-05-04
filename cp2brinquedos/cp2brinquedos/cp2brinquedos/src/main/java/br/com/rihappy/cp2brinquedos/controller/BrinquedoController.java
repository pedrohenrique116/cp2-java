package br.com.rihappy.cp2brinquedos.controller;

import br.com.rihappy.cp2brinquedos.dto.BrinquedoDTO;
import br.com.rihappy.cp2brinquedos.model.Brinquedo;
import br.com.rihappy.cp2brinquedos.repository.BrinquedoRepository;
import br.com.rihappy.cp2brinquedos.service.BrinquedoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/brinquedos")
public class BrinquedoController {

    @Autowired
    private BrinquedoService service;

    @PostMapping
    public ResponseEntity<Brinquedo> criar(@Valid @RequestBody BrinquedoDTO dto) {
        return new ResponseEntity<>(service.criar(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Brinquedo>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brinquedo> atualizar(@PathVariable Long id, @Valid @RequestBody BrinquedoDTO dto) {
        return service.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.deletar(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}