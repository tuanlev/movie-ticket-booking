package com.tuan.movieservice.model.moviestatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MovieStatusConverter implements AttributeConverter<MovieStatus,String> {
    @Override
    public String convertToDatabaseColumn(MovieStatus attribute) {
        return attribute.toString();
    }

    @Override
    public MovieStatus convertToEntityAttribute(String dbData) {
        return MovieStatus.valueOf(dbData);
    }

}
