package org.example.model;

public class BackendDeveloper implements Developer {
    @Override
    public void assignTask(String task) {
        System.out.println("Backend Dev trabalhando em: " + task);
    }

    @Override
    public String getSkill() {
        return "BACKEND";
    }
}
