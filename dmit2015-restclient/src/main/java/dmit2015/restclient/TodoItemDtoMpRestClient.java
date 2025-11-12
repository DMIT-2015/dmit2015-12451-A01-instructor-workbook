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
    Response create(TodoItemDto newTodoItemDto);

    @GET
    List<TodoItemDto> findAll();

    @GET
    @Path("/{id}")
    Optional<TodoItemDto> findById(@PathParam("id") Long id);

    @PUT
    @Path("/{id}")
    TodoItemDto update(@PathParam("id") Long id, TodoItemDto updatedTodoItemDto);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") Long id);
}
