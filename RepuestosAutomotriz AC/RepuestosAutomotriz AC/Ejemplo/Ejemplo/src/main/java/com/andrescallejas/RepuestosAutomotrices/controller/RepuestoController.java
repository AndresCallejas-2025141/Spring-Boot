package com.andrescallejas.RepuestosAutomotrices.controller;
import com.andrescallejas.RepuestosAutomotrices.entity.Repuesto;
import com.andrescallejas.RepuestosAutomotrices.service.RepuestoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repuestos")
public class RepuestoController {

    private final RepuestoService repuestoService;
    public RepuestoController(RepuestoService repuestoService){this.repuestoService = repuestoService;;}
    @GetMapping
    public List<Repuesto> getAllRepuesto(){return repuestoService.getAllRepuesto();}

    @PostMapping
    public ResponseEntity<Object> createRepuesto(@Valid @RequestBody Repuesto repuesto){
        try {
            Repuesto createdRepuesto = repuestoService.saveRepuesto(repuesto);
            return new ResponseEntity<>(createdRepuesto, HttpStatus.CREATED);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Repuesto> updateRepuesto(@PathVariable Integer id, @Valid @RequestBody Repuesto repuesto) {

        Repuesto updatedRepuesto = repuestoService.updateRepuesto(id, repuesto);

        if (updatedRepuesto != null) {
            return ResponseEntity.ok(updatedRepuesto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepuesto(@PathVariable Integer id) {
        repuestoService.deleteRepuesto(id);
        return ResponseEntity.noContent().build();
    }
}
