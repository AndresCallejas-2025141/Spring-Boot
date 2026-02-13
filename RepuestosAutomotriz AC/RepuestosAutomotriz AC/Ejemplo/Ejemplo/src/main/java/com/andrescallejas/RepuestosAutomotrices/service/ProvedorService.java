package com.andrescallejas.RepuestosAutomotrices.service;

import com.andrescallejas.RepuestosAutomotrices.entity.Proveedor;

import java.util.List;

public interface ProvedorService {
    List<Proveedor> getAllProveedor();
    Proveedor getProveedorById(Integer id);
    Proveedor saveProveedor (Proveedor proveedor) throws RuntimeException;
    Proveedor updateProveedor(Integer id, Proveedor proveedor);
    void deleteProveedor(Integer id);
}
