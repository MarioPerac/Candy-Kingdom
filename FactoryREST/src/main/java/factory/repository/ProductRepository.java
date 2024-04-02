package factory.repository;

import factory.model.Product;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.naming.ldap.HasControls;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {

    private static final String instanceName = "DB";
    JedisPool pool = new JedisPool("localhost", 6379);

    public void add(Product product) {
        try (Jedis jedis = pool.getResource()) {
            jedis.hmset(instanceName + ":products:map:" + product.getName(), product.toMap());
        }
    }

    public void addList(List<Product> list) {
        try (Jedis jedis = pool.getResource()) {
            for (Product product : list) {
                jedis.hmset(instanceName + ":products:map:" + product.getName(), product.toMap());
            }
        }
    }

    public List<Product> getAll() {
        try (Jedis jedis = pool.getResource()) {
            List<Product> products = new ArrayList<>();
            for (String key : jedis.keys(instanceName + ":products:map:*")) {
                Map<String, String> productAttributes = jedis.hgetAll(key);
                String name = productAttributes.get("name");
                Double price = Double.parseDouble(productAttributes.get("price"));
                int quantity = Integer.parseInt(productAttributes.get("quantity"));
                products.add(new Product(name, price, quantity));
            }
            return products;
        }
    }
}
