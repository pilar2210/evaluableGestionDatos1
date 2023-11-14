package org.example;

public interface bbdd {
    String nombreBbdd = "almacen";

    //tabla productos

    String nombreTablaProductos = "productos";
    String id_productos = "id";
    String nombreDelProducto = "nombre";

    String descipcion_productos = "descripción";

    String precio_productos = "precio";

    String cantidad_productos = "cantidad";

    //tabla pedidos

    String nombreTablaPedidos = "pedidos";

    String id_pedidos = "id";

    String id_producto2 = "id_producto";

    String descripcion_pedidos = "descripción";

    String precio_pedidos_total = "precio_total";

    //tabla empleados

    String nombreTablaEmpleados = "empleados";

    String id_empleados = "id";

    String nombre_empleados = "nombre";

    String apellidos_empleados = "apellidos";

    String correo_empleados = "correo";

    //tabla productos favoritos

    String nombreTablaProductosFavoritos = "productos_favoritos";

    String id_productosFavoritos = "id";

    String id_productos2 = "id_producto";




}
