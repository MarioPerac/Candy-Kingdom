package factory.service;

import com.google.gson.Gson;
import factory.logger.AppLogger;
import factory.model.OrderedProduct;
import factory.model.Product;
import factory.properties.ConfigProperties;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class ProductService {


    private ConfigProperties prop = new ConfigProperties();
    private Gson gson = new Gson();

    public List<Product> getAll() {

        HttpURLConnection connection = null;

        ArrayList<Product> productsList = null;
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
            productsList = new ArrayList<>(Arrays.asList(products));

        } catch (IOException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
        }
        return productsList;
    }


    public void decreaseProductsQuantity(List<OrderedProduct> orderedProducts) {
        try {
            URL url = new URL(prop.getProductsURL() + "/decrease");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream outputStream = connection.getOutputStream();
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {

                writer.write(gson.toJson(orderedProducts));
                writer.flush();

                int responseCode = connection.getResponseCode();
            } finally {
                connection.disconnect();
            }
        } catch (IOException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
        }
    }
}
