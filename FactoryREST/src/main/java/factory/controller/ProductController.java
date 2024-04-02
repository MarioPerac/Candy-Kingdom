package factory.controller;

import factory.model.Product;
import factory.service.ProductService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Map;

@Path("/products")
public class ProductController {

    ProductService productService = new ProductService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAll() {
        return productService.getAll();
    }
}
