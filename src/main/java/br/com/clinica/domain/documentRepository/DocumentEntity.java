package br.com.clinica.domain.documentRepository;

import java.lang.reflect.Field;
import java.util.UUID;
import br.com.clinica.domain.documentRepository.annotations.ManyToOne;
import br.com.clinica.domain.documentRepository.annotations.OneToMany;
import br.com.clinica.domain.documentRepository.implementation.RelationEntity;
import br.com.clinica.estruturas.List;

public class DocumentEntity {

    protected String uuid;

    public String getUuid() {
        return uuid;
    }

    public void createUuid() {
        this.uuid = UUID.randomUUID().toString();
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
        this.checkAnnotations();
    }

    public void findRelationalField() {
        this.checkAnnotations();
    }
    // Annotations Checks

    private Field findFieldByName(final String fieldName) {
        final Field[] fields = this.getClass().getDeclaredFields();

        for (final Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        return null;
    }

    private List<Field> getFields() {
        final Field[] fields = this.getClass().getDeclaredFields();
        final List<Field> fieldsList = new List<>();
        for (final Field field : fields) {
            fieldsList.add(field);
        }
        return fieldsList;
    }

    private void oneToManyRelation(final Field field) {
        try {

            final OneToMany annotation = field.getAnnotation(OneToMany.class);

            String fieldValue = this.uuid;

            Field fieldColumn;

            if (!annotation.atribute().equals("uuid")) {
                // Tratar campo inválido
                fieldColumn = this.findFieldByName(annotation.atribute());
                fieldColumn.setAccessible(true);
                fieldValue = fieldColumn.get(this).toString();
                return;
            }

            final RelationEntity relationEntity = new RelationEntity(field,
                    annotation.reference(),
                    fieldValue);

            final List<Object> list = relationEntity.getEntitys();

            field.setAccessible(true);
            field.set(this, list);

        } catch (IllegalArgumentException e) {

        } catch (IllegalAccessException e) {
        }
    }

    private void manyToOneRelation(final Field field) {
        try {

            final ManyToOne annotation = field.getAnnotation(ManyToOne.class);
            final Field fieldColumn = this.findFieldByName(annotation.atribute());
            fieldColumn.setAccessible(true);
            final String fieldValue = (String) fieldColumn.get(this);

            final RelationEntity relationEntity = new RelationEntity(field,
                    annotation.reference(),
                    fieldValue);

            final Object entity = relationEntity.getEntity();
            field.setAccessible(true);
            field.set(this, entity);

        } catch (IllegalArgumentException e) {

        } catch (IllegalAccessException e) {

        }
        return;
    }

    private void checkAnnotations() {
        final List<Field> fields = this.getFields();

        for (final Field field : fields) {
            if (field.isAnnotationPresent(ManyToOne.class)) {
                this.manyToOneRelation(field);
            } else if (field.isAnnotationPresent(OneToMany.class)) {
                this.oneToManyRelation(field);
            }
        }

    }
}
