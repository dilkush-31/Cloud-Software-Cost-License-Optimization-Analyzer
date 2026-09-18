package com.cloudoptimizer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "optimization_findings")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OptimizationFinding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String resourceOrSoftware;
    private String issue;
    private String severity;
    private Double estimatedMonthlySaving;
    private Double estimatedAnnualSaving;

    @Column(length = 1000)
    private String recommendation;

    private String status;
}
