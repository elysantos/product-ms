package com.elysantos.productms.util;

import com.elysantos.productms.exceptions.IllegalUseException;

import java.util.UUID;

public class UUIDUtils {
    private UUIDUtils() throws IllegalUseException {
        throw new IllegalUseException("Classe de utilidade não pode ser instanciada diretamente");
    }
    public static String get(){
        return UUID.randomUUID().toString();
    }
}
