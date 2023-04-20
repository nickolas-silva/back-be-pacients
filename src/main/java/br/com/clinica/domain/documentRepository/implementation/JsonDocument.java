package br.com.clinica.domain.documentRepository.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDocument<T> {

    ObjectMapper objectMapper;

    public JsonDocument() {
        this.objectMapper = new ObjectMapper();
    }

    public String serialize(T object) {
        try {
            final String json = objectMapper.writeValueAsString(object);
            return json;
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public T deserialize(final String json, Class<T> objectClass) {

        try {
            T object = objectMapper.readValue(json, objectClass);
            return object;
        } catch (JsonMappingException e) {
            return null;
        } catch (JsonProcessingException e) {
            return null;
        }

    }
}
