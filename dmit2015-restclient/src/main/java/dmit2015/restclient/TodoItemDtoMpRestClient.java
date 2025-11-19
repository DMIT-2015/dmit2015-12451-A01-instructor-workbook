package dmit2015.restclient;

import dmit2015.model.TodoItemDto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;
import java.util.Optional;

@RequestScoped
@RegisterRestClient(baseUri = "http://localhost:8090/restapi/TodoItemDtos")
public interface TodoItemDtoMpRestClient {

    @POST
    Response create(@HeaderParam("Authorization") String authorizationHeader,TodoItemDto newTodoItemDto);

    @GET
    List<TodoItemDto> findAll(@HeaderParam("Authorization") String authorizationHeader);

    @GET
    @Path("/{id}")
    Optional<TodoItemDto> findById(@HeaderParam("Authorization") String authorizationHeader,@PathParam("id") Long id);

    @PUT
    @Path("/{id}")
    TodoItemDto update(@HeaderParam("Authorization") String authorizationHeader,@PathParam("id") Long id, TodoItemDto updatedTodoItemDto);

    @DELETE
    @Path("/{id}")
    void delete(@HeaderParam("Authorization") String authorizationHeader,@PathParam("id") Long id);
}
