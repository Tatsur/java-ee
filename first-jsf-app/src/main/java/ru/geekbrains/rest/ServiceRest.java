package ru.geekbrains.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public interface ServiceRest<T> {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<T> findAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(T product);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(T product);


}
