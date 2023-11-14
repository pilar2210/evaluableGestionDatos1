package org.example;


import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.Scanner;

public class GestorDB implements bbdd {
    private Connection connection;

    public GestorDB() {
        getConexion();

    }

    public void getConexion() {
        String NombreUsuario = "pilarBD";
        String Password = "pilar22";
        String BaseDeDatos = "almacen";
        String host = "127.0.0.1:3306";
        String url = "jdbc:mariadb://" + host + "/" + BaseDeDatos;

        try {
            Class.forName("org.mariadb.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(url, NombreUsuario, Password);
            System.out.println("Conectado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void agregarProductos() {


        try {
            //esto lo insertamos desde el JSON
            URL url2 = new URL("https://dummyjson.com/products");
            HttpURLConnection conexion = (HttpURLConnection) url2.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            StringBuilder buffer = new StringBuilder();
            String line = reader.readLine();

            JSONObject obj = new JSONObject(line);
            JSONArray array = obj.getJSONArray("products");
            String query = "INSERT INTO " + bbdd.nombreTablaProductos + " (" + bbdd.id_productos + "," + bbdd.nombreDelProducto + "," + bbdd.descipcion_productos + "," + bbdd.precio_productos + "," + bbdd.cantidad_productos + ") VALUES (?, ?, ?, ?,?)";
            PreparedStatement pilar = null;

            try {
                pilar = connection.prepareStatement(String.format(query));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            for (int i = 0; i < array.length(); i++) {
                JSONObject producto = array.getJSONObject(i);
                int id = producto.getInt("id");
                String title = producto.getString("title");
                String description = producto.getString("description");
                int price = producto.getInt("price");
                int cantidad = producto.getInt("stock");
                try {

                    pilar.setInt(1, id);
                    pilar.setString(2, title);
                    pilar.setString(3, description);
                    pilar.setInt(4, price);
                    pilar.setInt(5, cantidad);
                    pilar.execute();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        } catch (IOException e) {
            System.out.println("Error de lectura");
        }
    }


    public void agregarEmpleado() {
        //esto lo insertamos desde el sacanner

        Scanner sc = new Scanner(System.in);

        String query = "INSERT INTO " + bbdd.nombreTablaEmpleados + " (" + bbdd.nombre_empleados + "," + bbdd.apellidos_empleados + "," + bbdd.correo_empleados + ") VALUES (?, ?, ?)";

        PreparedStatement pilar = null;
        System.out.println("Nombre");
        String nombre = sc.nextLine();
        System.out.println("Apellidos");
        String apellidos = sc.nextLine();
        System.out.println("Correo");
        String correo = sc.nextLine();

        try {
            pilar = connection.prepareStatement(String.format(query));
            pilar.setString(1, nombre);
            pilar.setString(2, apellidos);
            pilar.setString(3, correo);
            pilar.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String encontrarDescripcion(int id) {
        String query = "SELECT " + bbdd.descipcion_productos + " FROM " + bbdd.nombreTablaProductos + " WHERE " + bbdd.id_productos + "=?";
        PreparedStatement pilar = null;
        try {
            pilar = connection.prepareStatement(String.format(query));
            pilar.setString(1, String.valueOf(id));
            ResultSet rs = pilar.executeQuery();
            rs.next();
            return rs.getString(bbdd.descipcion_productos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int encontarPrecio(int id) {
        String query = "SELECT " + bbdd.precio_productos + " FROM " + bbdd.nombreTablaProductos + " WHERE " + bbdd.id_productos + "=?";
        PreparedStatement pilar = null;
        try {
            pilar = connection.prepareStatement(String.format(query));
            pilar.setString(1, String.valueOf(id));
            ResultSet rs = pilar.executeQuery();
            rs.next();
            return rs.getInt(bbdd.precio_productos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void agregarPedidos() {

        Scanner sc = new Scanner(System.in);
        String query = "INSERT INTO " + bbdd.nombreTablaPedidos + " (" + bbdd.id_producto2 + ", " + bbdd.descripcion_pedidos + ", " + bbdd.precio_pedidos_total + ") VALUES (?, ?, ?)";
        PreparedStatement pilar = null;
        System.out.println("Id producto");
        int id = sc.nextInt();
        System.out.println("¿cuantos pedidos?");
        int cantidad = sc.nextInt();
        int total = encontarPrecio(id) * cantidad;


        try {
            pilar = connection.prepareStatement(String.format(query));
            pilar.setInt(1, id);
            pilar.setString(2, encontrarDescripcion(id));
            pilar.setInt(3, total);
            pilar.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarEmpleados() {
        String query = "SELECT * FROM " + bbdd.nombreTablaEmpleados;
        PreparedStatement pilar = null;
        try {
            pilar = connection.prepareStatement(String.format(query));
            ResultSet rs = pilar.executeQuery();
            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString(bbdd.nombre_empleados));
                System.out.println("Apellidos: " + rs.getString(bbdd.apellidos_empleados));
                System.out.println("Correo: " + rs.getString(bbdd.correo_empleados));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void mostrarProductos() {
        String query = "SELECT * FROM " + bbdd.nombreTablaProductos;
        PreparedStatement pilar = null;
        try {
            pilar = connection.prepareStatement(String.format(query));
            ResultSet rs = pilar.executeQuery();
            while (rs.next()) {
                System.out.println("Id: " + rs.getString(bbdd.id_productos));
                System.out.println("Title: " + rs.getString(bbdd.nombreDelProducto));
                System.out.println("Description: " + rs.getString(bbdd.descipcion_productos));
                System.out.println("Price: " + rs.getString(bbdd.precio_productos));
                System.out.println("Stock: " + rs.getString(bbdd.cantidad_productos));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        public void mostrarPedidos() {
            String query = "SELECT * FROM " + bbdd.nombreTablaPedidos;
            PreparedStatement pilar = null;
            try {
                pilar = connection.prepareStatement(String.format(query));
                ResultSet rs = pilar.executeQuery();
                while (rs.next()) {
                    System.out.println("Id: " + rs.getString(bbdd.id_producto2));
                    System.out.println("Description: " + rs.getString(bbdd.descripcion_pedidos));
                    System.out.println("Price: " + rs.getString(bbdd.precio_pedidos_total));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        public void mostarPrecioInferiora() {
            String query = "SELECT * FROM " + bbdd.nombreTablaProductos + " WHERE " + bbdd.precio_productos + "< 600";
            PreparedStatement pilar = null;
            try {
                pilar = connection.prepareStatement(String.format(query));
                ResultSet rs = pilar.executeQuery();
                System.out.println("Productos con un precio inferior a 600: ");
                while (rs.next()) {
                    System.out.println("Id: " + rs.getString(bbdd.id_productos));
                    System.out.println("Title: " + rs.getString(bbdd.nombreDelProducto));
                    System.out.println("Description: " + rs.getString(bbdd.descipcion_productos));
                    System.out.println("Price: " + rs.getString(bbdd.precio_productos));
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    public void insertarFavoritos() {
        String query1 = "SELECT * FROM " + bbdd.nombreTablaProductosFavoritos + " WHERE " + bbdd.id_productosFavoritos + "> 1000";
        String query2 = "INSERT INTO " + bbdd.nombreTablaProductosFavoritos + " (" + bbdd.id_productosFavoritos + ", " + bbdd.id_productos2 + ") VALUES (?, ?)";
        try (PreparedStatement pilar = connection.prepareStatement(query2)) {


            pilar.setInt(1, 1001);
            pilar.setInt(2, 1002);
            pilar.executeUpdate();

            try (ResultSet rs = connection.createStatement().executeQuery(query1)) {
                System.out.println("Productos favoritos: ");
                while (rs.next()) {
                    System.out.println("Id productos favoritos: " + rs.getString(bbdd.id_productosFavoritos));
                    System.out.println("Id productos: " + rs.getString(bbdd.id_productos2));
                }
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}











