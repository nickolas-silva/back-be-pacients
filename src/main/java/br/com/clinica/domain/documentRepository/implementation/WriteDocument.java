package br.com.clinica.domain.documentRepository.implementation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteDocument {
    private FileWriter file;
    private Boolean permission;

    public WriteDocument(final String filename) {
        try {
            this.file = new FileWriter(filename);
            this.permission = false;
        } catch (IOException e) {
            this.permission = true;
            this.file = null;
        }
    }

    public Boolean isBlocked() {
        return permission;
    }

    public void setContent(String content) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(this.file);
            bufferedWriter.append(content);
            bufferedWriter.close();
        } catch (Exception e) {
            return;
        }
    }
}
