package factory.service;

import factory.logger.AppLogger;
import factory.model.OrderedProduct;
import factory.properties.ConfigProperties;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;

import com.google.gson.Gson;

public class ProductService {

    private ConfigProperties prop = new ConfigProperties();
    private Gson gson = new Gson();

    public void increaseProductsQuantity(List<OrderedProduct> orderedProducts) {
        try {
            URL url = new URL(prop.getProductsURL() + "/increase");
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
            throw new RuntimeException("Failed to send request", e);
        }
    }
}
