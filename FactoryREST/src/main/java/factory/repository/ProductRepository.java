package factory.repository;

import factory.model.OrderedProduct;
import factory.model.Product;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductRepository {

    private static final String instanceName = "DB";
    private JedisPool pool = new JedisPool("localhost", 6379);

    public void add(Product product) {
        try (Jedis jedis = pool.getResource()) {
            jedis.hmset(instanceName + ":products:map:" + product.getName(), product.toMap());
        }
    }

    public void addList(List<Product> list) {
        try (Jedis jedis = pool.getResource()) {
            for (Product product : list) {
                String key = instanceName + ":products:map:" + product.getName();
                if (!jedis.exists(key))
                    jedis.hmset(key, product.toMap());
                else {
                    int newQuantity = Integer.parseInt(jedis.hget(key, "quantity")) + product.getQuantity();
                    jedis.hset(key, "quantity", String.valueOf(newQuantity));
                }
            }
        }
    }

    public boolean delete(String name) {
        try (Jedis jedis = pool.getResource()) {
            long result = jedis.del(instanceName + ":products:map:" + name);
            return result == 1;
        }
    }

    public List<Product> getAllAvailable() {
        try (Jedis jedis = pool.getResource()) {
            List<Product> products = new ArrayList<>();
            for (String key : jedis.keys(instanceName + ":products:map:*")) {
                Map<String, String> productAttributes = jedis.hgetAll(key);
                String name = productAttributes.get("name");
                Double price = Double.parseDouble(productAttributes.get("price"));
                int quantity = Integer.parseInt(productAttributes.get("quantity"));
                if (quantity > 0)
                    products.add(new Product(name, price, quantity));
            }
            return products;
        }
    }

    public void decreaseProductQuantity(List<OrderedProduct> orderedProducts) {

        for (OrderedProduct op : orderedProducts) {
            try (Jedis jedis = pool.getResource()) {
                int quantity = Integer.parseInt(jedis.hget(instanceName + ":products:map:" + op.getName(), "quantity"));
                int newQuantity = quantity - op.getSelectedQuantity();
                jedis.hset(instanceName + ":products:map:" + op.getName(), "quantity", String.valueOf(newQuantity));
            }

        }
    }

    public void increaseProductQuantity(List<OrderedProduct> orderedProducts) {
        for (OrderedProduct op : orderedProducts) {
            try (Jedis jedis = pool.getResource()) {
                int quantity = Integer.parseInt(jedis.hget(instanceName + ":products:map:" + op.getName(), "quantity"));
                int newQuantity = quantity + op.getSelectedQuantity();
                jedis.hset(instanceName + ":products:map:" + op.getName(), "quantity", String.valueOf(newQuantity));
            }

        }
    }

    public void update(String name, Product product) {
        try (Jedis jedis = pool.getResource()) {
            if (name.equals(product.getName())) {
                jedis.hmset(instanceName + ":products:map:" + name, product.toMap());
            } else {
                delete(name);
                add(product);
            }
        }
    }
}
