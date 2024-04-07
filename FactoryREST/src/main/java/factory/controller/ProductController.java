package factory.controller;

import factory.model.OrderedProduct;
import factory.model.Product;
import factory.service.ProductService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.print.attribute.standard.Media;
import java.util.List;

@Path("/products")
public class ProductController {

    ProductService productService = new ProductService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllAvailable() {
        return productService.getAllAvailable();
    }

    @PUT
    @Path("/decrease")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response decreaseProductsQuantity(List<OrderedProduct> orderedProducts) {
        productService.decreaseProductQuantity(orderedProducts);
        return Response.status(200).build();
    }

    @PUT
    @Path("/increase")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response increaseProductsQuantity(List<OrderedProduct> orderedProducts) {
        productService.increaseProductQuantity(orderedProducts);
        return Response.status(200).build();
    }
}
