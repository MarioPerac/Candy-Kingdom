package factory.controller;

import factory.model.OrderedProduct;
import factory.model.Product;
import factory.service.ProductService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Path("/products")
public class ProductController {

    ProductService productService = new ProductService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllAvailable() {
        return productService.getAllAvailable();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) {
        productService.add(product);
        return Response.status(200).build();
    }

    @POST
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(List<Product> products) {
        productService.add(products);
        return Response.status(200).build();
    }

    @DELETE
    @Path("/{name}")
    public Response deleteProduct(@PathParam("name") String name) {
        String decodedName = null;
        try {
            decodedName = URLDecoder.decode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        if (productService.delete(decodedName))
            return Response.status(204).build();
        else
            return Response.status(404).build();
    }

    @PUT
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("name") String name, Product product) {
        String decodedName = null;
        try {
            decodedName = URLDecoder.decode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        productService.update(decodedName, product);
        return Response.status(200).build();
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
