create database DBRepuestosAutomotriz_in5cm;
use DBRepuestosAutomotriz_in5cm;

create table Proveedores(
id_proveedor int auto_increment not null,
 nombre_proveedor varchar(60) not null,
 telefono_proveedor int not null,
 direccion varchar(100) not null,
 email_proveedor varchar(100) not null,
 primary key PK_id_proveedor(id_proveedor)
);

create table Empleados(
id_empleado int auto_increment not null,
 nombre_empleado varchar(60) not null,
 apellido_empleado varchar(60) not null,
 puesto_empleado varchar(20) null,
 email_empleado varchar(100) not null,
 primary key PK_id_empleado(id_empleado)
);

create table Repuestos(
id_repuesto int auto_increment not null,
 nombre_repuesto varchar(60) not null,
 categoria_repuesto varchar(60) not null,
 precio_compra double not null,
 precio_venta double not null,
 id_proveedor int not null,
 primary key PK_id_repuesto(id_repuesto),
 constraint FK_repuesto_proveedor foreign key (id_proveedor)
references proveedores(id_proveedor) on delete cascade
);

create table Ventas(
id_venta int auto_increment not null,
 fecha_venta date not null,
 cantidad int not null,
 total double not null,
id_empleado int not null,
 id_repuesto int not null,
 primary key PK_id_venta(id_venta),
 constraint FK_ventas_empleado foreign key (id_empleado)
references Empleados(id_empleado) on delete cascade,
 constraint FK_ventas_repuestos foreign key (id_repuesto)
references Repuestos(id_repuesto) on delete cascade
);

-- =====================================================
-- procedimientos almacenados para la entidad proveedores
-- =====================================================

delimiter //

-- agregar proveedor
create procedure sp_agregar_proveedor(
    in p_nombre varchar(60),
    in p_telefono int,
    in p_direccion varchar(100),
    in p_email varchar(100)
)
begin
    insert into proveedores(nombre_proveedor, telefono_proveedor, direccion, email_proveedor)
    values (p_nombre, p_telefono, p_direccion, p_email);
end //

-- editar proveedor
create procedure sp_editar_proveedor(
    in p_id int,
    in p_nombre varchar(60),
    in p_telefono int,
    in p_direccion varchar(100),
    in p_email varchar(100)
)
begin
    update proveedores
    set nombre_proveedor = p_nombre,
        telefono_proveedor = p_telefono,
        direccion = p_direccion,
        email_proveedor = p_email
    where id_proveedor = p_id;
end //

-- eliminar proveedor
create procedure sp_eliminar_proveedor(in p_id int)
begin
    delete from proveedores
    where id_proveedor = p_id;
end //

-- listar proveedores
create procedure sp_listar_proveedores()
begin
    select * from proveedores;
end //

delimiter ;

-- =====================================================
-- procedimientos almacenados para la entidad empleados
-- =====================================================

delimiter //

-- agregar empleado
create procedure sp_agregar_empleado(
    in p_nombre varchar(60),
    in p_apellido varchar(60),
    in p_puesto varchar(20),
    in p_email varchar(100)
)
begin
    insert into empleados(nombre_empleado, apellido_empleado, puesto_empleado, email_empleado)
    values (p_nombre, p_apellido, p_puesto, p_email);
end //

-- editar empleado
create procedure sp_editar_empleado(
    in p_id int,
    in p_nombre varchar(60),
    in p_apellido varchar(60),
    in p_puesto varchar(20),
    in p_email varchar(100)
)
begin
    update empleados
    set nombre_empleado = p_nombre,
        apellido_empleado = p_apellido,
        puesto_empleado = p_puesto,
        email_empleado = p_email
    where id_empleado = p_id;
end //

-- eliminar empleado
create procedure sp_eliminar_empleado(in p_id int)
begin
    delete from empleados
    where id_empleado = p_id;
end //

-- listar empleados
create procedure sp_listar_empleados()
begin
    select * from empleados;
end //

delimiter ;

-- =====================================================
-- procedimientos almacenados para la entidad repuestos
-- =====================================================

delimiter //

-- agregar repuesto
create procedure sp_agregar_repuesto(
    in p_nombre varchar(60),
    in p_categoria varchar(60),
    in p_precio_compra double,
    in p_precio_venta double,
    in p_id_proveedor int
)
begin
    insert into repuestos(nombre_repuesto, categoria_repuesto, precio_compra, precio_venta, id_proveedor)
    values (p_nombre, p_categoria, p_precio_compra, p_precio_venta, p_id_proveedor);
end //

-- editar repuesto
create procedure sp_editar_repuesto(
    in p_id int,
    in p_nombre varchar(60),
    in p_categoria varchar(60),
    in p_precio_compra double,
    in p_precio_venta double,
    in p_id_proveedor int
)
begin
    update repuestos
    set nombre_repuesto = p_nombre,
        categoria_repuesto = p_categoria,
        precio_compra = p_precio_compra,
        precio_venta = p_precio_venta,
        id_proveedor = p_id_proveedor
    where id_repuesto = p_id;
end //

-- eliminar repuesto
create procedure sp_eliminar_repuesto(in p_id int)
begin
    delete from repuestos
    where id_repuesto = p_id;
end //

-- listar repuestos
create procedure sp_listar_repuestos()
begin
    select r.*, p.nombre_proveedor
    from repuestos r
    inner join proveedores p
        on r.id_proveedor = p.id_proveedor;
end //

delimiter ;

-- =====================================================
-- procedimientos almacenados para la entidad ventas
-- =====================================================

delimiter //

-- agregar venta
create procedure sp_agregar_venta(
    in p_fecha date,
    in p_cantidad int,
    in p_total double,
    in p_id_empleado int,
    in p_id_repuesto int
)
begin
    insert into ventas(fecha_venta, cantidad, total, id_empleado, id_repuesto)
    values (p_fecha, p_cantidad, p_total, p_id_empleado, p_id_repuesto);
end //

-- editar venta
create procedure sp_editar_venta(
    in p_id int,
    in p_fecha date,
    in p_cantidad int,
    in p_total double,
    in p_id_empleado int,
    in p_id_repuesto int
)
begin
    update ventas
    set fecha_venta = p_fecha,
        cantidad = p_cantidad,
        total = p_total,
        id_empleado = p_id_empleado,
        id_repuesto = p_id_repuesto
    where id_venta = p_id;
end //

-- eliminar venta
create procedure sp_eliminar_venta(in p_id int)
begin
    delete from ventas
    where id_venta = p_id;
end //

-- listar ventas
create procedure sp_listar_ventas()
begin
    select v.*, e.nombre_empleado, r.nombre_repuesto
    from ventas v
    inner join empleados e
        on v.id_empleado = e.id_empleado
    inner join repuestos r
        on v.id_repuesto = r.id_repuesto;
end //

delimiter ;

-- =====================================
-- carga de datos iniciales (seed data)
-- =====================================

start transaction;

-- =========================
-- proveedores
-- =========================
call sp_agregar_proveedor('autopartes del norte', 5551001, 'zona 1 ciudad', 'contacto@gmail.com');
call sp_agregar_proveedor('repuestos el volante', 5551002, 'zona 5 ciudad', 'ventas@gmail.com');
call sp_agregar_proveedor('importadora la ruta', 5551003, 'zona 9 ciudad', 'info@hotmail.com');
call sp_agregar_proveedor('repuestos san jose', 5551004, 'zona 10 ciudad', 'sanjose@yahoo.com');
call sp_agregar_proveedor('auto piezas express', 5551005, 'zona 3 ciudad', 'express@yahoo.com');
call sp_agregar_proveedor('distribuidora motor', 5551006, 'zona 7 ciudad', 'motor@dgmail.com');
call sp_agregar_proveedor('repuestos premium', 5551007, 'zona 14 ciudad', 'premium@gmail.com');
call sp_agregar_proveedor('auto partes central', 5551008, 'zona 12 ciudad', 'central@hotmail.com');
call sp_agregar_proveedor('repuestos el camino', 5551009, 'zona 8 ciudad', 'camino@rhotmail.com');
call sp_agregar_proveedor('importadora velocidad', 5551010, 'zona 6 ciudad', 'velocidad@gmail.com');

-- =========================
-- empleados
-- =========================
call sp_agregar_empleado('juan', 'perez', 'vendedor', 'juan.perez@gmail.com');
call sp_agregar_empleado('maria', 'lopez', 'cajera', 'maria.lopez@gmail.com');
call sp_agregar_empleado('carlos', 'ramirez', 'bodega', 'carlos.ramirez@gmail.com');
call sp_agregar_empleado('ana', 'gomez', 'vendedora', 'ana.gomez@hotmail.com');
call sp_agregar_empleado('luis', 'martinez', 'supervisor', 'luis.martinez@hotmail.com');
call sp_agregar_empleado('sofia', 'hernandez', 'cajera', 'sofia.hernandez@outlock.com');
call sp_agregar_empleado('pedro', 'castillo', 'bodega', 'pedro.castillo@outlock.com');
call sp_agregar_empleado('laura', 'morales', 'vendedora', 'laura.morales@gmail.com');
call sp_agregar_empleado('jose', 'ruiz', 'contador', 'jose.ruiz@hotmail.com');
call sp_agregar_empleado('andrea', 'flores', 'administrador', 'andrea.flores@gmail.com');

-- =========================
-- repuestos
-- =========================
call sp_agregar_repuesto('filtro de aceite', 'motor', 25.00, 40.00, 1);
call sp_agregar_repuesto('bujia estandar', 'encendido', 15.00, 30.00, 2);
call sp_agregar_repuesto('pastillas de freno', 'frenos', 80.00, 120.00, 3);
call sp_agregar_repuesto('bateria 12v', 'electrico', 350.00, 480.00, 4);
call sp_agregar_repuesto('correa de tiempo', 'motor', 90.00, 150.00, 5);
call sp_agregar_repuesto('amortiguador delantero', 'suspension', 200.00, 320.00, 6);
call sp_agregar_repuesto('radiador', 'enfriamiento', 400.00, 600.00, 7);
call sp_agregar_repuesto('faro delantero', 'carroceria', 180.00, 300.00, 8);
call sp_agregar_repuesto('alternador', 'electrico', 450.00, 650.00, 9);
call sp_agregar_repuesto('sensor de oxigeno', 'electronica', 220.00, 350.00, 10);

-- =========================
-- ventas
-- =========================
call sp_agregar_venta('2025-01-10', 2, 80.00, 1, 1);
call sp_agregar_venta('2025-01-11', 4, 120.00, 2, 2);
call sp_agregar_venta('2025-01-12', 1, 120.00, 3, 3);
call sp_agregar_venta('2025-01-13', 1, 480.00, 4, 4);
call sp_agregar_venta('2025-01-14', 2, 300.00, 5, 5);
call sp_agregar_venta('2025-01-15', 1, 320.00, 6, 6);
call sp_agregar_venta('2025-01-16', 1, 600.00, 7, 7);
call sp_agregar_venta('2025-01-17', 2, 600.00, 8, 8);
call sp_agregar_venta('2025-01-18', 1, 650.00, 9, 9);
call sp_agregar_venta('2025-01-19', 1, 350.00, 10, 10);

commit;

select * from Empleados
