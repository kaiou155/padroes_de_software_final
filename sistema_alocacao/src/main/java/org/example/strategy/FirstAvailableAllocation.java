package org.example.strategy;

import org.example.model.Developer;
import java.util.List;

public class FirstAvailableAllocation implements AllocationStrategy {
    @Override
    public Developer allocate(List<Developer> team, String requirementType) {
        if (!team.isEmpty()) {
            return team.get(0);
        }
        return null;
    }
}