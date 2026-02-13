package com.andrescallejas.RepuestosAutomotrices.service;
import com.andrescallejas.RepuestosAutomotrices.entity.Repuesto;

import java.util.List;

public interface RepuestoService {
    List<Repuesto> getAllRepuesto();
    Repuesto getRepuestoById(Integer id);
    Repuesto saveRepuesto (Repuesto repuesto) throws RuntimeException;
    Repuesto updateRepuesto(Integer id, Repuesto repuesto);
    void deleteRepuesto(Integer id);
}
