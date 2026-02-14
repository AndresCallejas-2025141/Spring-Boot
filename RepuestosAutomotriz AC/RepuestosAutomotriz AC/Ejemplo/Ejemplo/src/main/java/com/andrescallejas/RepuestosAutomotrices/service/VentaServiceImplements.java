package com.andrescallejas.RepuestosAutomotrices.service;

import com.andrescallejas.RepuestosAutomotrices.entity.Venta;
import com.andrescallejas.RepuestosAutomotrices.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImplements implements VentaService{
    public final VentaRepository ventaRepository;
    public VentaServiceImplements(VentaRepository ventaRepository){
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
        Optional<Venta> VentaExistente = ventaRepository.findById(id);
        if (VentaExistente.isPresent()){
            Venta ventaNew = VentaExistente.get();
            ventaNew.setCantidad(venta.getCantidad());
            ventaNew.setFechaVenta(venta.getFechaVenta());
            ventaNew.setTotal(venta.getTotal());
            ventaNew.setIdEmpleado(venta.getIdEmpleado());
            ventaNew.setIdRepuesto(venta.getIdRepuesto());

            return ventaRepository.save(ventaNew);
        }else {
            return null;
        }
    }

    @Override
    public void deleteVenta(Integer id) {
        ventaRepository.deleteById(id);
    }
}
