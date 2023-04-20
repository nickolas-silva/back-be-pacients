package br.com.clinica.domain.documentRepository.implementation;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import br.com.clinica.estruturas.List;
import br.com.clinica.estruturas.Set;

public class FindDocument {

    private String getPathFileName(Path path) {
        return path.getFileName()
                .toString().replace(".txt", "");
    }

    private DirectoryStream<Path> getDirectoryStream(final String repositoryPath) {
        try {
            return Files
                    .newDirectoryStream(Paths.get(repositoryPath));
        } catch (IOException e) {
            return null;
        }
    }

    private String getDocumentPath(String... paths) {
        String fullPath = "";

        for (final String path : paths) {
            fullPath += path + "/";
        }

        return fullPath;
    }

    private Set<String> getDocumentsPaths(final String repositoryPath, final Integer pag, Integer limitPerPag) {

        DirectoryStream<Path> stream = getDirectoryStream(repositoryPath);
        final Set<String> fileSet = new Set<>();

        if (stream == null) {
            return fileSet;
        }

        if (limitPerPag == null) {
            limitPerPag = 2;
        }

        Integer forCont = 0;

        for (Path path : stream) {
            forCont++;

            if (pag != null) {
                final Integer startPage = (pag - 1) * limitPerPag;
                final Integer finalPage = startPage + limitPerPag;

                if (!(startPage < forCont && forCont <= finalPage)) {
                    continue;
                }
            }

            fileSet.add(getPathFileName(path));

            if (pag == null && forCont == 100) {
                break;
            }

        }

        return fileSet;
    }

    public String getDocument(final String repositoryPath, final FindDocumentLambdaInterface isSelected) {

        DirectoryStream<Path> stream = getDirectoryStream(repositoryPath);

        if (stream == null) {
            return null;
        }

        String resultJsonEntity = null;

        for (Path path : stream) {

            final String documentPath = getDocumentPath(repositoryPath, getPathFileName(path));
            final ReadDocument readDocument = new ReadDocument(documentPath);
            final String jsonEntity = readDocument.getContent();

            if (isSelected.exec(jsonEntity)) {
                resultJsonEntity = jsonEntity;
            }

        }

        return resultJsonEntity;

    }

    public List<String> getDocuments(final String repositoryPath, final Integer pag, final Integer limitPerPag) {

        final Set<String> documentsNames = getDocumentsPaths(repositoryPath, pag, limitPerPag);
        final List<String> jsonEntitys = new List<>();

        for (final String documentName : documentsNames) {
            final String documentPath = getDocumentPath(repositoryPath, documentName);
            final ReadDocument readDocument = new ReadDocument(documentPath);
            final String jsonEntity = readDocument.getContent();
            jsonEntitys.add(jsonEntity);
        }

        return jsonEntitys;
    }

    public List<String> getDocuments(String repositoryPath, final Integer pag, final Integer limitPerPag,
            final FindDocumentLambdaInterface isSelected) {

        DirectoryStream<Path> stream = getDirectoryStream(repositoryPath);
        final List<String> jsonEntitys = new List<>();

        if (stream == null) {
            return jsonEntitys;
        }

        for (Path path : stream) {

            final String documentPath = getDocumentPath(repositoryPath, getPathFileName(path));
            final ReadDocument readDocument = new ReadDocument(documentPath);
            final String jsonEntity = readDocument.getContent();

            if (isSelected.exec(jsonEntity)) {
                jsonEntitys.add(jsonEntity);
            }

        }

        return jsonEntitys;
    }

}
