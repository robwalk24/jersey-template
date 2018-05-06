package io.rowtech.api.Mapping;

import java.lang.reflect.Type;

public interface Mapper {
    <D> D map(Object source, Class<D> destinationType);

    <D> D map(Object source, Class<D> destinationType, String typeMapName);

    void map(Object source, Object destination);

    void map(Object source, Object destination, String typeMapName);

    <D> D map(Object source, Type destinationType);

    <D> D map(Object source, Type destinationType, String typeMapName);
}
