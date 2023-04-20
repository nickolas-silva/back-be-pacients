package br.com.clinica.domain.documentRepository.implementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadDocument {

    private FileReader file;
    private Boolean fileExists;

    public ReadDocument(final String filenamePath) {
        File file = new File(filenamePath);
        this.fileExists = file.exists();

        try {
            this.file = new FileReader(file);
        } catch (IOException e) {
            this.file = null;
        }
    }

    public Boolean exists() {
        return fileExists;
    }

    public String getContent() {
        // if(file.)
        final BufferedReader bufferedReader = new BufferedReader(this.file);
        // Tratar se n ão houver arquivo

        final StringBuffer stringBuffer = new StringBuffer();
        String linha = "";

        try {

            while (true) {
                linha = bufferedReader.readLine();
                if (linha == null) {
                    break;
                }
                stringBuffer.append(linha + "\n");
            }

            bufferedReader.close();
        } catch (IOException e) {
            return null;
        }

        return stringBuffer.toString();
    }
}
