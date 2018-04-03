package com.example.automapping.util;

import org.modelmapper.ModelMapper;

public class DtoConvertUtil {

    private ModelMapper mapper;

    public DtoConvertUtil() {
        mapper = new ModelMapper();
    }

    public <S, D> D convert(S source, Class<D> destinationClass) {
        return this.mapper.map(source, destinationClass);
    }

}
