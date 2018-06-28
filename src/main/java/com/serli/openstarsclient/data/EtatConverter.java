package com.serli.openstarsclient.data;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EtatConverter implements AttributeConverter<Etat, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Etat attribute) {
        switch (attribute) {
            case PASTRAITEE:
                return 0;
            case TRAITEE:
                return 1;
            case ACCEPTEE:
                return 2;
            default:
                throw new IllegalArgumentException("Unknown" + attribute);
        }
    }

    @Override
    public Etat convertToEntityAttribute(Integer dbData) {
        switch (dbData) {
            case 0:
                return Etat.PASTRAITEE;
            case 1:
                return Etat.TRAITEE;
            case 2:
                return Etat.ACCEPTEE;
            default:
                throw new IllegalArgumentException("Unknown" + dbData);
        }
    }
}
