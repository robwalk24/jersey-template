package io.rowtech.api.exception.mapper;

import io.rowtech.api.model.ErrorModel;

import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Provider
@Singleton
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(buildErrorModels(exception.getConstraintViolations()))
                .build();
    }

    private List<ErrorModel> buildErrorModels(Set<ConstraintViolation<?>> violations){
        List<ErrorModel> models = new ArrayList<>();

        violations
                .stream()
                .forEach((v) -> models.add(new ErrorModel(String.format("Invalid value for %s. %s",
                        v.getPropertyPath(), v.getMessage()))));

        return models;
    }
}
