package io.rowtech.api.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonProvider implements ContextResolver<ObjectMapper> {
    private final ObjectMapper objectMapper;

    public JsonProvider(){
        objectMapper = create();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }

    private static ObjectMapper create(){
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JodaModule());

        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(DeserializationFeature.WRAP_EXCEPTIONS);
        mapper.disable(SerializationFeature.WRAP_EXCEPTIONS);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        return mapper;
    }
}
