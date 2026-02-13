package com.andrescallejas.RepuestosAutomotrices.controller;

import com.andrescallejas.RepuestosAutomotrices.entity.Proveedor;
import com.andrescallejas.RepuestosAutomotrices.service.ProvedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProvedorService provedorService;
    public ProveedorController(ProvedorService provedorService){this.provedorService = provedorService;;}
    @GetMapping
    public List<Proveedor> getAllProveedor(){return provedorService.getAllProveedor();}

    @PostMapping
    public ResponseEntity<Object> createProveedor(@Valid @RequestBody Proveedor proveedor){
        try {
            Proveedor createdProveedor = provedorService.saveProveedor(proveedor);
            return new ResponseEntity<>(createdProveedor, HttpStatus.CREATED);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> updateProveedor(@PathVariable Integer id, @Valid @RequestBody Proveedor proveedor) {

        Proveedor updatedProveedor = provedorService.updateProveedor(id, proveedor);

        if (updatedProveedor != null) {
            return ResponseEntity.ok(updatedProveedor);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Integer id) {
        provedorService.deleteProveedor(id);
        return ResponseEntity.noContent().build();
    }
}

