package io.rowtech.api.Mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import javax.inject.Inject;
import java.lang.reflect.Type;

public final class MapperImpl implements Mapper {
    private final ModelMapper modelMapper;

    @Inject
    public MapperImpl(Provider<?> provider){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setProvider(provider);
    }
    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    @Override
    public <D> D map(Object source, Class<D> destinationType, String typeMapName) {
        return modelMapper.map(source, destinationType, typeMapName);
    }

    @Override
    public void map(Object source, Object destination) {
        modelMapper.map(source, destination);
    }

    @Override
    public void map(Object source, Object destination, String typeMapName) {
        modelMapper.map(source, destination, typeMapName);
    }

    @Override
    public <D> D map(Object source, Type destinationType) {
        return modelMapper.map(source, destinationType);
    }

    @Override
    public <D> D map(Object source, Type destinationType, String typeMapName) {
        return modelMapper.map(source, destinationType, typeMapName);
    }
}
