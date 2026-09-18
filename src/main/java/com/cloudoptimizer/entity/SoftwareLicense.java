package com.cloudoptimizer.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "software_licenses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SoftwareLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String softwareName;
    private String vendor;
    private String licenseType;
    private Integer purchasedQuantity;
    private Integer assignedQuantity;
    private Integer activeUsers;
    private Integer unusedLicenses;
    private Integer excessUsage;
    private Double costPerLicense;
    private LocalDate renewalDate;
    private String department;
}
