package br.com.clinica.domain.documentRepository.implementation;

import br.com.clinica.estruturas.List;

public class FindSpecificDocument {

    private String addComma(final String value) {
        return "\"" + value + "\"";
    }

    private String convertToJsonKeyValue(final String key, final String value) {
        return addComma(key) + ":" + addComma(value);
    }

    private Boolean containsValue(final String document, final String key, final String value) {
        final String find = convertToJsonKeyValue(key, value);
        return document.contains(find);
    }

    public List<String> getDocumentsBySpecificField(final String repositoryPath, final String key, final String value) {
        List<String> documents = (new FindDocument()).getDocuments(repositoryPath, null, null, null);
        List<String> selectedDocuments = new List<>();

        for (String document : documents) {
            if (containsValue(document, key, value)) {
                selectedDocuments.add(document);
            }
        }

        return selectedDocuments;
    }

    public String getDocumentBySpecificField(final String repositoryPath, final String key, final String value) {

        final FindDocument findDocument = new FindDocument();

        final FindDocumentLambdaInterface f1 = (final String jsonEntity) -> {
            return containsValue(jsonEntity, key, value);
        };

        return findDocument.getDocument(repositoryPath, f1);
    }
}
