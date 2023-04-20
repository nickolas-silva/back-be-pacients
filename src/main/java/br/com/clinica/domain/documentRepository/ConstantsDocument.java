package br.com.clinica.domain.documentRepository;

public class ConstantsDocument {
    public static String getAbsoluteFilenameDbPath(final String filename) {
        return getAbsoluteDbPath() + filename + ".txt";
    }

    public static String getAbsoluteDbPath() {
        return "db/";
    }
}
