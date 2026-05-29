package org.example;

import org.example.manager.RequirementManager;
import org.example.factory.DeveloperFactory;
import org.example.model.Developer;
import org.example.strategy.ProjectAllocator;
import org.example.strategy.SkillBasedAllocation;
import org.example.strategy.FirstAvailableAllocation;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- 1. Testando Singleton ---");
        RequirementManager manager = RequirementManager.getInstance();
        manager.addRequirement("Criar API RESTful");
        manager.addRequirement("Desenvolver tela de login do App");

        System.out.println("\n--- 2. Testando Factory Method ---");
        List<Developer> team = new ArrayList<>();
        team.add(DeveloperFactory.createDeveloper("BACKEND"));
        team.add(DeveloperFactory.createDeveloper("MOBILE"));
        System.out.println("Equipe criada com " + team.size() + " desenvolvedores.");

        System.out.println("\n--- 3. Testando Strategy ---");
        ProjectAllocator allocator = new ProjectAllocator();

        // Usando a estratégia baseada em habilidades
        System.out.println("-> Estrategia: Baseada em Habilidades");
        allocator.setStrategy(new SkillBasedAllocation());
        allocator.allocateTask(team, manager.getRequirements().get(0), "BACKEND");
        allocator.allocateTask(team, manager.getRequirements().get(1), "MOBILE");

        // Mudando a estratégia em tempo de execução
        System.out.println("\n-> Estrategia: Primeiro Disponivel");
        allocator.setStrategy(new FirstAvailableAllocation());
        allocator.allocateTask(team, "Escrever documentacao geral", "ANY");
    }
}
