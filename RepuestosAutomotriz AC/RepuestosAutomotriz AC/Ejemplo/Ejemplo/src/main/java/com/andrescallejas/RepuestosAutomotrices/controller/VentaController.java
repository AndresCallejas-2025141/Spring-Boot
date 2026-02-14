package com.andrescallejas.RepuestosAutomotrices.controller;
import com.andrescallejas.RepuestosAutomotrices.entity.Venta;
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
    public VentaService ventaService;
    public VentaController(VentaService ventaService){
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<Venta> getAllVenta(){return ventaService.getAllVenta();}

    @GetMapping("/{id}")
    public ResponseEntity<?> getVentaById(@Valid @PathVariable Integer id){
        try {
            Venta venta = ventaService.getVentaById(id);
            if (venta != null){
                return ResponseEntity.ok(venta);
            }else return ResponseEntity.status(404).body("No se encontro la venta id: "+id);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> saveVenta(@RequestBody Venta venta){
        try {
            if (venta.getFechaVenta() == null ) {
                return ResponseEntity.status(400).body("La fecha de venta es necesaria");
            }
            if (venta.getCantidad() == null ) {
                return ResponseEntity.badRequest().body("La cantidad de la venta es necesario");
            }
            if (venta.getTotal() == null ) {
                return ResponseEntity.badRequest().body("El total de la venta es necesario");
            }
            if (venta.getIdEmpleado() == null) {
                return ResponseEntity.badRequest().body("El Id de empleado es necesario");
            }
            if (venta.getIdRepuesto() == null) {
                return ResponseEntity.badRequest().body("El id del repuesto es necesario");
            }
            Venta createVenta = ventaService.saveVenta(venta);
            return new ResponseEntity<>(createVenta, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVenta(@PathVariable Integer id, @RequestBody Venta venta){
        try {
            if (venta.getFechaVenta() == null ) {
                return ResponseEntity.status(400).body("La fecha de venta es necesaria");
            }
            if (venta.getCantidad() == null ) {
                return ResponseEntity.badRequest().body("La cantidad de la venta es necesario");
            }
            if (venta.getTotal() == null ) {
                return ResponseEntity.badRequest().body("El total de la venta es necesario");
            }
            if (venta.getIdEmpleado() == null) {
                return ResponseEntity.badRequest().body("El Id de empleado es necesario");
            }
            if (venta.getIdRepuesto() == null) {
                return ResponseEntity.badRequest().body("El id del repuesto es necesario");
            }
            Venta NewVentas = ventaService.updateVenta(id,venta);
            return ResponseEntity.ok(NewVentas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("No se encontro el id o no se ingreso algun dato" +e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Integer id){
        try {
            Venta existente = ventaService.getVentaById(id);
            if (existente != null){
                ventaService.deleteVenta(id);
                return ResponseEntity.ok("Se elimino Correctamente venta id: "+id);
            }else {
                return ResponseEntity.status(404).body("No se encontro la venta id: "+id);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
