package com.serli.openstarsclient.data;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AbonneSerialiser extends JsonSerializer<List<User>> {
    @Override
    public void serialize(List<User> o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        o.forEach(user -> {
            user.setAbonnes(null);
            try {
                objectMapper.writeValue(jsonGenerator, user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
