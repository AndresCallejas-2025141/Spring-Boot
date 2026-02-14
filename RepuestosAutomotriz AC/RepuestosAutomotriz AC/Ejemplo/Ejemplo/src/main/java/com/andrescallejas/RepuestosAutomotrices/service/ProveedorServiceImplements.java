package com.andrescallejas.RepuestosAutomotrices.service;

import com.andrescallejas.RepuestosAutomotrices.entity.Proveedor;
import com.andrescallejas.RepuestosAutomotrices.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImplements implements ProvedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImplements(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Proveedor> getAllProveedor() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor getProveedorById(Integer id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) throws RuntimeException {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor updateProveedor(Integer id, Proveedor proveedor) {

        Optional<Proveedor> existente = proveedorRepository.findById(id);

        if (existente.isPresent()) {

            Proveedor proveedorExistente = existente.get();

            // Actualización campo por campo (igual que en el ejemplo)
            proveedorExistente.setEmailProveedor(proveedor.getEmailProveedor());
            proveedorExistente.setNombreProveedor(proveedor.getNombreProveedor());
            proveedorExistente.setTelefonoProveedor(proveedor.getTelefonoProveedor());
            proveedorExistente.setDireccion(proveedor.getDireccion());

            return proveedorRepository.save(proveedorExistente);
        } else {
            return null;
        }
    }

    @Override
    public void deleteProveedor(Integer id) {
        proveedorRepository.deleteById(id);
    }
}



