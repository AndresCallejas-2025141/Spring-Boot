package com.andrescallejas.RepuestosAutomotrices.service;

import com.andrescallejas.RepuestosAutomotrices.model.Empleado;
import com.andrescallejas.RepuestosAutomotrices.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImplements implements EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImplements(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado getEmpleadoById(Integer id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) throws RuntimeException {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado updateEmpleado(Integer id, Empleado empleado) {
        Empleado empleadoExistente = empleadoRepository.findById(id).orElse(null);

        if (empleadoExistente != null) {
            empleado.setIdEmpleado(id);
            return empleadoRepository.save(empleado);
        }
        return null;
    }

    @Override
    public void deleteEmpleado(Integer id) {
    empleadoRepository.deleteById(id);
    }
}
