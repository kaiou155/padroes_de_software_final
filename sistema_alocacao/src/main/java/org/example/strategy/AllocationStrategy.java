package org.example.strategy;

import org.example.model.Developer;
import java.util.List;

public interface AllocationStrategy {
    Developer allocate(List<Developer> team, String requirementType);
}
