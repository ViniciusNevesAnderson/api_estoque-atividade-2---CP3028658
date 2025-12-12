package com.controleestoque.api_estoque.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
//adicionado
import org.springframework.http.ResponseEntity;
//
import org.springframework.web.bind.annotation.*;

import com.controleestoque.api_estoque.model.Fornecedor;
import com.controleestoque.api_estoque.repository.FornecedorRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorRepository fornecedorRepository;

    @GetMapping
    public List<Fornecedor> getAllFornecedores() {
        return fornecedorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> getFornecedorById(@PathVariable Long id) {
        return fornecedorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fornecedor createFornecedor(@RequestBody Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> updateFornecedor(
            @PathVariable Long id, @RequestBody Fornecedor fornecedorAtualizado) {

        return fornecedorRepository.findById(id)
                .map(fornecedor -> {
                    fornecedor.setNome(fornecedorAtualizado.getNome());
                    return ResponseEntity.ok(fornecedorRepository.save(fornecedor));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //modificado 
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFornecedor(@PathVariable Long id) {
        return fornecedorRepository.findById(id)
                .map(fornecedor -> {
                    fornecedorRepository.delete(fornecedor);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
