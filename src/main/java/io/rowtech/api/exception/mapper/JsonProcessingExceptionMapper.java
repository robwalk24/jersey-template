package io.rowtech.api.exception.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.rowtech.api.model.ErrorModel;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class JsonProcessingExceptionMapper implements ExceptionMapper<JsonProcessingException> {
    @Override
    public Response toResponse(JsonProcessingException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErrorModel(exception.getMessage()))
                .build();
    }
}
