package com.cloudoptimizer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cloud_resources")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CloudResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String resourceId;

    private String provider;
    private String resourceType;
    private String region;
    private String department;
    private String owner;
    private Double monthlyCost;
    private Double utilizationPercentage;
    private String status;
}
