import org.example.GestorDB;

import java.util.Scanner;

/* En nuestra empresa northwind, tenemos que gestionar una base de datos, cuyo objetivo
principal es la gestión de pedidos, empleados, productos, etc.
Se pide la realización de una aplicación que:
• Crea una base de datos llamada almacén con las siguientes tablas y campos
o Productos: id (pk), nombre, descripción, cantidad, precio.
o Empleados: id (pk), nombre, apellidos, correo
o Pedidos: id (pk), id_producto (fk), descripción, precio_total
o Productos_Fav: id (pk), id_producto (fk)
• Agregar todos los productos que están ubicados en el siguiente JSON dentro de
la tabla productos: https://dummyjson.com/products
• Agregar una serie empleados y pedidos mediante statement. Tener en cuenta que
los pedidos tienen una fk sobre la tabla productos
• Muestra por consola mediante la ejecución de querys – statement:
o Todos los empleados.
o Todos los productos
o Todos los pedidos
• Muestra por consola todos los productos de la base de datos que tengan un precio
inferior a 600€
Inserta en la tabla productos_fav aquellos productos que tengan un valor superior a
1000€ */
public class Main extends GestorDB {
    public static void main(String[] args) {


        GestorDB gestorDB = new GestorDB();
        //gestorDB.getConexion();
        // gestorDB.agregarEmpleado();
        // gestorDB.agregarPedidos();
        // gestorDB.mostrarEmpleados();
        // gestorDB.mostrarProductos();
        //gestorDB.mostrarPedidos();
        //gestorDB.mostarPrecioInferiora();
        // gestorDB.insertarFavoritos();

        Scanner sc = new Scanner(System.in);

        int eleccion = 0;

        while (eleccion != 8) {
            System.out.println("Elige una opcion: ");
            System.out.println("1. Listar productos. ");
            System.out.println("2. Agregar empleados. ");
            System.out.println("3. Agregar pedidos. ");
            System.out.println("4. Mostrar empleados. ");
            System.out.println("5. Mostrar pedidos. ");
            System.out.println("6. Mostrar productos con precio inferior a 600€. ");
            System.out.println("7. Insertar los productos favoritos que tengan un valor superior a 1000€. ");
            System.out.println("8. Salir ");

            eleccion = sc.nextInt();

            switch (eleccion) {
                case 1:
                    System.out.println("Listar productos. ");
                    gestorDB.mostrarProductos();
                    break;
                case 2:
                    System.out.println("Agregar empleados. ");
                    gestorDB.agregarEmpleado();
                    break;
                case 3:
                    System.out.println("Agregar pedidos. ");
                    gestorDB.agregarPedidos();
                    break;
                case 4:
                    System.out.println("Mostrar empleados. ");
                    gestorDB.mostrarEmpleados();
                    break;
                case 5:
                    System.out.println("Mostrar pedidos. ");
                    gestorDB.mostrarPedidos();
                    break;
                case 6:
                    System.out.println("Mostrar productos con precio inferior a 600€. ");
                    gestorDB.mostarPrecioInferiora();
                    break;
                case 7:
                    System.out.println("Insertar los productos favoritos que tengan un valor superior a 1000€. ");
                    gestorDB.insertarFavoritos();
                    break;
                case 8:
                    System.out.println("Saliendo. ");

                    break;
                default:
                    System.out.println("Opcion no valida. ");
            }

        }



    }
}




