package com.tuan.authservice.model.role;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role,String> {
    @Override
    public String convertToDatabaseColumn(Role attribute) {
        return attribute.toString();
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        return Role.valueOf(dbData);
    }

}
