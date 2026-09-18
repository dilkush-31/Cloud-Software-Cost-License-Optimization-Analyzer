package com.cloudoptimizer.repository;

import com.cloudoptimizer.entity.OptimizationFinding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptimizationFindingRepository extends JpaRepository<OptimizationFinding, Long> {
}
