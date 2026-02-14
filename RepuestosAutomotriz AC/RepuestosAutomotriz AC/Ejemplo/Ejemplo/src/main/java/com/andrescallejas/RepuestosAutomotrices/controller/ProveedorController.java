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
    public final ProvedorService provedorService;

    public ProveedorController(ProvedorService provedorService){
        this.provedorService = provedorService;
    }

    @GetMapping
    public List<Proveedor> getAllProveedor(){
        return provedorService.getAllProveedor();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getProveedorById(@PathVariable Integer id){
        try {
            Proveedor provedor = provedorService.getProveedorById(id);
            if (provedor != null){
                return ResponseEntity.ok(provedor);
            }else {
                return ResponseEntity.status(404).body("No se encontro el proveedor");
            }
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> saveProveedor(@Valid @RequestBody Proveedor proveedor){
        try {
            if (proveedor.getNombreProveedor() == null || proveedor.getNombreProveedor().isBlank()) {
                return ResponseEntity.status(400).body("El nombre del proveedor es necesario");
            }
            if (proveedor.getDireccion() == null || proveedor.getDireccion().isBlank()) {
                return ResponseEntity.badRequest().body("La direccion del proveedor es necesario");
            }
            if (proveedor.getEmailProveedor() == null || proveedor.getEmailProveedor().isBlank()) {
                return ResponseEntity.badRequest().body("El email del proveedor es necesario");
            }
            if (proveedor.getTelefonoProveedor() == null) {
                return ResponseEntity.badRequest().body("El telefono del proveedor es necesario");
            }else {
                Proveedor createdP = provedorService.saveProveedor(proveedor);
                return new ResponseEntity<>(createdP, HttpStatus.CREATED);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProveedor(@Valid @PathVariable Integer id, @Valid @RequestBody Proveedor proveedor){
        try {
            if (proveedor.getNombreProveedor() == null || proveedor.getNombreProveedor().isBlank()) {
                return ResponseEntity.status(400).body("El nombre del proveedor es necesario");
            }
            if (proveedor.getDireccion() == null || proveedor.getDireccion().isBlank()) {
                return ResponseEntity.badRequest().body("La direccion del proveedor es necesario");
            }
            if (proveedor.getEmailProveedor() == null || proveedor.getEmailProveedor().isBlank()) {
                return ResponseEntity.badRequest().body("El email del proveedor es necesario");
            }
            if (proveedor.getTelefonoProveedor() == null) {
                return ResponseEntity.badRequest().body("El telefono del proveedor es necesario");
            }else {
                Proveedor existente = provedorService.getProveedorById(id);
                if (existente != null) {
                    Proveedor NewProveedor = provedorService.updateProveedor(id, proveedor);
                    return ResponseEntity.ok(NewProveedor);
                } else {
                    return ResponseEntity.status(404).body("No se encontro el proveedor id: " + id);
                }
            }
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProveedor(@Valid @PathVariable Integer id){
        try {
            Proveedor existente = provedorService.getProveedorById(id);
            if (existente != null){
                provedorService.deleteProveedor(id);
                return ResponseEntity.ok("Se elimino correcta mente el proveedor id: "+id);
            }else {
                return ResponseEntity.status(404).body("No se encontro el proveedor id: "+id);
            }
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
