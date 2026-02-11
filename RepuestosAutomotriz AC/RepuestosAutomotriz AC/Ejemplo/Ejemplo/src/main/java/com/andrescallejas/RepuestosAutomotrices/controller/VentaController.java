package com.andrescallejas.RepuestosAutomotrices.controller;
import com.andrescallejas.RepuestosAutomotrices.model.Venta;
import com.andrescallejas.RepuestosAutomotrices.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;
    public VentaController(VentaService ventaService){this.ventaService = ventaService;;}
    @GetMapping
    public List<Venta> getAllVenta(){return ventaService.getAllVenta();}

    @PostMapping
    public ResponseEntity<Object> createVenta(@Valid @RequestBody Venta venta){
        try {
            Venta createdVenta = ventaService.saveVenta(venta);
            return new ResponseEntity<>(createdVenta, HttpStatus.CREATED);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable Integer id, @Valid @RequestBody Venta venta) {

        Venta updatedVenta = ventaService.updateVenta(id, venta);

        if (updatedVenta != null) {
            return ResponseEntity.ok(updatedVenta);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Integer id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }
}
