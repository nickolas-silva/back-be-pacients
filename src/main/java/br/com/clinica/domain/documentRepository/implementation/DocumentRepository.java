package br.com.clinica.domain.documentRepository.implementation;

import java.io.File;
import br.com.clinica.domain.documentRepository.DocumentEntity;
import br.com.clinica.estruturas.List;
import br.com.clinica.domain.documentRepository.ConstantsDocument;

public class DocumentRepository<T extends DocumentEntity> {

    private final JsonDocument<T> jsonDocument;
    private final Class<T> internalClass;

    public DocumentRepository(final Class<T> cls) {
        this.internalClass = cls;
        this.jsonDocument = new JsonDocument<>();
        this.createrRepositoryDir();
    }

    private String getRepositoryDirPath(final String... uuid) {

        String path = this.internalClass.getSimpleName().toLowerCase();

        if (uuid.length == 1) {
            path += "/" + uuid[0];
        }
        return ConstantsDocument.getAbsoluteDbPath() + path;
    }

    private void createrRepositoryDir() {
        final File theDir = new File(getRepositoryDirPath());
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
    }

    public T save(final T entity, final Boolean... uuidOnlyNull) {

        final Boolean uuidIsNull = entity.getUuid() == null;
        final Boolean onlyUuidNull = uuidOnlyNull.length != 0 ? uuidOnlyNull[0] : true;

        if (onlyUuidNull && !uuidIsNull) {
            return null;
        }

        if (!onlyUuidNull && uuidIsNull) {
            return null;
        }

        entity.createUuid();

        final String json = jsonDocument.serialize(entity);

        final WriteDocument document = new WriteDocument(getRepositoryDirPath(entity.getUuid()));

        if (document.isBlocked()) {
            return null;
        }

        document.setContent(json);

        return entity;
    }

    public T update(final T entity) {
        return this.save(entity, false);
    }

    public T findByUuid(final String uuid) {
        ReadDocument document = new ReadDocument(getRepositoryDirPath(uuid));

        if (!document.exists()) {
            return null;
        }

        final String json = document.getContent();

        T entity = jsonDocument.deserialize(json, internalClass);
        entity.findRelationalField();

        return entity;
    }

    public T findBySpecificField(final String key, final String value) {
        final FindSpecificDocument findSpecificDocument = new FindSpecificDocument();
        final String jsonResult = findSpecificDocument.getDocumentBySpecificField(getRepositoryDirPath(), key,
                value);

        if (jsonResult == null) {
            return null;
        }

        final T entity = jsonDocument.deserialize(jsonResult, internalClass);
        entity.findRelationalField();

        return entity;
    }

    public void deleteByUuid(final String uuid) {
        final DeleteDocument document = new DeleteDocument(getRepositoryDirPath(uuid));
        if (!document.exists()) {
            return;
        }
        document.deleteContent();
    }

    public List<T> findAll(final Integer... paginationsConfig) {
        FindDocument document = new FindDocument();

        Integer pag = 1;
        Integer limitPerPag = 10;

        final Integer argsLen = paginationsConfig.length;

        if (argsLen > 0) {
            if (paginationsConfig[0] != null) {
                pag = paginationsConfig[0];
            }

            if (argsLen == 2) {
                limitPerPag = paginationsConfig[1];
            }
        }

        final List<String> jsonEntitys = document.getDocuments(getRepositoryDirPath(), pag, limitPerPag);

        final List<T> entitys = new List<>();

        for (final String jsonEntity : jsonEntitys) {
            T entity = jsonDocument.deserialize(jsonEntity, internalClass);
            entity.findRelationalField();
            entitys.add(entity);
        }

        return entitys;
    }

}