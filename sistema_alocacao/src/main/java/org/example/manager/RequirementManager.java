package org.example.manager;

import java.util.ArrayList;
import java.util.List;

public class RequirementManager {
    private static RequirementManager instance;
    private List<String> requirements;

    private RequirementManager() {
        requirements = new ArrayList<>();
    }

    public static RequirementManager getInstance() {
        if (instance == null) {
            instance = new RequirementManager();
        }
        return instance;
    }

    public void addRequirement(String req) {
        requirements.add(req);
        System.out.println("Requisito adicionado: " + req);
    }

    public List<String> getRequirements() {
        return requirements;
    }
}
