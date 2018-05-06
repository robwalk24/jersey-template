package io.rowtech.api.exception.mapper;

import io.rowtech.api.model.ErrorModel;

import javax.inject.Singleton;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException exception) {
        return Response
                .status(exception.getResponse().getStatus())
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErrorModel(exception.getMessage()))
                .build();
    }
}
