package org.example.model;

public class MobileDeveloper implements Developer {
    @Override
    public void assignTask(String task) {
        System.out.println("Mobile Dev trabalhando em: " + task);
    }

    @Override
    public String getSkill() {
        return "MOBILE";
    }
}
