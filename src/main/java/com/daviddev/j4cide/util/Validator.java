package com.daviddev.j4cide.util;

import java.io.File;

import static java.lang.String.format;

public class Validator {

    public static File checkIsReadable(File file, String name) {
        checkIfExists(file, name);
        if (!file.canRead())
            throw new SecurityException( format("Não é possível ler o arquivo \"%s\".", name) );
        return file;
    }

    public static File checkIfExists(File file, String name) {
        checkIsNotNull(file, name);
        if (!file.exists())
            throw new NullPointerException( format("O arquivo \"%s\" não existe.", name) );
        return file;
    }

    public static <E> E checkIsNotNull(E object, String name) {
        if (object == null)
            throw new NullPointerException( format("O campo \"%s\" não pode ser nulo.", name) );
        return object;
    }

    public static String checkIsNullOrEmpty(String str, String name) {
        if (str == null || str.isEmpty())
            throw new NullPointerException( format("O valor de texto \"%s\" é nulo ou vazio.", name) );
        return str;
    }

}
