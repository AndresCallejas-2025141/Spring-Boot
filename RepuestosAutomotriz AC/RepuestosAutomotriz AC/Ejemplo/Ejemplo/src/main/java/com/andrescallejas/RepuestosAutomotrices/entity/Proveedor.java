package com.andrescallejas.RepuestosAutomotrices.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name ="Proveedores")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @NotBlank(message = "El nombre del proveedor no puede estar vacío")
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;

    @NotNull(message = "El teléfono es obligatorio")
    @Min(value = 10000000, message = "El teléfono debe tener al menos 8 dígitos")
    private Integer telefonoProveedor;


    @NotBlank(message = "La direccion del proveedor no puede estar vacío")
    @Column(name = "direccion")
    private String direccion;

    @NotBlank(message = "El email del proveedor no puede estar vacío")
    @Column(name = "email_proveedor")
    private String emailProveedor;


    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Integer getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(Integer telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmailProveedor() {
        return emailProveedor;
    }

    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
    }
}
