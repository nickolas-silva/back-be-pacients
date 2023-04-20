package br.com.clinica.domain.documentRepository.implementation;

import java.io.File;

public class DeleteDocument {
    private final File file;

    public DeleteDocument(final String filename) {
        this.file = new File(filename);
    }

    public Boolean exists() {
        return file.exists();
    }

    public void deleteContent() {
        file.delete();
    }
}