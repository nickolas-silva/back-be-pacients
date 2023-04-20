package br.com.clinica.domain.documentRepository.implementation;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.clinica.domain.documentRepository.ConstantsDocument;
import br.com.clinica.estruturas.List;

public class RelationEntity {

    final Field field;
    final String key;
    final String value;

    public RelationEntity(final Field field, final String key, final String value) {
        this.field = field;
        this.key = key;
        this.value = value;
    }

    private String getLastSplitString(final String fullString) {
        final String fullEntityName = fullString.replace(">", "");
        final String[] allClasses = fullEntityName.split("\\.");
        final Integer last = allClasses.length - 1;
        final String entityName = allClasses[last];
        return entityName.toLowerCase();
    }

    private String getFieldName() {

        String fieldName = getLastSplitString(this.field.getType().getName());

        if (fieldName.equals("list")) {
            fieldName = getLastSplitString(this.field.getGenericType().getTypeName());
        }

        return fieldName;
    }

    private Class<?> getFieldType() {
        final String fieldName = getLastSplitString(this.field.getType().toString());

        if (fieldName.equals("list")) {
            ParameterizedType stringListType = (ParameterizedType) this.field.getGenericType();
            Class<?> listGenericClass = (Class<?>) stringListType.getActualTypeArguments()[0];
            return listGenericClass;
        }

        return this.field.getType();
    }

    private String getEntityRepositoryPath() {
        final String path = ConstantsDocument.getAbsoluteDbPath() + getFieldName();
        return path;
    }

    private List<String> getJsonEntitys() {
        final FindSpecificDocument findSpecificDocument = new FindSpecificDocument();
        final String path = getEntityRepositoryPath();
        final List<String> list = findSpecificDocument.getDocumentsBySpecificField(path,
                this.key, this.value);

        return list;
    }

    private String getJsonEntity() {
        final FindSpecificDocument findSpecificDocument = new FindSpecificDocument();
        final String path = getEntityRepositoryPath();
        final String jsonEntity = findSpecificDocument.getDocumentBySpecificField(path, this.key, this.value);
        return jsonEntity;
    }

    public List<Object> getEntitys() {
        final List<String> jsonEntitys = getJsonEntitys();
        final List<Object> entitys = new List<>();
        final ObjectMapper objectMapper = new ObjectMapper();

        if (jsonEntitys.size() == 0)
            return new List<>();

        for (final String jsonEntity : jsonEntitys) {
            try {
                entitys.add(objectMapper.readValue(jsonEntity, this.getFieldType()));
            } catch (JsonMappingException e) {
                continue;
            } catch (JsonProcessingException e) {
                continue;
            }
        }
        return entitys;
    }

    public Object getEntity() {
        final String jsonEntity = getJsonEntity();
        final ObjectMapper objectMapper = new ObjectMapper();
        Object entity = null;
        try {
            entity = objectMapper.readValue(jsonEntity, this.getFieldType());
        } catch (JsonMappingException e) {
        } catch (JsonProcessingException e) {
        }

        return entity;
    }
}
