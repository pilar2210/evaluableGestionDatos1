package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class LectorJson {
    public static void main(String[] args) {


        try {
            URL url = new URL("https://dummyjson.com/products");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder buffer = new StringBuilder();
            String line = reader.readLine();

            JSONObject obj = new JSONObject(line);
            JSONArray array = obj.getJSONArray("products");
            for (int i = 0; i < array.length(); i++) {
                JSONObject producto = array.getJSONObject(i);
                int id = producto.getInt("id");
                String title = producto.getString("title");
                String description = producto.getString("description");
                int price = producto.getInt("price");

                System.out.println("id: "+ id );
                System.out.println("titulo: "+title);
                System.out.println("descripcion: "+description);
                System.out.println("precio: "+price);


            }

        } catch (IOException e) {
            System.out.println("Error de lectura");
        }


   }
  }