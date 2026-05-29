package org.example.strategy;

import org.example.model.Developer;
import java.util.List;

public class SkillBasedAllocation implements AllocationStrategy {
    @Override
    public Developer allocate(List<Developer> team, String requirementType) {
        for (Developer dev : team) {
            if (dev.getSkill().equalsIgnoreCase(requirementType)) {
                return dev;
            }
        }
        return null;
    }
}
