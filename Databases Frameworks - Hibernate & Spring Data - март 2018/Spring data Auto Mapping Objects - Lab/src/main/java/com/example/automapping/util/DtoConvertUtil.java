package com.example.automapping.util;

import org.modelmapper.ModelMapper;

public class DtoConvertUtil {

    private static ModelMapper mapper = new ModelMapper();

    public DtoConvertUtil() {
    }

    public static <S, D> D convert(S source, Class<D> destinationClass) {
        return mapper.map(source, destinationClass);
    }



}
