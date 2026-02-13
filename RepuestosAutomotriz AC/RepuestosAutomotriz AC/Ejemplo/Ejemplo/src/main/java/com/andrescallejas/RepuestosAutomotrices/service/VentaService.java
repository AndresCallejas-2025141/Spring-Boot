package com.andrescallejas.RepuestosAutomotrices.service;
import com.andrescallejas.RepuestosAutomotrices.entity.Venta;

import java.util.List;
public interface VentaService {
    List<Venta> getAllVenta();
    Venta getVentaById(Integer id);
    Venta saveVenta (Venta venta) throws RuntimeException;
    Venta updateVenta(Integer id, Venta venta);
    void deleteVenta(Integer id);
}
