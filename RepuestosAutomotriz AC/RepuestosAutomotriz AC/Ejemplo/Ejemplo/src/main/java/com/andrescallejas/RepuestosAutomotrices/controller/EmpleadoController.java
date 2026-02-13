package com.andrescallejas.RepuestosAutomotrices.controller;

import com.andrescallejas.RepuestosAutomotrices.entity.Empleado;
import com.andrescallejas.RepuestosAutomotrices.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")

public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService){this.empleadoService = empleadoService;;}
    @GetMapping
    public List<Empleado> getAllEmpleados(){return empleadoService.getAllEmpleados();}

    @PostMapping
    public ResponseEntity<Object> createEmpleado(@Valid @RequestBody Empleado empleado){
        try {
            Empleado createdEmpleado = empleadoService.saveEmpleado(empleado);
            return new ResponseEntity<>(createdEmpleado, HttpStatus.CREATED);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Integer id, @Valid @RequestBody Empleado empleado) {

        Empleado updatedEmpleado = empleadoService.updateEmpleado(id, empleado);

        if (updatedEmpleado != null) {
            return ResponseEntity.ok(updatedEmpleado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Integer id) {
        empleadoService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}

