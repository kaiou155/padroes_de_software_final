package org.example.factory;

import org.example.model.BackendDeveloper;
import org.example.model.Developer;
import org.example.model.MobileDeveloper;

public class DeveloperFactory {
    public static Developer createDeveloper(String type) {
        if (type.equalsIgnoreCase("BACKEND")) {
            return new BackendDeveloper();
        } else if (type.equalsIgnoreCase("MOBILE")) {
            return new MobileDeveloper();
        }
        throw new IllegalArgumentException("Tipo de desenvolvedor desconhecido.");
    }
}
