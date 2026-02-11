package com.andrescallejas.RepuestosAutomotrices.service;

import com.andrescallejas.RepuestosAutomotrices.model.Venta;
import com.andrescallejas.RepuestosAutomotrices.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImplements implements VentaService {
    private final VentaRepository ventaRepository;

    public VentaServiceImplements(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> getAllVenta() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta getVentaById(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public Venta saveVenta(Venta venta) throws RuntimeException {
        return ventaRepository.save(venta);
    }

    @Override
    public Venta updateVenta(Integer id, Venta venta) {
        Venta ventaExistente = ventaRepository.findById(id).orElse(null);

        if (ventaExistente != null) {
            venta.setIdVenta(id);
            return ventaRepository.save(venta);
        }
        return null;
    }

    @Override
    public void deleteVenta(Integer id) {
        ventaRepository.deleteById(id);
    }
}

