package factory.service;

import com.google.gson.Gson;
import factory.logger.AppLogger;
import factory.model.Product;
import factory.properties.ConfigProperties;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class ProductService {


    private ConfigProperties prop = new ConfigProperties();
    private Gson gson = new Gson();

    public List<Product> getAll() {

        HttpURLConnection connection = null;

        try {
            URL url = new URL(prop.getProductsURL());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonContent = new StringBuffer();

            String line = "";
            while ((line = in.readLine()) != null) {
                jsonContent.append(line);
            }

            Product[] products = gson.fromJson(jsonContent.toString(), Product[].class);
            return new ArrayList<>(Arrays.asList(products));

        } catch (IOException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(String name, Product product) {
        HttpURLConnection connection = null;
        try {
            String encodedName = URLEncoder.encode(name, "UTF-8");
            URL url = new URL(prop.getProductsURL() + "/" + encodedName);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream outputStream = connection.getOutputStream();
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {

                writer.write(gson.toJson(product));
                writer.flush();

                int responseCode = connection.getResponseCode();
            }
        } catch (IOException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
            throw new RuntimeException("Failed to send request", e);
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }


    public void deleteProduct(String name) {
        HttpURLConnection connection = null;
        try {
            String encodedName = URLEncoder.encode(name, "UTF-8");
            System.out.println(prop.getProductsURL() + "/" + encodedName);
            URL url = new URL(prop.getProductsURL() + "/" + encodedName);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setDoOutput(true);

            try (OutputStream out = connection.getOutputStream()) {
                out.write("".getBytes(StandardCharsets.UTF_8));
                out.flush();
            }
            int responseCode = connection.getResponseCode();

        } catch (IOException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
            throw new RuntimeException("Failed to send request", e);
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }

}
