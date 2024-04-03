package factory.service;

import com.google.gson.Gson;
import factory.model.OrderedProduct;
import factory.model.Product;
import factory.properties.ConfigProperties;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            throw new RuntimeException(e);
        }

    }


    public void decreaseProductsQuantity(List<OrderedProduct> orderedProducts) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(prop.getProductsURL() + "/decrease");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connection.getOutputStream())));
            out.write(gson.toJson(orderedProducts));
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
