package org.example.strategy;

import org.example.model.Developer;
import java.util.List;

public class ProjectAllocator {
    private AllocationStrategy strategy;

    public void setStrategy(AllocationStrategy strategy) {
        this.strategy = strategy;
    }

    public void allocateTask(List<Developer> team, String task, String requiredSkill) {
        if (strategy == null) {
            System.out.println("Nenhuma estratégia de alocação foi definida.");
            return;
        }

        Developer dev = strategy.allocate(team, requiredSkill);
        if (dev != null) {
            System.out.println("Alocação bem sucedida!");
            dev.assignTask(task);
        } else {
            System.out.println("Falha na alocação para a tarefa: " + task);
        }
    }
}
