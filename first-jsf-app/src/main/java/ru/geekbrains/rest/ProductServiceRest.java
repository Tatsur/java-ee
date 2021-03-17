package ru.geekbrains.rest;

import ru.geekbrains.service.ProductRepr;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/product")
public interface ProductServiceRest extends ServiceRest<ProductRepr> {

    @GET
    @Path("/category_{category_id}")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepr> findByCategoryId(@PathParam("category_id") Long id);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductRepr findById(@PathParam("id") Long id);

    @DELETE
    @Path("/{id}")
    void deleteById(@PathParam("id") Long id);

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepr> findByName(@PathParam("name") String name);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();
}
